package net.javacogito.swapnumbers;

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
        System.out.println(swapNumbers(inputLine));
      }
    }
  }

  private static String swapNumbers(String data){
    String[] words = data.split(" ");
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(String word : words){
      if(first){
        first = false;
        sb.append(swapNumbersInAWord(word));
        continue;
      }
      sb.append(" ");
      sb.append(swapNumbersInAWord(word));
    }
    return sb.toString();
  }

  private static String swapNumbersInAWord(String word){
    int length = word.length();
    String firstNumber = word.substring(0, 1);
    String lastNumber = word.substring(length - 1);
    return lastNumber + word.substring(1, length -1) + firstNumber;
  }
}