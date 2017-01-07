package net.javacogito.fizzbuzz;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      String[] numbers = inputLine.split(" ");
      int x = Integer.parseInt(numbers[0]);
      int y = Integer.parseInt(numbers[1]);
      int numCount = Integer.parseInt(numbers[2]);
      for (int i = 1; i <= numCount; i++) {
        boolean isFizzBuzz = false;
        if (i % x == 0) {
          System.out.print("F");
          isFizzBuzz = true;
        }
        if (i % y == 0) {
          System.out.print("B");
          isFizzBuzz = true;
        }
        if (!isFizzBuzz) {
          System.out.print(i);
        }
        if (i != numCount) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }
}