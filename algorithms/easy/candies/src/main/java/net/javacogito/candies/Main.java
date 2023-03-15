package net.javacogito.candies;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int candies = sc.nextInt();
    int friends = sc.nextInt();
    int optimal = candies / friends;
    int amountToIncrease = candies - optimal * friends;
    int optimalAmount = friends - amountToIncrease;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= optimalAmount - 1; i++) {
      sb.append(optimal);
      sb.append(" ");
    }
    for (int i = 0; i <= amountToIncrease - 1; i++) {
      sb.append(optimal + 1);
      if (i < amountToIncrease - 1) {
        sb.append(" ");
      }
    }
    System.out.println(sb);
  }
}