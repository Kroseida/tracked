package org.kroseida.tracked.backend.logic.user.execption;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when invalid authentication data is provided.
 */
public class InvalidAuthenticationDataException extends TrackedBackendException {

  public InvalidAuthenticationDataException(String message) {
    super("INVALID_AUTHENTICATION_DATA", message);
  }

}
