package net.javacogito.chardonnayorcabernet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
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
        System.out.println(process(inputLine));
      }
    }
  }

  private static String process(String inputLine){
    String[] namesOfWines = parseNamesOfWines(inputLine);
    String letters = parseLetters(inputLine);
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    boolean hasFound = false;
    for(String nameOfWine : namesOfWines){
      if(containsAll(nameOfWine.toLowerCase(), letters.toLowerCase())){
        hasFound = true;
        if(first){
          sb.append(nameOfWine);
          first = false;
          } else {
          sb.append(" ");
          sb.append(nameOfWine);
        }
      }
    }
    if(hasFound){
      return sb.toString();
    }
    return "False";
  }

  private static String[] parseNamesOfWines(String inputLine){
    String[] rawData = inputLine.split(" \\| ");
    return rawData[0].split(" ");
  }

  private static String parseLetters(String inputLine){
    String[] rawData = inputLine.split(" \\| ");
    return rawData[1];
  }

  private static boolean containsAll(String word, String letters){
    int length = letters.length();
    for(int i = 0; i <= length - 1; i++){
      String letter = letters.substring(i, i + 1);
      if(!word.contains(letter)){
        return false;
      } else {
        word = word.replaceFirst(letter, "");
      }
    }
    return true;
  }
}