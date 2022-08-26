package org.kroseida.tracked.backend.persistance.project;

import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProjectRepository extends CrudRepository<Project, UUID> {

  @Query("select p from Project p where (?1 is null OR p.organization.id = ?1) and (p.name like %?2% or p.description like %?2% or hex(p.id) like %?2%)")
  Page<Project> findAll(UUID organizationId, Pageable pageable, String filter);

  Project findByOrganizationIdAndName(UUID organizationId, String name);

}
