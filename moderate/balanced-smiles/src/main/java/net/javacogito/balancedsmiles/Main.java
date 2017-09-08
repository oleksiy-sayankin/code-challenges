package net.javacogito.balancedsmiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static final String YES = "YES";
  private static final String NO = "NO";
  private static final String SMILE = ":)";
  private static final String FROWN = ":(";
  private static final String BALANCE_SYMBOL = "Z";

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
    if(isSimpleBalanced(data)){
      return YES;
    }
    if(!hasSmile(data)){
      return  NO;
    }
    String result = isBalanced(replaceSingleSmile(data));
    if(YES.equals(result)){
      return YES;
    }
    return isBalanced(replaceColonFromSmile(data));
  }


  static String replaceSingleSmile(String data){
    int smileIndex = data.indexOf(SMILE);
    int frownIndex = data.indexOf(FROWN);
    if(smileIndex < 0 && frownIndex < 0){
      return data;
    }
    if(smileIndex < 0){
      return data.replaceFirst(":\\(", BALANCE_SYMBOL);
    }
    if(frownIndex < 0){
      return data.replaceFirst(":\\)", BALANCE_SYMBOL);
    }
    if(smileIndex > frownIndex){
      return data.replaceFirst(":\\(", BALANCE_SYMBOL);
    }
    return data.replaceFirst(":\\)", BALANCE_SYMBOL);
  }


  static String replaceColonFromSmile(String data){
    int smileIndex = data.indexOf(SMILE);
    int frownIndex = data.indexOf(FROWN);
    if(smileIndex < 0 && frownIndex < 0){
      return data;
    }
    StringBuilder sb = new StringBuilder(data);
    if(smileIndex < 0){
      sb.setCharAt(frownIndex, 'Z');
      return sb.toString();
    }
    if(frownIndex < 0){
      sb.setCharAt(smileIndex, 'Z');
      return sb.toString();
    }
    if(smileIndex > frownIndex){
      sb.setCharAt(frownIndex, 'Z');
      return sb.toString();
    }
    sb.setCharAt(smileIndex, 'Z');
    return sb.toString();
  }


  private static boolean hasSmile(String data){
    int smileIndex = data.indexOf(SMILE);
    int frownIndex = data.indexOf(FROWN);
    return !(smileIndex < 0 && frownIndex < 0);
  }

  private static boolean isSimpleBalanced(String data){
    if(data.isEmpty()){
      return true;
    }
    char[] symbols = data.toCharArray();
    int depth = 0;
    for (char symbol : symbols){
      if(symbol == '('){
        if(depth >= 0){
          depth++;
        } else {
          return false;
        }
      }
      if(symbol == ')'){
        if(depth >= 0){
          depth--;
        } else {
          return false;
        }
      }
    }
    return depth == 0;
  }
}