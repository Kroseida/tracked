package org.kroseida.tracked.backend.logic.metadata;

import org.kroseida.tracked.backend.persistance.metadata.model.MetaData;
import org.springframework.stereotype.Component;

import java.util.List;

public interface MetaDataLogicLayer {


  /**
   * Get all meta data for the application.
   * MetaData could be used to store application specific data like version, status etc.
   *
   * @return List of meta data
   */
  List<MetaData> getApplicationMetaData();

  /**
   * Get specific meta data by key.
   *
   * @param name key of the meta data
   * @return meta data
   */
  MetaData getMetaData(String name);

  /**
   *
   * @param statusMetaDataField
   * @param data
   */
  void setMetaData(String statusMetaDataField, String data);

}
