package com.frwk.drugstore.domain.exception;

public class EntityNotFoundException extends GenericException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
