package net.javacogito.stepwiseword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(buildLongestStepWiseWord(inputLine));
      }
    }
  }

  private static String buildLongestStepWiseWord(String data) {
    return buildStepWiseWord(findLongest(data.split(" ")));
  }

  private static String findLongest(String[] words){
    String longestWord = words[0];
    for (String word : words){
      if(word.length() > longestWord.length()){
        longestWord = word;
      }
    }
    return longestWord;
  }

  private static String buildStepWiseWord(String word){
    char[] symbols = word.toCharArray();
    boolean first = true;
    StringBuilder sb = new StringBuilder();
    int size = 0;
    for (char symbol : symbols){
      if(first){
        first = false;
        sb.append(Character.toString(symbol));
        size++;
        continue;
      }
      sb.append(" ");
      sb.append(fillInWith("*", size));
      sb.append(Character.toString(symbol));
      size++;
    }
    return sb.toString();
  }

  private static String fillInWith(String symbol, int size){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i <= size - 1; i++){
      sb.append(symbol);
    }
    return sb.toString();
  }
}