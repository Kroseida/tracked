package org.kroseida.tracked.backend.logic.exception;

/**
 * Unauthorized is thrown when the user is not authorized to perform the action.
 * This is not a business exception, but a technical one.
 * <p>
 * This class does not extend {@link TrackedBackendException} because it is not a business exception!
 */
public class UnauthorizedException extends RuntimeException {

}
