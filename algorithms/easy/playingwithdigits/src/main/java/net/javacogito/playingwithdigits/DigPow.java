package net.javacogito.playingwithdigits;

public class DigPow {
  public static long digPow(int n, int p) {
    long result = sum(n , p) / n;
    if (sum(n , p) % n == 0) {
      return result;
    }
    return -1;
  }

  private static String digitAt(String number, int i) {
    return number.substring(i, i + 1);
  }

  public static long pow(long number, int power) {
    long result = 1;
    for (int i = 1; i <= power; i++) {
      result *= number;
    }
    return result;
  }

  public static long sum(int n, int p) {
    String numberAsString = Integer.toString(n);
    long sum = 0;
    int length = numberAsString.length();
    for (int i = 0; i <= length - 1; i++) {
      int digit = Integer.parseInt(digitAt(numberAsString, i));
      sum += pow(digit, p);
      p++;
    }
    return sum;
  }
}
