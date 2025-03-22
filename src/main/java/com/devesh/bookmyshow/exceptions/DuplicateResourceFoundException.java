package com.devesh.bookmyshow.exceptions;

public class DuplicateResourceFoundException extends RuntimeException {
  public DuplicateResourceFoundException(String message) {
    super(message);
  }
}
