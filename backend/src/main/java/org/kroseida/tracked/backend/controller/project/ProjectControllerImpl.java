package org.kroseida.tracked.backend.controller.project;

import org.kroseida.tracked.backend.controller.organization.dto.in.OrganizationCreationDto;
import org.kroseida.tracked.backend.controller.project.dto.in.ProjectCreationDto;
import org.kroseida.tracked.backend.controller.project.dto.out.ProjectDto;
import org.kroseida.tracked.backend.logic.project.ProjectLogicLayer;
import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.kroseida.tracked.backend.util.dto.DtoUtils;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.kroseida.tracked.backend.util.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ProjectControllerImpl implements ProjectController {
  
  private final ProjectLogicLayer projectLogicLayer;

  @Autowired
  public ProjectControllerImpl(ProjectLogicLayer projectLogicLayer) {
    this.projectLogicLayer = projectLogicLayer;
  }

  @Override
  public ResponseEntity<ResponseData<ProjectDto>> create(ProjectCreationDto creation) {
    return ResponseUtils.handle(() -> {
      Project project = this.projectLogicLayer.createProject(
          creation.getName(),
          creation.getDescription(),
          creation.isActive(),
          creation.getStartedAt(),
          creation.getOrganizationId()
      );
      return DtoUtils.dto(project, ProjectDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Page<ProjectDto>>> list(String organizationId, String filter, Pageable pageable) {
    return ResponseUtils.handle(() -> {
      UUID organizationFilter = null;
      if (!organizationId.equals("*")) {
        organizationFilter = UUID.fromString(organizationId);
      }
      Page<Project> projects = projectLogicLayer.getProjects(organizationFilter, filter, pageable);

      return DtoUtils.dtoPage(projects, ProjectDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Boolean>> delete(String id) {
    return ResponseUtils.handle(() -> {
      projectLogicLayer.deleteProject(UUID.fromString(id));
      return true;
    });
  }

  @Override
  public ResponseEntity<ResponseData<ProjectDto>> get(String id) {
    return ResponseUtils.handle(() -> {
      Project project = projectLogicLayer.getProject(UUID.fromString(id));
      return DtoUtils.dto(project, ProjectDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Boolean>> patch(String id, ProjectCreationDto creation) {
    return ResponseUtils.handle(() -> {
      projectLogicLayer.updateProject(
          UUID.fromString(id),
          creation.getName(),
          creation.getDescription(),
          creation.getStartedAt(),
          creation.isActive()
      );
      return true;
    });
  }

}
