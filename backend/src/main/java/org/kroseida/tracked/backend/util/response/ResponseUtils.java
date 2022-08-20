package org.kroseida.tracked.backend.util.response;

import com.google.gson.JsonObject;
import org.kroseida.tracked.backend.logic.exception.TrackedBackendException;
import org.kroseida.tracked.backend.logic.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ResponseUtils {

  public static class Result {
    public static final String SUCCESS = "SUCCESS";
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
  }

  private ResponseUtils() {
  }

  /**
   * This method will handle the entire application layer code and try to handle errors by himself.
   * Error like Unauthorized will be catched automaticly.
   *
   * @param runnable application layer code
   * @return a mups Response
   */
  public static <T> ResponseEntity<ResponseData<T>> handle(ResponseRunnable<T> runnable) {
    try {
      return success(runnable.run());
    } catch (UnauthorizedException e) {
      return unauthorized();
    } catch (TrackedBackendException e) {
      return responseEntity(new ResponseData(e.getType(), e.getMessage()), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace(); // TODO: use logger// TODO: use logger
      return internalError();
    }
  }

  public static <T> ResponseEntity<ResponseData<T>> success(T data) {
    return responseEntity(new ResponseData(Result.SUCCESS, data), HttpStatus.OK);
  }

  public static <T> ResponseEntity<ResponseData<T>> internalError() {
    return responseEntity(new ResponseData(Result.INTERNAL_ERROR, null), HttpStatus.OK);
  }

  public static <T> ResponseEntity<ResponseData<T>> unauthorized() {
    return responseEntity(new ResponseData(Result.UNAUTHORIZED, null), HttpStatus.OK);
  }

  public static ResponseEntity responseEntity(ResponseData response, HttpStatus status) {
    MultiValueMap<String, String> header = new LinkedMultiValueMap<>();

    header.add("Content-Type", "application/json");
    return new ResponseEntity(response, header, status);
  }

}
