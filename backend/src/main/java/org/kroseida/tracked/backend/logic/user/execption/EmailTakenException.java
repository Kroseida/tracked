package org.kroseida.tracked.backend.logic.user.execption;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when a user tries to register a new user with the same email.
 */
public class EmailTakenException extends TrackedBackendException {

  public EmailTakenException() {
    super("EMAIL_TAKEN", "User cannot be created. Email is already taken.");
  }

}
