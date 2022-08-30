package org.kroseida.tracked.backend.util.crypto;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class CryptoUtils {

  private static final String APP_SALT = "j.ECFTgWhN2wA";
  private static final Random RANDOM = new SecureRandom();
  private static final Base64.Encoder BASE_64_ENCODER = Base64.getUrlEncoder(); //threadsafe
  private static final int ITERATIONS = 20000;
  private static final int KEY_LENGTH = 512;

  private CryptoUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * This method generates a random salt.
   *
   * @return a 64 bytes random salt
   */
  public static String nextSalt() {
    byte[] salt = new byte[64];
    RANDOM.nextBytes(salt);
    return new String(salt);
  }

  public static String nextToken() {
    byte[] randomBytes = new byte[32];
    RANDOM.nextBytes(randomBytes);
    return BASE_64_ENCODER.encodeToString(randomBytes);
  }

  /**
   * This method will generate a hash for the given password and salt combined with the app salt.
   * </p>
   * The salt is used to make the hash more secure.
   * The app salt is used to force attackers to get knowledge of the application.
   *
   * @param password the password to be hashed
   * @param salt     the salt to be used in the hash
   * @return the hashed password with a pinch of salt
   */
  public static String hash(char[] password, String salt) {
    PBEKeySpec spec = new PBEKeySpec(
        password,
        (salt + APP_SALT).getBytes(StandardCharsets.UTF_8),
        ITERATIONS,
        KEY_LENGTH
    );

    Arrays.fill(password, Character.MIN_VALUE);
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      return new String(skf.generateSecret(spec).getEncoded());
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    } finally {
      spec.clearPassword();
    }
  }

  /**
   * Returns true if the given password and salt are a match for the given hash.
   *
   * @param password     the password to be checked
   * @param salt         the salt used to hash the password
   * @param expectedHash the expected hashed value of the password
   * @return true if the given password and salt are a match for the given hash
   */
  public static boolean validate(char[] password, String salt, String expectedHash) {
    String passwordHash = hash(password, salt);
    Arrays.fill(password, Character.MIN_VALUE);
    return passwordHash.equals(expectedHash);
  }

}
