package org.kroseida.tracked.backend.organization;

import org.junit.jupiter.api.Test;
import org.kroseida.tracked.backend.logic.organization.OrganizationLogicLayer;
import org.kroseida.tracked.backend.logic.organization.OrganizationLogicLayerImpl;
import org.kroseida.tracked.backend.logic.organization.exception.OrganizationAlreadyExistsException;
import org.kroseida.tracked.backend.persistance.organization.OrganizationRepository;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationTest {

  @Test
  public void update_organization() {
    OrganizationRepository organizationRepository = Mockito.mock(OrganizationRepository.class);
    OrganizationLogicLayer organizationLogicLayer = newOrganizationLogicLayer(organizationRepository);

    UUID id = UUID.randomUUID();
    String name = "test-organization";
    String description = "test-description";
    boolean active = true;

    Mockito.when(organizationRepository.findById(id))
        .thenReturn(Optional.of(Organization.builder()
            .id(id)
            .name(name)
            .description(description)
            .active(active)
            .projects(new ArrayList<>())
            .reports(new ArrayList<>())
            .activities(new ArrayList<>())
            .users(new ArrayList<>())
            .build()));

    List<Organization> updates = new ArrayList<>();
    Mockito.when(organizationRepository.save(Mockito.any(Organization.class)))
        .thenAnswer(invocation -> {
          updates.add(invocation.getArgument(0));
          return invocation.getArgument(0);
        });

    // Name
    {
      String newName = "new-test-organization";
      organizationLogicLayer.updateOrganization(id, newName, null, null);
      Organization organization = updates.get(0);
      assertEquals(newName, organization.getName());
      assertEquals(description, organization.getDescription());
      assertEquals(id, organization.getId());
      assertEquals(active, organization.isActive());
      organizationLogicLayer.updateOrganization(id, name, null, null);
      updates.clear();
    }

    // Description
    {
      String newDescription = "new-test-description";
      organizationLogicLayer.updateOrganization(id, null, newDescription, null);
      Organization organization = updates.get(0);
      assertEquals(name, organization.getName());
      assertEquals(newDescription, organization.getDescription());
      assertEquals(id, organization.getId());
      assertEquals(active, organization.isActive());
      organizationLogicLayer.updateOrganization(id, null, description, null);

      updates.clear();
    }

    // Active
    {
      boolean newActive = false;
      organizationLogicLayer.updateOrganization(id, null, description, newActive);
      Organization organization = updates.get(0);
      assertEquals(name, organization.getName());
      assertEquals(description, organization.getDescription());
      assertEquals(id, organization.getId());
      assertEquals(newActive, organization.isActive());
      organizationLogicLayer.updateOrganization(id, null, null, active);

      updates.clear();
    }

    // Name and description
    {
      String newName = "new-test-organization";
      String newDescription = "new-test-description";
      organizationLogicLayer.updateOrganization(id, newName, newDescription, null);
      Organization organization = updates.get(0);
      assertEquals(newName, organization.getName());
      assertEquals(newDescription, organization.getDescription());
      assertEquals(id, organization.getId());
      assertEquals(active, organization.isActive());
      organizationLogicLayer.updateOrganization(id, name, description, null);

      updates.clear();
    }
  }

  @Test
  public void create_organization() {
    OrganizationRepository organizationRepository = Mockito.mock(OrganizationRepository.class);
    OrganizationLogicLayer organizationLogicLayer = newOrganizationLogicLayer(organizationRepository);

    List<Organization> createdOrganization = new ArrayList<>();
    Mockito.when(organizationRepository.save(Mockito.any(Organization.class)))
        .thenAnswer(invocation -> {
          createdOrganization.add(invocation.getArgument(0));
          return invocation.getArgument(0);
        });

    organizationLogicLayer.createOrganization("test-organization", "test-description", true);
    organizationLogicLayer.createOrganization("test-organization2", "test-description", false);

    assertEquals(2, createdOrganization.size());
  }

  @Test
  public void create_organization_already_existing() {
    OrganizationRepository organizationRepository = Mockito.mock(OrganizationRepository.class);
    OrganizationLogicLayer organizationLogicLayer = newOrganizationLogicLayer(organizationRepository);

    String name = "test-organization";

    Mockito.when(organizationRepository.findByName(name))
        .thenReturn(Organization.builder()
            .id(UUID.randomUUID())
            .name(name)
            .description("test-description")
            .active(false) // not important if it is active or not, just that it exists
            .projects(new ArrayList<>())
            .reports(new ArrayList<>())
            .activities(new ArrayList<>())
            .users(new ArrayList<>())
            .build());

    Exception exception = assertThrows(OrganizationAlreadyExistsException.class, () -> {
      organizationLogicLayer.createOrganization(name, "test-description", true);
    });

    assertNotNull(exception);
  }

  public OrganizationLogicLayer newOrganizationLogicLayer(OrganizationRepository organizationRepository) {
    return new OrganizationLogicLayerImpl(organizationRepository);
  }

}
