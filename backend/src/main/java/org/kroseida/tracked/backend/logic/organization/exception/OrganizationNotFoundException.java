package org.kroseida.tracked.backend.logic.organization.exception;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;

/**
 * This exception is thrown when an organization with the given id could not be found.
 */
public class OrganizationNotFoundException extends TrackedBackendException {

  public OrganizationNotFoundException() {
    super("ORGANIZATION_NOT_FOUND", "The organization with the given id was not found.");
  }

}
