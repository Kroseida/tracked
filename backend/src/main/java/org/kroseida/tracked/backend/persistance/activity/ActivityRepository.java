package org.kroseida.tracked.backend.persistance.activity;

import org.kroseida.tracked.backend.persistance.activity.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ActivityRepository extends CrudRepository<Activity, UUID> {

  @Query("select a from Activity a where (?1 is null OR a.organization.id = ?1) and (a.name like %?2% or a.description like %?2% or hex(a.id) like %?2%)")
  Page<Activity> findAll(UUID organizationId, Pageable pageable, String filter);

  Activity findByOrganizationIdAndName(UUID organizationId, String name);

}
