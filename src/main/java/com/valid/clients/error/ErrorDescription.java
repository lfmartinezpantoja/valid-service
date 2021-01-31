package com.valid.clients.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorDescription {
	
	
	
	THERE_ARE_NO_CLIENTS("There are no clients to show"),
	CLIENTS_NOT_PROCESSED("Clients were not processed, client with id %s not found"),
	VALIDATION_EXCEPTION("The following errors ocurred validating the JSON, errors: %s"),
	GENERAL_EXCEPTION("An uncontrolled error has ocurred, please try again or contact your aministrator");
	
	private String message;
}
