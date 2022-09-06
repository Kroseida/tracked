package org.kroseida.tracked.backend.controller.activity;

import org.kroseida.tracked.backend.controller.activity.dto.in.ActivityCreationDto;
import org.kroseida.tracked.backend.controller.activity.dto.out.ActivityDto;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/activity/")
public interface ActivityController {

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseBody
  ResponseEntity<ResponseData<ActivityDto>> create(
      @RequestBody ActivityCreationDto creation
  );

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  ResponseEntity<ResponseData<Page<ActivityDto>>> list(
      @RequestParam(value = "organizationId", required = false, defaultValue = "*") String organizationId,
      @RequestParam(value = "filter", required = false, defaultValue = "") String filter,
      Pageable pageable
  );

  @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
  @ResponseBody
  ResponseEntity<ResponseData<Boolean>> delete(@PathVariable("id") String id);

  @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
  @ResponseBody
  ResponseEntity<ResponseData<ActivityDto>> get(@PathVariable("id") String id);

  @RequestMapping(value = "/{id}/", method = RequestMethod.PATCH)
  @ResponseBody
  ResponseEntity<ResponseData<Boolean>> patch(
      @PathVariable("id") String id,
      @RequestBody ActivityCreationDto creation
  );

}
