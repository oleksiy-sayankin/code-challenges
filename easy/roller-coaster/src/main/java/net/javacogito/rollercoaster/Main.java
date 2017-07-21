package net.javacogito.rollercoaster;

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
        System.out.println(reformat(inputLine));
      }
    }
  }

  private static String reformat(String data){
    StringBuilder sb = new StringBuilder();
    boolean isUpper = true;
    char[] symbols = data.toCharArray();
    for(char symbol : symbols){
      if(isAlphabetic(symbol)) {
        if (isUpper) {
          sb.append(Character.toUpperCase(symbol));
          isUpper = false;
        } else {
          sb.append(Character.toLowerCase(symbol));
          isUpper = true;
        }
      } else {
        sb.append(symbol);
      }
    }
    return sb.toString();
  }

  private static boolean isAlphabetic(char symbol){
    return (symbol >= 'a' && symbol <= 'z') || (symbol >= 'A' && symbol <= 'Z');
  }
}