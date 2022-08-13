package org.kroseida.tracked.backend.persistance.project;

import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProjectRepository extends CrudRepository<Project, UUID> {

}
