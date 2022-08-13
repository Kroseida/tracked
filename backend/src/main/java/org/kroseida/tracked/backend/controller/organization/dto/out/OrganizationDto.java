package org.kroseida.tracked.backend.controller.organization.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kroseida.tracked.backend.util.dto.Dto;

import java.util.UUID;

/**
 * This DTO will be sent from the server to the client.
 * <p>
 * This DTO is used to represent a organization.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto implements Dto {

  private UUID id;
  private String name;

}
