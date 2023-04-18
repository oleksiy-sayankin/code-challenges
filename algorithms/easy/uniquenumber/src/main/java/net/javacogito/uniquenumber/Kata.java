package net.javacogito.uniquenumber;

public class Kata {
  public static double findUniq(double numbers[]) {
    double first = numbers[0];
    double second = numbers[1];
    double third = numbers[2];
    double repetition = numbers[0];
    if (first == second && second == third) {
      repetition = first;
    }
    if (first == second && second != third) {
      return third;
    }
    if (first == third && first != second) {
      return second;
    }
    if (second == third && first != second) {
      return first;
    }

    for (double number : numbers) {
      if (number != repetition) {
        return number;
      }
    }
    return numbers[0];
  }
}
