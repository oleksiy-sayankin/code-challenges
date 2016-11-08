package net.javacogito.numberofones;

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
      int number = Integer.parseInt(inputLine.trim());
      int numberOfOnes = 0;
      while (number > 0) {
        if (number % 2 == 1) {
          numberOfOnes++;
        }
        number /= 2;
      }
      System.out.println(numberOfOnes);
    }
  }
}