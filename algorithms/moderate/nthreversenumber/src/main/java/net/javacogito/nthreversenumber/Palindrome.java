package net.javacogito.nthreversenumber;

import java.math.BigInteger;

public class Palindrome {

  /**
   * Brute force solution. For testing purpose only.
   *
   * @param n index
   * @return palindrome
   */
  public static BigInteger findReverseNumber(long n) {
    int i = 1;
    BigInteger number = BigInteger.ZERO;
    boolean isPalindrome;
    while (i < n) {
      number = number.add(BigInteger.ONE);
      isPalindrome = isPalindrome(number);
      i += isPalindrome ? 1 : 0;
    }
    return number;
  }

  private static boolean isPalindrome(BigInteger number) {
    String asStringNumber = number.toString();
    int length = asStringNumber.length();
    if (length == 1) {
      return true;
    }
    for (int i = 0; i <= length / 2 - 1; i++) {
      if (asStringNumber.charAt(i) != asStringNumber.charAt(length - i - 1)) {
        return false;
      }
    }
    return true;
  }

}
