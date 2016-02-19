package com.excilys.computerdb.mapper.exceptions;

public class MapperException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MapperException(String message) {
		super(message);
	}

	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}

	public MapperException(Throwable cause) {
		super(cause);
	}
}
