package net.javacogito.sum;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    for (int i = 0; i <= n - 1; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      int c = sc.nextInt();
      if(a + b == c || a + c == b || b + c == a) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    }
  }
}