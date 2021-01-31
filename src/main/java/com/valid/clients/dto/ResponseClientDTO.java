package com.valid.clients.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseClientDTO implements Serializable {

	private String message;
	private int idClient;
	private boolean success;
	private static final long serialVersionUID = 1L;
}
