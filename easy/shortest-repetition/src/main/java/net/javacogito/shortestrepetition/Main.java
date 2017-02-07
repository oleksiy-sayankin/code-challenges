package net.javacogito.shortestrepetition;

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
        System.out.println(findShortestRepetition(inputLine));
      }
    }
  }

  private static int findShortestRepetition(String data){
    boolean isRepetition = false;
    int repetitionLength = 1;
    String repetition = data.substring(0, repetitionLength);
    while (!isRepetition(repetition, data)){
      repetitionLength++;
      repetition = data.substring(0, repetitionLength);
    }
    return repetitionLength;
  }

  private static boolean isRepetition(String repetition, String line){
    int numRepetitions = line.length() / repetition.length();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= numRepetitions - 1; i++){
      sb.append(repetition);
    }
    return sb.toString().equals(line);
  }
}