package org.kroseida.tracked.backend.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionHelper {

  private CollectionHelper() {
  }

  /**
   * Create a list from an iterable.
   *
   * @param iterable The iterable to create a list from.
   * @param <T>      The type of the iterable.
   * @return A list containing the elements of the iterable.
   */
  public static <T> List<T> listFromIterable(Iterable<T> iterable) {
    List<T> list = new ArrayList<>();
    for (T item : iterable) {
      list.add(item);
    }
    return list;
  }

}
