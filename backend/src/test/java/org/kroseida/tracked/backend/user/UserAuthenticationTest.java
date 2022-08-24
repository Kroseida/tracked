package org.kroseida.tracked.backend.user;

import org.junit.jupiter.api.Test;
import org.kroseida.tracked.backend.logic.user.authentication.UsernamePasswordGenerationMiddleware;
import org.kroseida.tracked.backend.logic.user.execption.InvalidAuthenticationDataException;
import org.kroseida.tracked.backend.persistance.authentication.model.Authentication;
import org.kroseida.tracked.backend.persistance.authentication.model.AuthenticationType;
import org.kroseida.tracked.backend.persistance.user.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class UserAuthenticationTest {

  @Test
  public void missing_password_authentication() {
    User user = newUser();
    Exception exception = assertThrows(InvalidAuthenticationDataException.class, () -> {
      user.addAuthentication(Authentication.builder()
          .id(UUID.randomUUID())
          .user(user)
          .type(AuthenticationType.USERNAME_PASSWORD)
          .build());
    });

    assertTrue(exception.getMessage().contains(UsernamePasswordGenerationMiddleware.PASSWORD_FIELD));
  }

  @Test
  public void password_authentication() {
    String password = "AwopgML4%V!V98D+?hec^W0vHxk1})@Tbd?njpanK=,MVdLh"; // random password as example.
    User user = newUser();
    user.addAuthentication(Authentication.builder()
        .id(UUID.randomUUID())
        .user(user)
        .type(AuthenticationType.USERNAME_PASSWORD)
        .data(new HashMap<>(Map.of(UsernamePasswordGenerationMiddleware.PASSWORD_FIELD, password)))
        .build());

    Authentication authentication = user.authenticate(
        AuthenticationType.USERNAME_PASSWORD,
        new HashMap<>(Map.of(UsernamePasswordGenerationMiddleware.PASSWORD_FIELD, password))
    );

    assertNotNull(authentication);
  }

  @Test
  public void remove_password_from_authentication_security() {
    String password = "AwopgML4%V!V98D+?hec^W0vHxk1})@Tbd?njpanK=,MVdLh"; // random password as example.
    User user = newUser();
    user.addAuthentication(Authentication.builder()
        .id(UUID.randomUUID())
        .user(user)
        .type(AuthenticationType.USERNAME_PASSWORD)
        .data(new HashMap<>(Map.of(UsernamePasswordGenerationMiddleware.PASSWORD_FIELD, password)))
        .build());

    for (Authentication authentication : user.getAuthentications()) {
      assertFalse(authentication.toString().contains(password)); // ensure that the password is not in the authentication.
    }
  }

  @Test
  public void session_expiration_test_not_expired() {
    String token = "test_token_123";

    Authentication authentication = Authentication.builder()
        .id(UUID.randomUUID())
        .type(AuthenticationType.SESSION_TOKEN)
        .data(Map.of(
            "token", token,
            "createdAt", String.valueOf(System.currentTimeMillis())
        ))
        .build();

    boolean authenticated = authentication.authenticate(Map.of(
        "token", token,
        "authenticatedAt", String.valueOf(System.currentTimeMillis() + 5000)
    ));

    assertTrue(authenticated);
  }

  @Test
  public void session_expiration_test_expired() {
    String token = "test_token_123";

    Authentication authentication = Authentication.builder()
        .id(UUID.randomUUID())
        .type(AuthenticationType.SESSION_TOKEN)
        .data(Map.of(
            "token", token,
            "createdAt", String.valueOf(System.currentTimeMillis())
        ))
        .build();

    boolean authenticated = authentication.authenticate(Map.of(
        "token", token,
        "authenticatedAt", String.valueOf(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15))
    ));

    assertFalse(authenticated);
  }

  @Test
  public void session_expiration_test_missing_authenticated_at() {
    String token = "test_token_123";

    Authentication authentication = Authentication.builder()
        .id(UUID.randomUUID())
        .type(AuthenticationType.SESSION_TOKEN)
        .data(Map.of(
            "token", token,
            "createdAt", String.valueOf(System.currentTimeMillis())
        ))
        .build();
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      authentication.authenticate(Map.of(
          "token", token
      ));
    });

    assertTrue(exception.getMessage().contains("authenticatedAt"));
  }

  private User newUser() {
    return User.builder()
        .id(UUID.randomUUID())
        .username("John.Doe")
        .email("john.doe@example.org")
        .firstName("John")
        .lastName("Doe")
        .build();
  }
}
