package org.kroseida.tracked.backend.util.logic;

public class LogicUtils {

  private LogicUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static <T> void updateField(T value, FieldUpdateExecutor<T> update) {
    if (value == null) {
      return;
    }
    update.update(value);
  }

}
