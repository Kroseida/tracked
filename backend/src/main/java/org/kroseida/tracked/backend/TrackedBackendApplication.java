package org.kroseida.tracked.backend;

import org.kroseida.tracked.backend.logic.metadata.MetaDataLogicLayer;
import org.kroseida.tracked.backend.persistance.metadata.model.ApplicationStatus;
import org.kroseida.tracked.backend.persistance.metadata.model.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Component
public class TrackedBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(TrackedBackendApplication.class, args);
  }

  private MetaDataLogicLayer metaDataLogicLayer;

  @Autowired
  public TrackedBackendApplication(MetaDataLogicLayer metaDataLogicLayer) {
    this.metaDataLogicLayer = metaDataLogicLayer;
  }

  @PostConstruct
  public void init() {
    MetaData metaData = metaDataLogicLayer.getMetaData(MetaData.STATUS);
    if (metaData == null) {
      metaDataLogicLayer.setMetaData(MetaData.STATUS, ApplicationStatus.UNINSTALLED.toString());
    }
  }

}
