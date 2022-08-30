package org.kroseida.tracked.backend.logic.project;

import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface ProjectLogicLayer {

  /**
   * This method will return the project with the given id.
   *
   * @param id The id of the project.
   * @return The project with the given id.
   */
  Project getProject(UUID id);

  /**
   * This method will return all the projects stored in the database.
   *
   * @param organizationId The id of the organization to get the projects from. If null, it will return all projects.
   * @param filter         Filter by name or description.
   * @param pageable       The pageable object.
   * @return a list of all the projects.
   */
  Page<Project> getProjects(UUID organizationId, String filter, Pageable pageable);

  /**
   * This method will create a new project in the database.
   *
   * @param name           The name of the project.
   * @param description    The description of the project.
   * @param active         The active status of the project.
   * @param organizationId The id of the organization to create the project in.
   * @return the created project.
   */
  Project createProject(String name, String description, boolean active, LocalDate startDate, LocalDate endDate,
                        UUID organizationId);

  /**
   * This method will delete the project with the given id.
   *
   * @param id The id of the project.
   */
  void deleteProject(UUID id);

  /**
   * This method will update the project with the given id.
   * <p>
   * Setting the one field to null will not update the target field.
   * e.g. if the name is null, the name will not be updated.
   *
   * @param id          The id of the project.
   * @param name        The new name of the project.
   * @param description The new description of the project.
   * @param startDate   The new start date of the project.
   * @param endDate     The new end date of the project.
   * @param active      The new active state of the project.
   */
  void updateProject(UUID id, String name, String description, LocalDate startDate, LocalDate endDate, Boolean active);
}
