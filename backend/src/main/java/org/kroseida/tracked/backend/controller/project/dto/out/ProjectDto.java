package org.kroseida.tracked.backend.controller.project.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kroseida.tracked.backend.controller.organization.dto.out.OrganizationIdContainerDto;
import org.kroseida.tracked.backend.util.dto.Dto;

import java.util.UUID;

/**
 * This DTO will be sent from the server to the client.
 * <p>
 * This DTO is used to represent a project.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto implements Dto {

  private UUID id;
  private String name;
  private String description;
  private boolean active;
  private String startDate;
  private String endDate;
  private OrganizationIdContainerDto organization;

}
