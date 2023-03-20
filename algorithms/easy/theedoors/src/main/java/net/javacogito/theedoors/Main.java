package net.javacogito.theedoors;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    final int EMPTY = 0;
    Scanner sc = new Scanner(System.in);
    int testcasesNumber = sc.nextInt();
    for (int i = 0; i <= testcasesNumber - 1; i++) {
      boolean[] doors = new boolean[3];
      int[] keys = new int[3];
      int currentKey = sc.nextInt();
      keys[0] = sc.nextInt();
      keys[1] = sc.nextInt();
      keys[2] = sc.nextInt();
      while (isNotOpenedYet(doors, currentKey)) {
        currentKey = open(doors, keys, currentKey);
        boolean isKeyBehindTheDoor = currentKey != EMPTY;
        if (!isKeyBehindTheDoor) {
          break;
        }
      }
      System.out.println(areAllDoorsOpened(doors) ? "YES" : "NO");
    }
  }

  private static int open(boolean[] doors, int[] keys, int key) {
    doors[key - 1] = true;
    return keys[key - 1];
  }

  private static boolean areAllDoorsOpened(boolean[] doors) {
    return doors[0] && doors[1] && doors[2];
  }

  private static boolean isNotOpenedYet(boolean[] doors, int key) {
    return !doors[key - 1];
  }
}
