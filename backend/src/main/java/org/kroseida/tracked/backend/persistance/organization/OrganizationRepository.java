package org.kroseida.tracked.backend.persistance.organization;

import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends CrudRepository<Organization, UUID> {

  Organization findByName(String name);

}
