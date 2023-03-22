package net.javacogito.theobservedpin;

import java.util.*;

import static java.util.Arrays.asList;

public class ObservedPin {
  private static final Map<String, List<String>> possibleDigits = new HashMap<>();

  static {
    possibleDigits.put("1", asList("1", "2", "4"));
    possibleDigits.put("2", asList("1", "2", "3", "5"));
    possibleDigits.put("3", asList("2", "3", "6"));
    possibleDigits.put("4", asList("1", "4", "5", "7"));
    possibleDigits.put("5", asList("2", "4", "5", "6", "8"));
    possibleDigits.put("6", asList("3", "5", "6", "9"));
    possibleDigits.put("7", asList("4", "7", "8"));
    possibleDigits.put("8", asList("0", "5", "7", "8", "9"));
    possibleDigits.put("9", asList("6", "8", "9"));
    possibleDigits.put("0", asList("0", "8"));
  }

  private static final List<String> newPins = new LinkedList<>();

  public static List<String> getPINs(String observed) {
    newPins.clear();
    append("", observed, 0);
    return newPins;
  } // getPINs

  private static void append(String pin, String observed, int currentIndex) {
    for (String digit : possibleDigits.get(stringAt(observed, currentIndex))) {
      String newPin = pin + digit;
      if (currentIndex == observed.length() - 1) {
        newPins.add(newPin);
      }
      if (currentIndex < observed.length() - 1) {
        append(newPin, observed, currentIndex + 1);
      }
    }
  }

  private static String stringAt(String value, int index) {
    return value.substring(index, index + 1);
  }

} // ObservedPin
