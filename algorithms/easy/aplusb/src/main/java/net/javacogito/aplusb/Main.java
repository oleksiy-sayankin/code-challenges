package net.javacogito.aplusb;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    for (int i = 0; i <= n - 1; i++) {
      String toParse = sc.next();
      int a = Integer.parseInt(toParse.substring(0, 1));
      int b = Integer.parseInt(toParse.substring(2, 3));
      int sum = a + b;
      System.out.println(sum);
    }
  }
}
