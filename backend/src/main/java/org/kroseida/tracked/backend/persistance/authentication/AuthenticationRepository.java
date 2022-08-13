package org.kroseida.tracked.backend.persistance.authentication;

import org.kroseida.tracked.backend.persistance.authentication.model.Authentication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;
import java.util.UUID;

public interface AuthenticationRepository extends CrudRepository<Authentication, UUID> {

  /**
   * Finds a user by its session.
   *
   * @param token the session of the user to find.
   * @return the user with the given session or null if no user with the given session exists.
   */
  @Query("SELECT a FROM Authentication a WHERE function('JSON_EXTRACT', a.data, '$.token') = :token")
  Authentication findByDataLike(String token);

}
