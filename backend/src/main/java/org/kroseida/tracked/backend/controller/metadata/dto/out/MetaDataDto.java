package org.kroseida.tracked.backend.controller.metadata.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kroseida.tracked.backend.util.dto.Dto;

/**
 * This DTO will be sent from the server to the client.
 * <p>
 * This DTO is used to represent one metadata entry.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataDto implements Dto {

  private String name;
  private String data;

}
