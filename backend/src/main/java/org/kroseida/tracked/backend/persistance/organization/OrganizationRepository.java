package org.kroseida.tracked.backend.persistance.organization;

import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrganizationRepository extends CrudRepository<Organization, UUID> {

  Organization findByName(String name);

  @Query("select o from Organization o where o.name like %?1% or o.description like %?1% or hex(o.id) like %?1%")
  Page<Organization> findAll(Pageable pageable, String filter);

}
