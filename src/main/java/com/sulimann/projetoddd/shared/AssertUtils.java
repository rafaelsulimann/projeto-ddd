package com.sulimann.projetoddd.shared;

public class AssertUtils {

  public static void hasText(String value, String message) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void notNull(Object value, String message) {
    if (value == null) {
      throw new IllegalArgumentException(message);
    }
  }

}
