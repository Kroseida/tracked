package org.kroseida.tracked.backend.util.response;

import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;
import org.kroseida.tracked.backend.logic.exception.UnauthorizedException;

public interface ResponseRunnable<T> {

  /**
   * Call this method to handle the request. This method will be called by the Controller.
   *
   * @return The Object that will be sent to the frontend.
   * @throws UnauthorizedException   If the user is not authorized to perform the action.
   * @throws TrackedBackendException If there is an error in the backend.
   */
  T run() throws UnauthorizedException, TrackedBackendException;

}
