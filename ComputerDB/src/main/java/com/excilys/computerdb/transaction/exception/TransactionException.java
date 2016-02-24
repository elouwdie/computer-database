package com.excilys.computerdb.transaction.exception;

public class TransactionException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Exception with an error message.
   * @param message : the error message.
   */
  public TransactionException(String message) {
    super(message);
  }

  /**
   * Exception with an error message and the cause of the message.
   * @param message : the error message.
   * @param cause : the error cause.
   */
  public TransactionException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Exception with the cause of the error.
   * @param cause : the error cause.
   */
  public TransactionException(Throwable cause) {
    super(cause);
  }
}
