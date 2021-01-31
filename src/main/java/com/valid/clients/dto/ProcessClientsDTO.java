package com.valid.clients.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProcessClientsDTO implements Serializable {

	private int[] idsClient;
	private static final long serialVersionUID = 1L;
}
