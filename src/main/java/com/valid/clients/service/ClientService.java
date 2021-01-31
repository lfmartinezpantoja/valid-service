package com.valid.clients.service;

import java.util.List;

import com.valid.clients.dto.ClientDTO;
import com.valid.clients.dto.ProcessClientsDTO;
import com.valid.clients.dto.ResponseClientDTO;
import com.valid.clients.dto.ResponseProcessClientDTO;

public interface ClientService {
	
	public ResponseClientDTO createClient(ClientDTO clientDTO);
	
	public List<ClientDTO> getClient();

	public ResponseProcessClientDTO processClients(ProcessClientsDTO processClientsDTO);
	
	

}
