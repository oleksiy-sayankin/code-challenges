package net.javacogito.romannumeralshelper;

import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {
  private static final Map<Character, String>[] toRoman = new Map[4];

  static {
    toRoman[0] = new HashMap<>();
    toRoman[1] = new HashMap<>();
    toRoman[2] = new HashMap<>();
    toRoman[3] = new HashMap<>();

    // Digit 0
    toRoman[0].put('1', "I");
    toRoman[0].put('2', "II");
    toRoman[0].put('3', "III");
    toRoman[0].put('4', "IV");
    toRoman[0].put('5', "V");
    toRoman[0].put('6', "VI");
    toRoman[0].put('7', "VII");
    toRoman[0].put('8', "VIII");
    toRoman[0].put('9', "IX");

    // Digit 1
    toRoman[1].put('1', "X");
    toRoman[1].put('2', "XX");
    toRoman[1].put('3', "XXX");
    toRoman[1].put('4', "XL");
    toRoman[1].put('5', "L");
    toRoman[1].put('6', "LX");
    toRoman[1].put('7', "LXX");
    toRoman[1].put('8', "LXXX");
    toRoman[1].put('9', "XC");

    // Digit 2
    toRoman[2].put('1', "C");
    toRoman[2].put('2', "CC");
    toRoman[2].put('3', "CCC");
    toRoman[2].put('4', "CD");
    toRoman[2].put('5', "D");
    toRoman[2].put('6', "DC");
    toRoman[2].put('7', "DCC");
    toRoman[2].put('8', "DCCC");
    toRoman[2].put('9', "CM");

    // Digit 3
    toRoman[3].put('1', "M");
    toRoman[3].put('2', "MM");
    toRoman[3].put('3', "MMM");
  }

  private static final Map<String, Integer> fromRoman = new HashMap<>();

  static {

    fromRoman.put("I", 1);
    fromRoman.put("IV", 4);
    fromRoman.put("V", 5);
    fromRoman.put("IX", 9);
    fromRoman.put("X", 10);
    fromRoman.put("XL", 40);
    fromRoman.put("L", 50);
    fromRoman.put("XC", 90);
    fromRoman.put("C", 100);
    fromRoman.put("CD", 400);
    fromRoman.put("D", 500);
    fromRoman.put("CM", 900);
    fromRoman.put("M", 1000);
  }

  public static String toRoman(int n) {
    String result = "";
    String numberAsString = Integer.toString(n);
    int length = numberAsString.length();
    for (int i = length - 1; i >= 0; i--) {
      char digit = numberAsString.charAt(i);
      if (digit == '0') {
        continue;
      }
      result = toRoman[length - i - 1].get(digit) + result;
    }
    return result;
  }

  public static int fromRoman(String romanNumeral) {
    int result = 0;
    while (!romanNumeral.isEmpty()) {
      if (romanNumeral.length() >= 2) {
        Integer lastTwoDigits = fromRoman.get(lastTwoDigits(romanNumeral));
        if (lastTwoDigits != null) {
          result += lastTwoDigits;
          romanNumeral = removeLastTwoDigits(romanNumeral);
          continue;
        }
      }
      result += fromRoman.get(lastDigit(romanNumeral));
      romanNumeral = removeLastDigit(romanNumeral);
    }
    return result;
  }

  private static String lastTwoDigits(String romanNumeral) {
    return romanNumeral.substring(romanNumeral.length() - 2);
  }

  private static String lastDigit(String romanNumeral) {
    return romanNumeral.substring(romanNumeral.length() - 1);
  }

  private static String removeLastTwoDigits(String romanNumeral) {
    return romanNumeral.substring(0, romanNumeral.length() - 2);
  }

  private static String removeLastDigit(String romanNumeral) {
    return romanNumeral.substring(0, romanNumeral.length() - 1);
  }
}
