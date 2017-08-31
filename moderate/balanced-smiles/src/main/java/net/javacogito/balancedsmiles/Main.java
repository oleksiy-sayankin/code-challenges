package net.javacogito.balancedsmiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static final String YES = "YES";
  private static final String NO = "NO";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(isBalanced(inputLine));
      }
    }
  }

  static String isBalanced(String data){
    data = prepare(data);
    if(data.isEmpty()){
      return YES;
    }
    char[] symbols = data.toCharArray();
    int depth = 0;
    for (char symbol : symbols){
      if(symbol == '('){
        if(depth >= 0){
          depth++;
        } else {
          return NO;
        }
      }
      if(symbol == ')'){
        if(depth >= 0){
          depth--;
        } else {
          return NO;
        }
      }
    }
    if(depth == 0) {
      return YES;
    }
    return NO;
  }

  static String prepare(String data){
    String result = data.replaceAll("\\(([a-z]|\\s)*:+\\)", "Z");
    result = result.replaceAll("([a-z]\\s)*:+\\)", "Z");
    result = result.replaceAll("[a-z]|\\s", "Z");
    result = result.replaceAll("Z+:+Z+", "Z");
    result = result.replaceAll(":\\)", "Z");
    result = result.replaceAll(":\\(", "Z");
    return result.replaceAll("Z", "");
  }
}