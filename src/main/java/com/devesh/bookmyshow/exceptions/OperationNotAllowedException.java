package com.devesh.bookmyshow.exceptions;

public class OperationNotAllowedException extends RuntimeException {
  public OperationNotAllowedException(String message) {
    super(message);
  }
}
