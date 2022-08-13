package org.kroseida.tracked.backend.logic.metadata;

import org.kroseida.tracked.backend.persistance.metadata.MetaDataRepository;
import org.kroseida.tracked.backend.persistance.metadata.model.MetaData;
import org.kroseida.tracked.backend.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MetaDataLogicLayerImpl implements MetaDataLogicLayer {

  private final MetaDataRepository metaDataRepository;

  @Autowired
  public MetaDataLogicLayerImpl(MetaDataRepository metaDataRepository) {
    this.metaDataRepository = metaDataRepository;
  }

  @Override
  public List<MetaData> getApplicationMetaData() {
    return CollectionHelper.listFromIterable(metaDataRepository.findAll());
  }

  @Override
  public MetaData getMetaData(String name) {
    return metaDataRepository.findByName(name);
  }

  @Override
  public void setMetaData(String name, String data) {
    MetaData metaData = metaDataRepository.findByName(name);
    if (metaData == null) {
      metaData = MetaData.builder()
          .id(UUID.randomUUID())
          .name(name)
          .data(data)
          .build();
    } else {
      metaData = MetaData.builder()
          .id(metaData.getId())
          .name(name)
          .data(data)
          .build();
    }
    metaDataRepository.save(metaData);
  }

}
