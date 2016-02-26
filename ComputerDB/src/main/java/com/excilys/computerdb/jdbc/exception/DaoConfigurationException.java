package com.excilys.computerdb.jdbc.exception;

public class DaoConfigurationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Exception with an error message.
   * @param message : the error message.
   */
  public DaoConfigurationException(String message) {
    super(message);
  }

  /**
   * Exception with an error message and the cause of the message.
   * @param message : the error message.
   * @param cause : the error cause.
   */
  public DaoConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Exception with the cause of the error.
   * @param cause : the error cause.
   */
  public DaoConfigurationException(Throwable cause) {
    super(cause);
  }
}
