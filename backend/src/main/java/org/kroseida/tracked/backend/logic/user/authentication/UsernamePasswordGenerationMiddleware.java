package org.kroseida.tracked.backend.logic.user.authentication;

import org.kroseida.tracked.backend.logic.user.execption.InvalidAuthenticationDataException;
import org.kroseida.tracked.backend.persistance.user.model.User;
import org.kroseida.tracked.backend.util.crypto.CryptoUtils;

import java.util.Map;

public class UsernamePasswordGenerationMiddleware implements GenerationMiddleware {

  public static final String PASSWORD_FIELD = "password";
  public static final String HASH_FIELD = "hash";
  public static final String SALT_FIELD = "salt";

  @Override
  public void process(User user, Map<String, String> data) {
    if (data == null || !data.containsKey(PASSWORD_FIELD) || data.get(PASSWORD_FIELD) == null) {
      throw new InvalidAuthenticationDataException(
          "Invalid authentication data provided. Missing field '" + PASSWORD_FIELD + "'."
      );
    }
    String salt = CryptoUtils.nextSalt();
    String hash = CryptoUtils.hash(data.get(PASSWORD_FIELD).toCharArray(), salt);

    data.remove(PASSWORD_FIELD);
    data.put(HASH_FIELD, hash);
    data.put(SALT_FIELD, salt);
  }

}
