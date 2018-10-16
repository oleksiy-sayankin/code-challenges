package net.javacogito.cleanupthewords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(cleanUp(inputLine));
      }
    }
  }

  private static String cleanUp(String data) {
    return data.toLowerCase().replaceAll("[^a-z]+", " ").trim();
  }
}