package org.kroseida.tracked.backend.persistance.authentication.model;

import lombok.*;
import org.kroseida.tracked.backend.logic.user.authentication.UsernamePasswordGenerationMiddleware;
import org.kroseida.tracked.backend.persistance.converter.MapConverter;
import org.kroseida.tracked.backend.persistance.user.model.User;
import org.kroseida.tracked.backend.util.crypto.CryptoUtils;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * This class represents authentication options for a user.
 */
@Table
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Authentication {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;
  private AuthenticationType type;
  @Convert(converter = MapConverter.class)
  private Map<String, String> data;
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  public boolean authenticate(Map<String, String> data) {
    switch (type) {
      case USERNAME_PASSWORD:
        return handlePasswordAuthentication(data);
      case SESSION_TOKEN:
        return handleSessionTokenAuthentication(data);
      default:
        return false;
    }
  }

  private boolean handlePasswordAuthentication(Map<String, String> data) {
    return CryptoUtils.validate(
        data.get(UsernamePasswordGenerationMiddleware.PASSWORD_FIELD).toCharArray(),
        this.data.get(UsernamePasswordGenerationMiddleware.SALT_FIELD),
        this.data.get(UsernamePasswordGenerationMiddleware.HASH_FIELD)
    );
  }

  private boolean handleSessionTokenAuthentication(Map<String, String> data) {
    if (!data.containsKey("token")) {
      throw new IllegalArgumentException("The data must contain a token field");
    }
    if (!data.get("token").equals(this.data.get("token"))) {
      return false;
    }
    if (!data.containsKey("authenticatedAt")) {
      throw new IllegalArgumentException("The data must contain a authenticatedAt field");
    }
    long createdAt = Long.valueOf(this.data.get("createdAt"));
    long authenticatedAt = Long.valueOf(data.get("authenticatedAt"));
    if (createdAt + TimeUnit.DAYS.toMillis(3) < authenticatedAt) {
      return false;
    }

    return true;
  }

}
