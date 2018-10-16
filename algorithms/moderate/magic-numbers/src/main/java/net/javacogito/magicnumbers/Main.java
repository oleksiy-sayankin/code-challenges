package net.javacogito.magicnumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toString(findMagicNumbers(parseStart(inputLine), parseEnd(inputLine))));
      }
    }
  }

  private static List<Integer> findMagicNumbers(int start, int end){
    List<Integer> result = new LinkedList<>();
    for(int i = start; i <= end; i++){
      if(isMagicNumber(i)){
        result.add(i);
      }
    }
    if(result.isEmpty()){
      result.add(-1);
    }
    return result;
  }

  private static int parseStart(String data){
    return Integer.parseInt(data.split(" ")[0]);
  }

  private static int parseEnd(String data){
    return Integer.parseInt(data.split(" ")[1]);
  }


  private static String toString(List<Integer> numbers){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (int number : numbers){
      if(first){
        sb.append(number);
        first = false;
        continue;
      }
      sb.append(" ");
      sb.append(number);
    }
    return sb.toString();
  }


  private static boolean isMagicNumber(int n){
    char[] digits = Integer.toString(n).toCharArray();
    if(hasRepeatedDigits(digits)){
      return false;
    }
    int length = digits.length;
    int index = 0;
    boolean[] visitedDigits = new boolean[length];
    while (!allVisited(visitedDigits)){
      char digit = digits[index];
      int timesToMove = digit - (int)'0';
      index = moveNTimes(timesToMove, index, length - 1);
      if(visitedDigits[index]){
        return false;
      }
      visitedDigits[index] = true;
    }
    return true;
  }


  private static boolean hasRepeatedDigits(char[] digits){
    Set<Character> verifiedDigits = new HashSet<>();
    for(char digit : digits){
      if(verifiedDigits.contains(digit)){
        return true;
      } else {
        verifiedDigits.add(digit);
      }
    }
    return false;
  }

  private static boolean allVisited(boolean[] visitedDigits){
    for(boolean visitedDigit : visitedDigits){
      if(!visitedDigit){
        return false;
      }
    }
    return true;
  }

  private static int moveNTimes(int n, int currentIndex, int endIndex){
    int result = currentIndex;
    for(int i = 1; i <= n; i++){
      result = move(result, endIndex);
    }
    return result;
  }

  private static int move(int currentIndex, int endIndex){
    currentIndex++;
    if(currentIndex > endIndex){
      currentIndex = 0;
    }
    return currentIndex;
  }
}