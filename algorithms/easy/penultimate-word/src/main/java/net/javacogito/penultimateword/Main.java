package net.javacogito.penultimateword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(nextToLast(inputLine));
      }
    }
  }
  private static String nextToLast(String data){
    String[] words = data.replaceAll("[ ]{2,}", " ").split(" ");
    int nextToLastIndex = words.length - 2;
    return words[nextToLastIndex];
  }
}