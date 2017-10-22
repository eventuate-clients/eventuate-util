package io.eventuate.util.test.async;

public class EventuallyException extends RuntimeException {
  public EventuallyException(String message, Throwable t) {
    super(message, t);
  }
}