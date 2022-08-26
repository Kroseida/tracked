package org.kroseida.tracked.backend.controller.project;

import org.kroseida.tracked.backend.controller.organization.dto.in.OrganizationCreationDto;
import org.kroseida.tracked.backend.controller.project.dto.in.ProjectCreationDto;
import org.kroseida.tracked.backend.controller.project.dto.out.ProjectDto;
import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/project/")
public interface ProjectController {

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseBody
  ResponseEntity<ResponseData<ProjectDto>> create(
      @RequestBody ProjectCreationDto creation
  );

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  ResponseEntity<ResponseData<Page<ProjectDto>>> list(
      @RequestParam(value = "organizationId", required = false, defaultValue = "*") String organizationId,
      @RequestParam(value = "filter", required = false, defaultValue = "") String filter,
      Pageable pageable
  );

  @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
  @ResponseBody
  ResponseEntity<ResponseData<Boolean>> delete(@PathVariable("id") String id);

  @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
  @ResponseBody
  ResponseEntity<ResponseData<ProjectDto>> get(@PathVariable("id") String id);

  @RequestMapping(value = "/{id}/", method = RequestMethod.PATCH)
  @ResponseBody
  ResponseEntity<ResponseData<Boolean>> patch(
      @PathVariable("id") String id,
      @RequestBody ProjectCreationDto creation
  );

}
