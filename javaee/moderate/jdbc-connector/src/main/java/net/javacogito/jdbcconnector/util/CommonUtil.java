package net.javacogito.jdbcconnector.util;

/**
 * Class for different util methods.
 */
public final class CommonUtil {
  private CommonUtil(){}

  /**
   * Checks if string is valid integer.
   *
   * @param str string to test
   * @return true if string is valid integer
   */
  public static boolean isInteger(String str) {
    if (str == null) {
      return false;
    }
    if (str.isEmpty()) {
      return false;
    }
    int i = 0;
    int length = str.length();
    if (str.charAt(0) == '-') {
      if (length == 1) {
        return false;
      }
      i = 1;
    }
    for (; i < length; i++) {
      char c = str.charAt(i);
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }
}
