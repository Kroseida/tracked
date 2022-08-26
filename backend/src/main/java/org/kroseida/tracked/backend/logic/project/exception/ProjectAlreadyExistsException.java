package org.kroseida.tracked.backend.logic.project.exception;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when a user tries to create a new project with the same name.
 */
public class ProjectAlreadyExistsException extends TrackedBackendException {

  public ProjectAlreadyExistsException() {
    super("PROJECT_ALREADY_EXISTS", "Project cannot be created. Name is already taken.");
  }

}
