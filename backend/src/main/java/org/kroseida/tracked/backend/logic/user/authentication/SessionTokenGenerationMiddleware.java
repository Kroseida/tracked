package org.kroseida.tracked.backend.logic.user.authentication;

import org.kroseida.tracked.backend.logic.user.execption.InvalidAuthenticationDataException;
import org.kroseida.tracked.backend.persistance.user.model.User;

import java.util.Map;

public class SessionTokenGenerationMiddleware implements GenerationMiddleware {

  public static String PASSWORD_FIELD = "password";

  @Override
  public void process(User user, Map<String, String> data) {

  }

}
