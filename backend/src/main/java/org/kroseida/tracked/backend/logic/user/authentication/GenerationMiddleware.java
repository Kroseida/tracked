package org.kroseida.tracked.backend.logic.user.authentication;

import org.kroseida.tracked.backend.persistance.authentication.model.AuthenticationType;
import org.kroseida.tracked.backend.persistance.user.model.User;

import java.util.Map;

public interface GenerationMiddleware {

  Map<AuthenticationType, GenerationMiddleware> MIDDLEWARE = Map.of(
      AuthenticationType.USERNAME_PASSWORD, new UsernamePasswordGenerationMiddleware(),
      AuthenticationType.SESSION_TOKEN, new SessionTokenGenerationMiddleware()
  );

  void process(User user, Map<String, String> data);

}
