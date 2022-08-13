package org.kroseida.tracked.backend.logic.organization;

import org.kroseida.tracked.backend.logic.organization.exception.OrganizationAlreadyExistsException;
import org.kroseida.tracked.backend.persistance.organization.OrganizationRepository;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class OrganizationLogicLayerImpl implements OrganizationLogicLayer {

  private OrganizationRepository organizationRepository;

  @Autowired
  public OrganizationLogicLayerImpl(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }

  @Override
  public Organization createOrganization(String name) {
    Organization organization = Organization.builder()
        .id(UUID.randomUUID())
        .name(name)
        .projects(new ArrayList<>())
        .reports(new ArrayList<>())
        .build();

    if (organizationRepository.findByName(name) != null) {
      throw new OrganizationAlreadyExistsException();
    }

    organizationRepository.save(organization);

    return organization;
  }

}
