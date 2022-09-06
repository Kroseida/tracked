package org.kroseida.tracked.backend.logic.activity.exception;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when an activity with the given name already exists.
 */
public class ActivityAlreadyExistsException extends TrackedBackendException {

  public ActivityAlreadyExistsException() {
    super("ACTIVITY_ALREADY_EXISTS", "Activity cannot be created. Name is already taken.");
  }

}
