package com.drugstore.api.domain.exception;

public class AlreadyExistingCategoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadyExistingCategoryException(String message) {
		super(message);
	}
}
