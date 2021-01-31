package com.valid.clients.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseProcessClientDTO implements Serializable {

	private String message;
	private boolean success;
	private static final long serialVersionUID = 1L;
}
