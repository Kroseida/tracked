package org.kroseida.tracked.backend.logic.user;

import org.kroseida.tracked.backend.logic.user.execption.EmailTakenException;
import org.kroseida.tracked.backend.logic.user.execption.UserAlreadyExistsException;
import org.kroseida.tracked.backend.persistance.authentication.AuthenticationRepository;
import org.kroseida.tracked.backend.persistance.authentication.model.Authentication;
import org.kroseida.tracked.backend.persistance.user.UserRepostiory;
import org.kroseida.tracked.backend.persistance.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class UserLogicLayerImpl implements UserLogicLayer {

  private final UserRepostiory userRepostiory;
  private final AuthenticationRepository authenticationRepository;

  @Autowired
  public UserLogicLayerImpl(UserRepostiory userRepostiory, AuthenticationRepository authenticationRepository) {
    this.userRepostiory = userRepostiory;
    this.authenticationRepository = authenticationRepository;
  }

  @Override
  public User createUser(String username, String email, String firstName, String lastName) {
    if (getUser(username) != null) {
      throw new UserAlreadyExistsException();
    }
    if (getUserByEmail(email) != null) {
      throw new EmailTakenException();
    }
    User user = User.builder()
        .id(UUID.randomUUID())
        .username(username)
        .email(email)
        .firstName(firstName)
        .lastName(lastName)
        .build();

    userRepostiory.save(user);
    return user;
  }

  @Override
  public User getUser(String username) {
    return userRepostiory.findByUsername(username);
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepostiory.findByEmail(email);
  }

  @Override
  public User getUser(UUID userId) {
    return userRepostiory.findById(userId).orElse(null);
  }

  @Override
  public User getUserBySession(String token) {
    Authentication authentication = authenticationRepository.findByDataLike(token);
    if (authentication == null) {
      return null;
    }
    if (authentication.authenticate(Map.of("token", token, "authenticatedAt", System.currentTimeMillis() + ""))) {
      return authentication.getUser();
    }
    return null;
  }

  @Override
  public void save(User user) {
    userRepostiory.save(user);
  }

}
