package org.kroseida.tracked.backend.controller.activity.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * This DTO will be sent from the client to the server.
 * <p>
 * This DTO is used to create a new activity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCreationDto {

  private String name;
  private String description;
  private boolean active;
  private UUID organizationId;

}
