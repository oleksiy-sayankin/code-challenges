package net.javacogito.watermelon;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int value = sc.nextInt();
    System.out.println(isEven(value) && value > 2 ? "YES" : "NO");
  }

  private static boolean isEven(int n) {
    return (n / 2) * 2 == n;
  }
}