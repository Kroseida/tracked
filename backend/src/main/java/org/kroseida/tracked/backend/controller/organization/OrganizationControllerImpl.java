package org.kroseida.tracked.backend.controller.organization;

import org.kroseida.tracked.backend.controller.organization.dto.in.OrganizationCreationDto;
import org.kroseida.tracked.backend.controller.organization.dto.out.OrganizationDto;
import org.kroseida.tracked.backend.logic.organization.OrganizationLogicLayer;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.kroseida.tracked.backend.util.dto.DtoUtils;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.kroseida.tracked.backend.util.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class OrganizationControllerImpl implements OrganizationController {

  private final OrganizationLogicLayer organizationLogicLayer;

  @Autowired
  public OrganizationControllerImpl(OrganizationLogicLayer organizationLogicLayer) {
    this.organizationLogicLayer = organizationLogicLayer;
  }

  @Override
  public ResponseEntity<ResponseData<OrganizationDto>> create(OrganizationCreationDto creation) {
    return ResponseUtils.handle(() -> {
      Organization organization = organizationLogicLayer.createOrganization(
          creation.getName(),
          creation.getDescription(),
          creation.isActive()
      );
      return DtoUtils.dto(organization, OrganizationDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<List<OrganizationDto>>> list() {
    return ResponseUtils.handle(() -> {
      List<Organization> organization = organizationLogicLayer.getOrganizations();
      return DtoUtils.dtoList(organization, OrganizationDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Boolean>> delete(String id) {
    return ResponseUtils.handle(() -> {
      organizationLogicLayer.deleteOrganization(UUID.fromString(id));
      return true;
    });
  }

  @Override
  public ResponseEntity<ResponseData<OrganizationDto>> get(String id) {
    return ResponseUtils.handle(() -> {
      Organization organization = organizationLogicLayer.getOrganization(UUID.fromString(id));
      return DtoUtils.dto(organization, OrganizationDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Boolean>> patch(String id, OrganizationCreationDto creation) {
    return ResponseUtils.handle(() -> {
      organizationLogicLayer.updateOrganization(
          UUID.fromString(id),
          creation.getName(),
          creation.getDescription(),
          creation.isActive()
      );
      return true;
    });
  }

}
