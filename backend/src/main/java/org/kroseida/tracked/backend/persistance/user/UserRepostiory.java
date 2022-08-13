package org.kroseida.tracked.backend.persistance.user;

import org.kroseida.tracked.backend.persistance.user.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepostiory extends CrudRepository<User, UUID> {

  /**
   * Finds a user by its username.
   *
   * @param username the username of the user to find.
   * @return the user with the given username or null if no user with the given username exists.
   */
  User findByUsername(String username);

  /**
   * Finds a user by its email.
   *
   * @param email the email of the user to find.
   * @return the user with the given email or null if no user with the given email exists.
   */
  User findByEmail(String email);

}
