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

    return removeBalanced(result);
  }

  private static String removeBalanced(String data){
    return data.replaceAll(BALANCE_SYMBOL, "");
  }

  static String preProcessSmile(String data){
    int leftIndex = data.indexOf(SMILE);
    int rightIndex = leftIndex;

    leftIndex = maxLeftNonTerminal(data, leftIndex);

    int[] withFirstBracket = maxBalancedInterval(data, leftIndex, rightIndex);
    int[] withFirstNoBracket = maxBalancedInterval(data, leftIndex, maxRightNonTerminal(data,rightIndex + 1));

    int withFirstBracketLength = withFirstBracket[1] - withFirstBracket[0];
    int withFirstNoBracketLength = withFirstNoBracket[1] - withFirstNoBracket[0];

    int leftBalancedIndex;
    int rightBalancedIndex;

    if(withFirstBracketLength > withFirstNoBracketLength){
      leftBalancedIndex = withFirstBracket[0];
      rightBalancedIndex = withFirstBracket[1];
    } else {
      leftBalancedIndex = withFirstNoBracket[0];
      rightBalancedIndex = withFirstNoBracket[1];
    }
    return markBalanced(data, leftBalancedIndex, rightBalancedIndex);
  }

  private static String markBalanced(String data, int leftIndex, int rightIndex){
    StringBuilder sb = new StringBuilder();
    int length = data.length();
    for(int i = 0; i <= length - 1; i++){
      if(i >= leftIndex && i <= rightIndex){
        sb.append(BALANCE_SYMBOL);
        continue;
      }
      sb.append(data.charAt(i));
    }
    return sb.toString();
  }


  private static int[] maxBalancedInterval(String data, int leftIndex, int rightIndex){
    int length = data.length();
    int[] result = new int[2];
    while (leftIndex > 0 && rightIndex < length){
      int nextLeftIndex = nearestOpenLeftBracket(data, leftIndex);
      int nextRightIndex = nearestClosedRightBracket(data, rightIndex);
      if((bracketExist(nextLeftIndex) && bracketExist(nextRightIndex))){
        leftIndex = nextLeftIndex;
        rightIndex = nextRightIndex;
        continue;
      }
      if(!bracketExist(nextLeftIndex) && !bracketExist(nextRightIndex)){
        leftIndex = maxLeftNonTerminal(data, leftIndex);
        rightIndex = maxRightNonTerminal(data, rightIndex);
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
    while (isValid(currentChar) && !isBracket(currentChar)){
      result--;
      if(result == -1){
        return NO_BRACKET;
      }
      currentChar = data.charAt(result);
    }
    if(isOpenBracket(currentChar)){
      return result;
    }
    return NO_BRACKET;
  }

  private static int maxLeftNonTerminal(String data, int startIndex){
    int result = startIndex;
    result--;
    if(result == -1){
      return 0;
    }
    char currentChar = data.charAt(result);
    while (isValid(currentChar) && !isBracket(currentChar)){
      result--;
      if(result == -1){
        return 0;
      }
      currentChar = data.charAt(result);
    }
    return result + 1;
  }


  private static int nearestClosedRightBracket(String data, int startIndex){
    int result = startIndex;
    int length = data.length();
    result++;
    if(result == length){
      return NO_BRACKET;
    }
    char currentChar = data.charAt(result);
    while (isValid(currentChar) && !isBracket(currentChar)){
      result++;
      if(result == length){
        return NO_BRACKET;
      }
      currentChar = data.charAt(result);
    }
    if(isClosedBracket(currentChar)){
      return result;
    }
    return NO_BRACKET;
  }


  private static int maxRightNonTerminal(String data, int startIndex){
    int result = startIndex;
    int length = data.length();
    result++;
    if(result == length){
      return length - 1;
    }
    char currentChar = data.charAt(result);
    while (isValid(currentChar) && !isBracket(currentChar)){
      result++;
      if(result == length){
        return length - 1;
      }
      currentChar = data.charAt(result);
    }
    return result - 1;
  }



  private static boolean isBracket(char symbol){
    return isOpenBracket(symbol) || isClosedBracket(symbol);
  }

  private static boolean isOpenBracket(char symbol){
    return symbol == '(';
  }

  private static boolean isClosedBracket(char symbol){
    return symbol == ')';
  }


  static String preProcessFrown(String data){
    int leftIndex = data.indexOf(FROWN);
    int rightIndex = leftIndex;

    //leftIndex = maxLeftNonTerminal(data, leftIndex);
    //rightIndex = maxRightNonTerminal(data, rightIndex + 1);

    int[] withFirstBracket = maxBalancedInterval(data, maxLeftNonTerminal(data, leftIndex), rightIndex + 1);
    int[] withFirstNoBracket = maxBalancedInterval(data, leftIndex + 1, maxRightNonTerminal(data, rightIndex + 2));

    int withFirstBracketLength = withFirstBracket[1] - withFirstBracket[0];
    int withFirstNoBracketLength = withFirstNoBracket[1] - withFirstNoBracket[0];

    int leftBalancedIndex;
    int rightBalancedIndex;

    if(withFirstBracketLength > withFirstNoBracketLength){
      leftBalancedIndex = withFirstBracket[0];
      rightBalancedIndex = withFirstBracket[1];
    } else {
      leftBalancedIndex = withFirstNoBracket[0];
      rightBalancedIndex = withFirstNoBracket[1];
    }
    return markBalanced(data, leftBalancedIndex, rightBalancedIndex);
  }


  private static boolean smileExists(String data){
    return data.contains(SMILE);
  }

  private static boolean frownExists(String data){
    return data.contains(FROWN);
  }

}