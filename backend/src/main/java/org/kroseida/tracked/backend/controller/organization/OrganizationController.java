package org.kroseida.tracked.backend.controller.organization;

import org.kroseida.tracked.backend.controller.organization.dto.in.OrganizationCreationDto;
import org.kroseida.tracked.backend.controller.organization.dto.out.OrganizationDto;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/organization/")
public interface OrganizationController {

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseBody
  ResponseEntity<ResponseData<OrganizationDto>> create(@RequestBody OrganizationCreationDto creation);

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  ResponseEntity<ResponseData<List<OrganizationDto>>> list();

  @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
  @ResponseBody
  ResponseEntity<ResponseData<Boolean>> delete(@PathVariable("id") String id);

  @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
  @ResponseBody
  ResponseEntity<ResponseData<OrganizationDto>> get(@PathVariable("id") String id);

  @RequestMapping(value = "/{id}/", method = RequestMethod.PATCH)
  @ResponseBody
  ResponseEntity<ResponseData<Boolean>> patch(
      @PathVariable("id") String id,
      @RequestBody OrganizationCreationDto creation
  );

}
