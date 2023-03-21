package net.javacogito.nextbiggernumber;


import static java.lang.Math.abs;

public class Kata {
  private static final int NOT_FOUND = -1;

  public static long nextBiggerNumber(long n) {
    int[] digits = splitToDigits(n);
    int currentIndex = digits.length - 2;
    int indexOfTheNearest = indexOfTheNearest(digits, currentIndex);
    while (indexOfTheNearest == NOT_FOUND && currentIndex >= 1) {
      currentIndex--;
      indexOfTheNearest = indexOfTheNearest(digits, currentIndex);
    }
    if (indexOfTheNearest == NOT_FOUND) {
      return NOT_FOUND;
    }
    switchDigits(digits, currentIndex, indexOfTheNearest);
    sortAsc(digits, currentIndex + 1);
    return buildNumber(digits);
  }

  private static void sortAsc(int[] digits, int startIndex) {
    boolean switched = true;
    while (switched) {
      switched = false;
      for (int i = startIndex; i <= digits.length - 2; i++) {
        if (digits[i] > digits[i + 1]) {
          switchDigits(digits, i, i + 1);
          switched = true;
        }
      }
    }
  }

  private static void switchDigits(int[] digits, int i, int j) {
    int temp = digits[i];
    digits[i] = digits[j];
    digits[j] = temp;
  }

  private static int indexOfTheNearest(int[] digits, int digitIndex) {
    if (digits.length == 1) {
      return NOT_FOUND;
    }
    int indexOfTheNearest = digitIndex + 1;
    int nearest = digits[indexOfTheNearest];
    int digit = digits[digitIndex];
    boolean found = nearest > digits[digitIndex];
    for (int i = digitIndex + 1; i <= digits.length - 1; i++) {
      int current = digits[i];
      if (abs(digit - current) < abs(digit - nearest) && current > digit) {
        nearest = current;
        indexOfTheNearest = i;
        found = true;
      }
    }
    return found ? indexOfTheNearest : NOT_FOUND;
  }

  private static int[] splitToDigits(long n) {
    String numberAsString = Long.toString(n);
    int length = numberAsString.length();
    int[] digits = new int[length];
    for (int i = 0; i <= length - 1; i++) {
      digits[i] = stringAt(numberAsString, i);
    }
    return digits;
  }

  private static int stringAt(String value, int index) {
    return Integer.parseInt(value.substring(index, index + 1));
  }

  private static long buildNumber(int[] digits) {
    int power = 0;
    long result = 0;
    for (int i = digits.length - 1; i >= 0; i--) {
      result += powerTen(power) * digits[i];
      power++;
    }
    return result;
  }

  private static long powerTen(int power) {
    long result = 1;
    for (int i = 0; i <= power - 1; i++) {
      result *= 10;
    }
    return result;
  }
}
