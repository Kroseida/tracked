package org.kroseida.tracked.backend.logic.organization;

import org.kroseida.tracked.backend.logic.organization.exception.OrganizationAlreadyExistsException;
import org.kroseida.tracked.backend.logic.organization.exception.OrganizationNotFoundException;
import org.kroseida.tracked.backend.persistance.organization.OrganizationRepository;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.kroseida.tracked.backend.util.logic.LogicUtils.updateField;

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
  public Page<Organization> getOrganizations(Pageable pageable, String filter) {
    return organizationRepository.findAll(pageable, filter);
  }

  @Override
  public void deleteOrganization(UUID id) {
    getOrganization(id); // Validate organization exists
    organizationRepository.deleteById(id);
  }

  @Override
  public Organization getOrganization(UUID id) {
    return organizationRepository.findById(id)
        .orElseThrow(() -> new OrganizationNotFoundException());
  }

  @Override
  public void updateOrganization(UUID id, String name, String description, Boolean active) {
    Organization organization = getOrganization(id);
    updateField(name, organization::setName);
    updateField(description, organization::setDescription);
    updateField(active, organization::setActive);

    organizationRepository.save(organization);
  }

}
