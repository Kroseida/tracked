package org.kroseida.tracked.backend.controller.organization;

import org.kroseida.tracked.backend.controller.organization.dto.in.OrganizationCreationDto;
import org.kroseida.tracked.backend.controller.organization.dto.out.OrganizationDto;
import org.kroseida.tracked.backend.controller.user.dto.out.UserDto;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/organization/")
public interface OrganizationController {


  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseBody
  ResponseEntity<ResponseData<OrganizationDto>> create(@RequestBody OrganizationCreationDto creation);

}
