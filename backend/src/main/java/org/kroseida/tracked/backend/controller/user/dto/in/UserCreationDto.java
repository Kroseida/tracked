package org.kroseida.tracked.backend.controller.user.dto.in;

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
public class UserCreationDto implements Dto {

  private String username;
  private String email;
  private String firstName;
  private String lastName;

}
