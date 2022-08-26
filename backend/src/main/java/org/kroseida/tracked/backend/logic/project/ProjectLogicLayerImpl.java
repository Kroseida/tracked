package org.kroseida.tracked.backend.logic.project;

import org.kroseida.tracked.backend.logic.organization.OrganizationLogicLayer;
import org.kroseida.tracked.backend.logic.project.exception.ProjectAlreadyExistsException;
import org.kroseida.tracked.backend.logic.project.exception.ProjectNotFoundException;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.kroseida.tracked.backend.persistance.project.ProjectRepository;
import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.kroseida.tracked.backend.util.logic.LogicUtils.updateField;

@Component
public class ProjectLogicLayerImpl implements ProjectLogicLayer {

  private final OrganizationLogicLayer organizationLogicLayer;
  private final ProjectRepository projectRepository;

  @Autowired
  public ProjectLogicLayerImpl(OrganizationLogicLayer organizationLogicLayer, ProjectRepository projectRepository) {
    this.organizationLogicLayer = organizationLogicLayer;
    this.projectRepository = projectRepository;
  }

  @Override
  public Project getProject(UUID id) {
    return projectRepository.findById(id)
        .orElseThrow(() -> new ProjectNotFoundException());
  }

  @Override
  public Page<Project> getProjects(UUID organizationId, String filter, Pageable pageable) {
    return projectRepository.findAll(organizationId, pageable, filter);
  }

  @Override
  public Project createProject(String name, String description, boolean active, long startedAt, UUID organizationId) {
    Organization organization = organizationLogicLayer.getOrganization(organizationId);

    if (projectRepository.findByOrganizationIdAndName(organization.getId(), name) != null) {
      throw new ProjectAlreadyExistsException();
    }

    Project project = Project.builder()
        .id(UUID.randomUUID())
        .name(name)
        .description(description)
        .active(active)
        .startedAt(startedAt)
        .organization(organization)
        .build();

    projectRepository.save(project);

    return project;
  }

  @Override
  public void deleteProject(UUID id) {
    getProject(id);
    projectRepository.deleteById(id);
  }

  @Override
  public void updateProject(UUID id, String name, String description, Long startedAt, Boolean active) {
    Project project = getProject(id);
    updateField(name, project::setName);
    updateField(description, project::setDescription);
    updateField(startedAt, project::setStartedAt);
    updateField(active, project::setActive);

    projectRepository.save(project);
  }

}
