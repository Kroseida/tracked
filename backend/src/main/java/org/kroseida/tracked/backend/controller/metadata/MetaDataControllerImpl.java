package org.kroseida.tracked.backend.controller.metadata;

import org.kroseida.tracked.backend.logic.metadata.MetaDataLogicLayer;
import org.kroseida.tracked.backend.persistance.metadata.model.ApplicationStatus;
import org.kroseida.tracked.backend.persistance.metadata.model.MetaData;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.kroseida.tracked.backend.util.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MetaDataControllerImpl implements MetaDataController {

  private MetaDataLogicLayer metaDataLogicLayer;
  private BuildProperties properties;

  @Autowired
  private MetaDataControllerImpl(MetaDataLogicLayer metaDataLogicLayer, BuildProperties properties) {
    this.metaDataLogicLayer = metaDataLogicLayer;
    this.properties = properties;
  }

  @Override
  public ResponseEntity<ResponseData<Map<String, Object>>> getMetaData() {
    return ResponseUtils.handle(
        () -> {
          Map<String, Object> metaData = new HashMap<>();
          metaData.put(MetaData.VERSION, properties.getVersion());
          for (MetaData entry : metaDataLogicLayer.getApplicationMetaData()) {
            metaData.put(entry.getName(), entry.getData());
          }
          return metaData;
        }
    );
  }

  @Override
  public ResponseEntity<ResponseData<Boolean>> lockInstallation() {
      return ResponseUtils.handle(() -> {
      // Now we need user context to do something installation related.
      metaDataLogicLayer.setMetaData(MetaData.STATUS, ApplicationStatus.OPERATIONAL.toString());
      return true;
    });
  }

}
