package com.valid.clients.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClientDTO implements Serializable {

	private int idClient;
	@NotNull(message = "name cannot be null")
	private String name;
	@NotNull(message = "lastName cannot be null")
	private String lastName;
	private boolean processed;
	private static final long serialVersionUID = 1L;

}
