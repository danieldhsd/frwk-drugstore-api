package com.frwk.drugstore.domain.exception;

public class EntityInUseException extends GenericException {

	private static final long serialVersionUID = 1L;

	public EntityInUseException(String message) {
		super(message);
	}
}
