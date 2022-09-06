package org.kroseida.tracked.backend.logic.activity.exception;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when an activity with the given id could not be found.
 */
public class AcitvityNotFoundException extends TrackedBackendException {

  public AcitvityNotFoundException() {
    super("ACTIVITY_NOT_FOUND", "The activity with the given id was not found.");
  }

}
