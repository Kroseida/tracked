package org.kroseida.tracked.backend.logic.organization;

import org.kroseida.tracked.backend.logic.user.execption.UserAlreadyExistsException;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
  Page<Organization> getOrganizations(Pageable pageable, String filter);

  /**
   * This method will delete the organization with the given id.
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

  /**
   * This method will update the organization with the given id.
   * <p>
   * Setting the one field to null will not update the target field.
   * e.g. if the name is null, the name will not be updated.
   *
   * @param id          The id of the organization.
   * @param name        The new name of the organization.
   * @param description The new description of the organization.
   * @param active      The new active state of the organization.
   */
  void updateOrganization(UUID id, String name, String description, Boolean active);

}
