package org.kroseida.tracked.backend.logic.organization;

import org.kroseida.tracked.backend.logic.user.execption.UserAlreadyExistsException;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;

import java.util.List;
import java.util.UUID;

public interface OrganizationLogicLayer {


  /**
   * This method will create a new organization.
   * <p>
   * If the organization already exists, it will throw an {@link UserAlreadyExistsException}.
   *
   * @param name        The name of the organization.
   * @param description The description of the organization.
   * @param active      The active state of the organization.
   *                    If the organization is active, it will be visible to the users.
   * @return The organization that was created.
   */
  Organization createOrganization(String name, String description, boolean active);

  /**
   * This method will return all the organizations stored in the database.
   *
   * @return A list of all the organizations.
   */
  List<Organization> getOrganizations();

  /**
   * This method will return the organization with the given id.
   *
   * @param id The id of the organization.
   */
  void deleteOrganization(UUID id);

  /**
   * This method will return the organization with the given id.
   *
   * @param id The id of the organization.
   */
  Organization getOrganization(UUID id);

}