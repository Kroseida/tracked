package org.kroseida.tracked.backend.logic.organization;

import org.kroseida.tracked.backend.logic.user.execption.EmailTakenException;
import org.kroseida.tracked.backend.logic.user.execption.UserAlreadyExistsException;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;

public interface OrganizationLogicLayer {


  /**
   * This method will create a new organization.
   * <p>
   * If the organization already exists, it will throw an {@link UserAlreadyExistsException}.
   *
   * @param name The name of the organization.
   * @return The organization that was created.
   */
  Organization createOrganization(String name);

}
