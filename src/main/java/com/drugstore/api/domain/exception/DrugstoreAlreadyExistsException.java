package com.drugstore.api.domain.exception;

public class DrugstoreAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DrugstoreAlreadyExistsException(String message) {
		super(message);
	}
	
}
