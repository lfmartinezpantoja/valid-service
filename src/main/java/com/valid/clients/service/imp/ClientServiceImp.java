package com.valid.clients.service.imp;

import static com.valid.clients.error.ErrorDescription.CLIENTS_NOT_PROCESSED;
import static com.valid.clients.error.ErrorDescription.THERE_ARE_NO_CLIENTS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.valid.clients.dto.ClientDTO;
import com.valid.clients.dto.ProcessClientsDTO;
import com.valid.clients.dto.ResponseClientDTO;
import com.valid.clients.dto.ResponseProcessClientDTO;
import com.valid.clients.exception.CustomExpection;
import com.valid.clients.model.Client;
import com.valid.clients.repository.ClientRepository;
import com.valid.clients.service.ClientService;

import lombok.extern.java.Log;

@Log
@Service
public class ClientServiceImp implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public ResponseClientDTO createClient(ClientDTO clientDTO) {

		log.info("Information received to create client: name: " + clientDTO.getName() + "lastName: "
				+ clientDTO.getLastName());

		Client client = new Client();
		client.setName(clientDTO.getName());
		client.setLastName(clientDTO.getLastName());

		clientRepository.save(client);

		log.info("Client created succesfully, idClient: " + client.getIdClient());

		return new ResponseClientDTO("Client created succesfully", client.getIdClient(), true);
	}

	@Override
	public List<ClientDTO> getClient() {
		log.info("Request was received for obtain all clients");

		List<ClientDTO> clientsResponseDTO;

		List<Client> clients = clientRepository.findAll();

		if (clients.isEmpty()) {
			throw new CustomExpection(THERE_ARE_NO_CLIENTS.getMessage(), HttpStatus.NOT_FOUND);
		}

		clientsResponseDTO = clients.stream().map(client -> modelMapper.map(client, ClientDTO.class))
				.collect(Collectors.toList());

		log.info("Request proccesed succesfully, total clients: " + clientsResponseDTO.size());

		return clientsResponseDTO;
	}

	@Override
	public ResponseProcessClientDTO processClients(ProcessClientsDTO processClientsDTO) {
		log.info("Request received for proccess clients, total clients received: "
				+ processClientsDTO.getIdsClient().length);

		for (int idClient : processClientsDTO.getIdsClient()) {
			Optional<Client> verifyClient = clientRepository.findById(idClient);
			if (!verifyClient.isPresent()) {
				
				log.info("Client with id: " + idClient
						+ " was not found, rollback  processed clients is begining");
				
				revertProccesedClients(processClientsDTO, idClient);
				throw new CustomExpection(String.format(CLIENTS_NOT_PROCESSED.getMessage(), idClient),
						HttpStatus.BAD_REQUEST);
			}
			verifyClient.get().setProcessed(true);
			clientRepository.save(verifyClient.get());
		}

		log.info("Request processed sucessfully, total clients processed: " + processClientsDTO.getIdsClient().length);

		return new ResponseProcessClientDTO("Clients processed successfully", true);
	}

	private void revertProccesedClients(ProcessClientsDTO processClientsDTO, int idClient) {

		for (int idRevertClient : processClientsDTO.getIdsClient()) {
			if (idRevertClient == idClient) {
				break;
			}
			Optional<Client> revertClient = clientRepository.findById(idRevertClient);
			if (revertClient.isPresent()) {

				log.info("Reverting processed client with id: " + idRevertClient);

				revertClient.get().setProcessed(false);
				clientRepository.save(revertClient.get());
			}

		}

	}

}
