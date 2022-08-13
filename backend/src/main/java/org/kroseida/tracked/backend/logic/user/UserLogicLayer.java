package org.kroseida.tracked.backend.logic.user;

import org.kroseida.tracked.backend.logic.user.execption.UserAlreadyExistsException;
import org.kroseida.tracked.backend.logic.user.execption.EmailTakenException;
import org.kroseida.tracked.backend.persistance.user.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UserLogicLayer {

  /**
   * This method will create a new user.
   * <p>
   * If the user already exists, it will throw an {@link UserAlreadyExistsException}.
   * <p>
   * If the email is already taken, it will throw an {@link EmailTakenException}.
   *
   * @param username  The username of the user.
   * @param email     The email of the user.
   * @param firstName The first name of the user.
   * @param lastName  The last name of the user.
   * @return The user that was created.
   */
  User createUser(String username, String email, String firstName, String lastName);

  /**
   * This method will return the user with the given username.
   * If the user does not exist, it will return null.
   *
   * @param username The username of the user.
   * @return The user with the given username.
   */
  User getUser(String username);

  /**
   * This method will return the user with the given email.
   *
   * @param email The email of the user.
   * @return The user with the given email.
   */
  User getUserByEmail(String email);

  /**
   * This method will return the user with the given id.
   *
   * @param userId the id of the user.
   * @return The user with the given id.
   */
  User getUser(UUID userId);

  /**
   * This method will resolve the user by an session.
   *
   * @param session The session of the user.
   * @return The user with the given session.
   */
  User getUserBySession(String session);

  /**
   * This method will save the given user.
   *
   * @param user The user to save.
   */
  void save(User user);

}
