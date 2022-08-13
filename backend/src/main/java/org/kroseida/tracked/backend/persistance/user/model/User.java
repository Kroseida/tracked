package org.kroseida.tracked.backend.persistance.user.model;

import lombok.*;
import org.kroseida.tracked.backend.logic.user.authentication.GenerationMiddleware;
import org.kroseida.tracked.backend.persistance.authentication.model.Authentication;
import org.kroseida.tracked.backend.persistance.authentication.model.AuthenticationType;
import org.kroseida.tracked.backend.persistance.report.model.Report;
import org.kroseida.tracked.backend.util.crypto.CryptoUtils;

import javax.persistence.*;
import java.util.*;

/**
 * This class represents a user stored in the database.
 * <p>
 * The user is identified by its username or id.
 * <p>
 * The username is the unique external identifier of the user in purpose of authentication.
 * (e.g Username/Password Authentication)
 * <p>
 * Users can have multiple authentication options.
 */
@Table
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  /*+
   * This is the list of authentication options for the user.
   */
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
  private List<Authentication> authentications;
  /**
   * This is the list of reports for the user.
   */
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
  private List<Report> reports;

  /**
   * This method will create a new authentication option for the user.
   *
   * @param authentication The authentication option to add.
   */
  public void addAuthentication(Authentication authentication) {
    // This is to add some security stuff to the authentication, like a salt and a hash.
    GenerationMiddleware.MIDDLEWARE.get(authentication.getType())
        .process(this, authentication.getData());

    if (this.authentications == null) {
      this.authentications = new ArrayList<>();
    }

    this.authentications.add(authentication);
  }

  /**
   * This method will authenticate the user with the given authentication option.
   * After the authentication is successful, a session will be created and returned.
   *
   * @param type The type of the authentication option.
   * @param data The data of the authentication option.
   * @return A new authentication session if the authentication was successful, otherwise null.
   */
  public Authentication authenticate(AuthenticationType type, Map<String, String> data) {
    boolean authenticated = false;
    for (Authentication authentication : authentications) {
      if (!authentication.getType().equals(type)) {
        continue;
      }
      if (!authentication.authenticate(data)) {
        continue;
      }
      authenticated = true;
    }
    if (!authenticated) {
      return null; // Authentication failed.
    }
    // Create a new authentication session.
    Authentication authentication = Authentication.builder()
        .id(UUID.randomUUID())
        .user(this)
        .data(Map.of(
            "token", CryptoUtils.nextToken(),
            "createdAt", String.valueOf(new Date().getTime())
        ))
        .type(AuthenticationType.SESSION_TOKEN)
        .build();

    addAuthentication(authentication);

    return authentication;
  }

}
