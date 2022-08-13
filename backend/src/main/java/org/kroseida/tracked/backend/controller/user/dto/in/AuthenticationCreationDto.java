package org.kroseida.tracked.backend.controller.user.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kroseida.tracked.backend.persistance.authentication.model.AuthenticationType;
import org.kroseida.tracked.backend.util.dto.Dto;

import java.util.Map;
import java.util.UUID;

/**
 * This DTO will be sent from the client to the server.
 * <p>
 * This DTO is used to create a new authentication option.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationCreationDto implements Dto {

  private UUID userId;
  private AuthenticationType type;
  private Map<String, String> data;

}
