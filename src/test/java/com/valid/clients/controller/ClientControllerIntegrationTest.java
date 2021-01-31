package com.valid.clients.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valid.clients.dto.ClientDTO;
import com.valid.clients.dto.ProcessClientsDTO;

@AutoConfigureMockMvc
@SpringBootTest
@Sql(scripts = "classpath:data.sql")
class ClientControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createClientSuccessfully() throws JsonProcessingException, Exception {

		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setName("Luis Fernando");
		clientDTO.setLastName("Martinez Pantoja");

		mvc.perform(MockMvcRequestBuilders.post("/clients").content(objectMapper.writeValueAsString(clientDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void getAllClientsSuccessfully() throws JsonProcessingException, Exception {

		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setName("Luis Fernando");
		clientDTO.setLastName("Martinez Pantoja");

		mvc.perform(MockMvcRequestBuilders.get("/clients").content(objectMapper.writeValueAsString(clientDTO))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void proccesClientsSuccessfully() throws JsonProcessingException, Exception {

		int[] idsClient = { 1, 2 };
		ProcessClientsDTO processClientsDTO = new ProcessClientsDTO();
		processClientsDTO.setIdsClient(idsClient);

		mvc.perform(MockMvcRequestBuilders.patch("/clients").content(objectMapper.writeValueAsString(processClientsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	
	@Test()
	void proccesClientsAndExpectExceptionByClientNotFound() throws JsonProcessingException, Exception {
		
		int[] idsClient = {100};
		ProcessClientsDTO processClientsDTO = new ProcessClientsDTO();
		processClientsDTO.setIdsClient(idsClient);

		mvc.perform(MockMvcRequestBuilders.patch("/clients").content(objectMapper.writeValueAsString(processClientsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());		
		
	}
	
}
