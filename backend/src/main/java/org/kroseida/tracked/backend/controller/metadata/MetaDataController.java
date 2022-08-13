package org.kroseida.tracked.backend.controller.metadata;

import org.kroseida.tracked.backend.util.response.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/metadata/")
public interface MetaDataController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  ResponseEntity<ResponseData<Map<String, Object>>> getMetaData();

  @RequestMapping(value = "/install/", method = RequestMethod.POST)
  @ResponseBody
  ResponseEntity<ResponseData<Boolean>> lockInstallation();

}
