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
  private static final String VALID_CHARS = "abcdefghijgklmnopqrstuvwxyz :";
  private static final int NO_BRACKET = -1;

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
    String result = preProcessSmile(data);
    while (smileExists(result)){
      result = preProcessSmile(result);
    }

    while (frownExists(result)){
      result = preProcessFrown(result);
    }

    result = result.replaceAll("Z+:+Z+", "Z");
    return result.replaceAll("Z", "");
  }

  static String preProcessSmile(String data){
    int leftIndex = data.indexOf(SMILE);
    int rightIndex = leftIndex + 1;

    while (leftIndex > 0 && !isBracket(data.charAt(leftIndex))){
      leftIndex--;
    }

    int[] withFirstBracket = maxBalancedInterval(data, leftIndex, rightIndex);
    int[] withFirstNoBracket = maxBalancedInterval(data, leftIndex, rightIndex + 1);
    
    return "";
  }

  private static int[] maxBalancedInterval(String data, int leftIndex, int rightIndex){
    int length = data.length();
    int[] result = new int[2];
    while (leftIndex > 0 && rightIndex < length){
      int nextLeftIndex = nearestOpenLeftBracket(data, leftIndex);
      int nextRightIndex = nearestClosedRightBracket(data, rightIndex);
      if((bracketExist(nextLeftIndex) && bracketExist(nextRightIndex))
        || (!bracketExist(nextLeftIndex) && !bracketExist(nextRightIndex))){
        leftIndex = nextLeftIndex;
        rightIndex = nextRightIndex;
        continue;
      }
      break;
    }
    result[0] = leftIndex;
    result[1] = rightIndex;
    return result;
  }

  private static boolean isValid(char symbol){
    if(symbol >= 'a' && symbol <= 'z'){
      return true;
    }
    return symbol == ':' || symbol == ' ';
  }

  private static boolean bracketExist(int index){
    return index != NO_BRACKET && index >= 0;
  }

  private static int nearestOpenLeftBracket(String data, int startIndex){
    int result = startIndex;
    result--;
    if(result == -1){
      return NO_BRACKET;
    }
    char currentChar = data.charAt(result);
    while (isValid(currentChar) && currentChar != '('){
      result--;
      if(result == -1){
        return NO_BRACKET;
      }
      currentChar = data.charAt(result);
    }
    return result;
  }


  private static int nearestClosedRightBracket(String data, int startIndex){
    int result = startIndex;
    int length = data.length();
    result++;
    if(result == length){
      return NO_BRACKET;
    }
    char currentChar = data.charAt(result);
    while (isValid(currentChar) && currentChar != ')'){
      result++;
      if(result == length){
        return NO_BRACKET;
      }
      currentChar = data.charAt(result);
    }
    return result;
  }



  private static boolean isBracket(char symbol){
    return symbol == '(' || symbol == ')';
  }

  static String preProcessFrown(String data){
    return "";
  }


  private static boolean smileExists(String data){
    return data.contains(SMILE);
  }

  private static boolean frownExists(String data){
    return data.contains(FROWN);
  }

}