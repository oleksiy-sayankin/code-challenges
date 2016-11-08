package net.javacogito.minimumcoins;

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
      int number = Integer.parseInt(inputLine);
      int fivesCount = number / 5;
      int threesCount = (number - fivesCount * 5) / 3;
      int onesCount = (number - fivesCount * 5 - threesCount * 3);
      int totalCount = onesCount + threesCount + fivesCount;
      System.out.println(totalCount);
    }
  }
}