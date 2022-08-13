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
      Organization organization = organizationLogicLayer.createOrganization(creation.getName());
      return DtoUtils.dto(organization, OrganizationDto.class);
    });
  }

}
