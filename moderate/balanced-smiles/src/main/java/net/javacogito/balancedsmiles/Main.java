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
    data = deleteSmiles(data);
    data = deleteAzCharsAndColons(data);
    data = deleteColons(data);
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

  private static String deleteAzCharsAndColons(String data){
    String result = data.replaceAll("[a-z]|\\s", "Z");
    return result.replaceAll("Z+:Z+", "");
  }

  static String deleteColons(String data){
    char[] symbols = data.toCharArray();
    StringBuilder sb = new StringBuilder();
    int index = 0;
    int last = symbols.length - 1;
    for(char symbol : symbols){
      if(symbol != ':'){
        index++;
        sb.append(symbol);
        continue;
      }
      if(index == last){
        index++;
        continue;
      }
      if(symbols[index + 1] != ')' && symbols[index + 1] != '('){
        index++;
        continue;
      }
      sb.append(symbol);
      index++;
    }
    return sb.toString();
  }

  static String deleteSmiles(String data){
    String result = data.replaceAll("\\(:\\)", "");
    result = result.replaceAll(":\\)" , "");
    result = result.replaceAll(":\\(" , "");
    return result;
  }
}