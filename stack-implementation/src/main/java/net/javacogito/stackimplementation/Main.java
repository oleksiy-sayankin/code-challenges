package net.javacogito.stackimplementation;


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
      inputLine = inputLine.trim();
      String numbers[] = inputLine.split(" ");
      int numbersCount = numbers.length;
      boolean isNumbersCountEven = numbersCount % 2 == 0;
      int firstIndex;
      if (isNumbersCountEven) {
        firstIndex = 1;
      } else {
        firstIndex = 0;
      }
      for (int i = numbersCount - 1; i >= 0; i -= 2) {
        System.out.print(numbers[i]);
        if (i > firstIndex) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }
}