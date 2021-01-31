package com.valid.clients.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valid.clients.dto.ClientDTO;
import com.valid.clients.dto.ProcessClientsDTO;
import com.valid.clients.dto.ResponseClientDTO;
import com.valid.clients.dto.ResponseProcessClientDTO;
import com.valid.clients.service.imp.ClientServiceImp;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientServiceImp clientService;

	@PostMapping
	@ApiOperation(nickname = "create client", value = "Endpoint for create clients")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succes operation"),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<ResponseClientDTO> createClient(@Validated @RequestBody ClientDTO clientDTO) {
		return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(nickname = "create client", value = "Endpoint for get all clients")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succes operation"),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<List<ClientDTO>> getClient() {
		return new ResponseEntity<>(clientService.getClient(), HttpStatus.OK);
	}

	@PatchMapping
	@ApiOperation(nickname = "create client", value = "Endpoint for change status of process clients from false to true")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succes operation"),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<ResponseProcessClientDTO> processClients(@RequestBody ProcessClientsDTO processClientsDTO) {
		return new ResponseEntity<>(clientService.processClients(processClientsDTO), HttpStatus.OK);
	}
}
