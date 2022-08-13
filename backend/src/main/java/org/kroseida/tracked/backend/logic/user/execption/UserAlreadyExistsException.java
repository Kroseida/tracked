package org.kroseida.tracked.backend.logic.user.execption;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when a user tries to register a new user with the same username.
 */
public class UserAlreadyExistsException extends TrackedBackendException {

  public UserAlreadyExistsException() {
    super("USER_ALREADY_EXISTS", "User cannot be created. Username is already taken.");
  }

}
