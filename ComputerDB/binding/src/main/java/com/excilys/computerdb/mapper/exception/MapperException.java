package com.excilys.computerdb.mapper.exception;

public class MapperException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Exception with an error message.
   * @param message : the error message.
   */
  public MapperException(String message) {
    super(message);
  }

  /**
   * Exception with an error message and the cause of the message.
   * @param message : the error message.
   * @param cause : the error cause.
   */
  public MapperException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Exception with the cause of the error.
   * @param cause : the error cause.
   */
  public MapperException(Throwable cause) {
    super(cause);
  }
}
