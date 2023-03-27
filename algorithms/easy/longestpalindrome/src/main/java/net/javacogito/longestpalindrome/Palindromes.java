package net.javacogito.longestpalindrome;

public class Palindromes {
  public static int longestPalindrome(final String s) {
    int length = s.length();
    int maxPalindromeLength = 0;
    for (int i = 0; i <= length - 1; i++) {
      for (int j = i; j <= length; j++) {
        String data = s.substring(i, j);
        int currentPalindromeLength = data.length();
        if (isPalindrome(data) && currentPalindromeLength > maxPalindromeLength) {
          maxPalindromeLength = currentPalindromeLength;
        }
      }
    }
    return maxPalindromeLength;
  }

  public static boolean isPalindrome(String data) {
    int length = data.length();
    for (int i = 0; i <= length / 2 - 1; i++) {
      if (data.charAt(i) != data.charAt(length - i - 1)) {
        return false;
      }
    }
    return true;
  }
}
