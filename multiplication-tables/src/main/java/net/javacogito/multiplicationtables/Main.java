package net.javacogito.multiplicationtables;

public class Main {
  public static void main(String[] args) {
    for (int i = 1; i <= 12; i++) {
      for (int j = 1; j <= 12; j++) {
        int product = i * j;
        System.out.printf("%4d", product);
      }
      System.out.println();
    }
  }
}