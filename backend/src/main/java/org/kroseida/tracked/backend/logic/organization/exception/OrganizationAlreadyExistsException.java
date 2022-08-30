package org.kroseida.tracked.backend.logic.organization.exception;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when a user tries to create a new organization with the same name.
 */
public class OrganizationAlreadyExistsException extends TrackedBackendException {

  public OrganizationAlreadyExistsException() {
    super("ORGANIZATION_ALREADY_EXISTS", "Organization cannot be created. Name is already taken.");
  }

}
