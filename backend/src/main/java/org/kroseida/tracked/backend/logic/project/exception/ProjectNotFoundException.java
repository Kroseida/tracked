package org.kroseida.tracked.backend.logic.project.exception;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when a project with the given id could not be found.
 */
public class ProjectNotFoundException extends TrackedBackendException {

  public ProjectNotFoundException() {
    super("PROJECT_NOT_FOUND", "The project with the given id was not found.");
  }

}
