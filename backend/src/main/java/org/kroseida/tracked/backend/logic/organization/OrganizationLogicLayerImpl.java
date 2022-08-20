package org.kroseida.tracked.backend.logic.organization;

import org.kroseida.tracked.backend.logic.organization.exception.OrganizationAlreadyExistsException;
import org.kroseida.tracked.backend.persistance.organization.OrganizationRepository;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OrganizationLogicLayerImpl implements OrganizationLogicLayer {

  private OrganizationRepository organizationRepository;

  @Autowired
  public OrganizationLogicLayerImpl(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }

  @Override
  public Organization createOrganization(String name, String description, boolean active) {
    Organization organization = Organization.builder()
        .id(UUID.randomUUID())
        .name(name)
        .description(description)
        .active(active)
        .projects(new ArrayList<>())
        .reports(new ArrayList<>())
        .build();

    if (organizationRepository.findByName(name) != null) {
      throw new OrganizationAlreadyExistsException();
    }

    organizationRepository.save(organization);

    return organization;
  }

  @Override
  public List<Organization> getOrganizations() {
    List<Organization> organizations = new ArrayList<>();
    organizationRepository.findAll().forEach(organizations::add);
    return organizations;
  }

  @Override
  public void deleteOrganization(UUID id) {
    organizationRepository.deleteById(id);
  }

  @Override
  public Organization getOrganization(UUID id) {
    return organizationRepository.findById(id).get();
  }

}
