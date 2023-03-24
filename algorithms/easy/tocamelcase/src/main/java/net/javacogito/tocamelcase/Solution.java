package net.javacogito.tocamelcase;

public class Solution {
  static String toCamelCase(String s) {
    StringBuilder sb = new StringBuilder();
    int length = s.length();
    for (int i = 0; i <= length - 1; i++) {
      char currentChar = s.charAt(i);
      if (currentChar == '_' || currentChar == '-') {
        continue;
      }
      boolean isFirstChar = i == 0;
      if (!isFirstChar) {
        char previousChar = s.charAt(i - 1);
        if (previousChar == '_' || previousChar == '-') {
          sb.append(Character.toUpperCase(currentChar));
        } else {
          sb.append(currentChar);
        }
      } else {
        sb.append(currentChar);
      }
    }
    return sb.toString();
  }
}
