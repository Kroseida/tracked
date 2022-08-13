package org.kroseida.tracked.backend.logic.exception;

import lombok.Getter;

/**
 * This class is used to represent a business exception in the backend.
 * <p>
 * Our REST Endpoints will handle business exceptions by returning a
 * {@link org.kroseida.tracked.backend.util.response.ResponseData} object with result set to "error".
 */
@Getter
public class TrackedBackendException extends RuntimeException {

  private final String type;

  public TrackedBackendException(String type, String message) {
    super(message);
    this.type = type;
  }

}
