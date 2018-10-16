package net.javacogito.trailingstring;

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
      if(EMPTY.equals(inputLine)){
        continue;
      }
      String source = parseSource(inputLine);
      String pattern = parsePattern(inputLine);
      System.out.println(source.endsWith(pattern)? 1 : 0);
    }
  }

  private static String parseSource(String inputLine){
    return inputLine.split(",")[0];
  }

  private static String parsePattern(String inputLine){
    return inputLine.split(",")[1];
  }
}