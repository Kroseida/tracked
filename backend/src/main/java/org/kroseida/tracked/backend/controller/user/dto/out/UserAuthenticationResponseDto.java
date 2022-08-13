package org.kroseida.tracked.backend.controller.user.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kroseida.tracked.backend.util.dto.Dto;

import java.util.UUID;

/**
 * This DTO will be sent from the server to the client.
 * <p>
 * This DTO is used to represent a session of a user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationResponseDto implements Dto {

  private UUID userId;
  private String session;

}
