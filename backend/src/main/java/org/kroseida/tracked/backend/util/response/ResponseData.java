package org.kroseida.tracked.backend.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents a response with status and data.
 *
 * @param <T> The type of the data.
 */
@Data
@AllArgsConstructor
public class ResponseData<T> {

  private String result;
  private T data;

}

