package net.javacogito.interruptedbubblesort;

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
      String[] numbersAndState = inputLine.split("\\|");
      numbersAndState[0] = numbersAndState[0].trim();
      numbersAndState[1] = numbersAndState[1].trim();
      String[] numbersAsString = numbersAndState[0].split(" ");
      int numbersCount = numbersAsString.length;
      int[] numbers = new int[numbersCount];
      for (int i = 0; i <= numbersCount - 1; i++) {
        numbers[i] = Integer.parseInt(numbersAsString[i]);
      }
      long state = Long.parseLong(numbersAndState[1]);
      for (long i = 0; i <= state - 1; i++) {
        boolean isChanged = false;
        for (int j = 0; j <= numbersCount - 2; j++) {
          if (numbers[j] > numbers[j + 1]) {
            int temp = numbers[j];
            numbers[j] = numbers[j + 1];
            numbers[j + 1] = temp;
            isChanged = true;
          }
        }
        if (!isChanged) {
          break;
        }
      }

      for (int j = 0; j <= numbersCount - 2; j++) {
        System.out.print(numbers[j] + " ");
      }
      System.out.print(numbers[numbersCount - 1]);
      System.out.println();
    }
  }
}