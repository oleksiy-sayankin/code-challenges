package net.javacogito.multiples3or5;

public class Solution {
  public int solution(int number) {
    int sum = 0;
    for (int i = 3; i < number; i++) {
      if (isDividableBy3(i) || isDividableBy5(i)) {
        sum+= i;
      }
    }
    return sum;
  }

  private static boolean isDividableBy3(int number) {
    return number / 3 * 3 == number;
  }

  private static boolean isDividableBy5(int number) {
    return number / 5 * 5 == number;
  }
}
