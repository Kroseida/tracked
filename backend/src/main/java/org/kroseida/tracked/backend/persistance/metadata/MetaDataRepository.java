package org.kroseida.tracked.backend.persistance.metadata;

import org.kroseida.tracked.backend.persistance.metadata.model.MetaData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MetaDataRepository extends CrudRepository<MetaData, UUID> {

  MetaData findByName(String name);

}
