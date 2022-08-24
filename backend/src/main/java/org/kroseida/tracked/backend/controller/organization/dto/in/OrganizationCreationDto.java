package org.kroseida.tracked.backend.controller.organization.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kroseida.tracked.backend.util.dto.Dto;

/**
 * This DTO will be sent from the client to the server.
 * <p>
 * This DTO is used to create a new user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreationDto implements Dto {

  private String name;
  private String description;
  private boolean active;

}
