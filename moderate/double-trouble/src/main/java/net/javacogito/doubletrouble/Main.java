package net.javacogito.doubletrouble;

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
        System.out.println(numVariants(inputLine));
      }
    }
  }

  private static int numVariants(String data){
    String[] lines = split(data);
    if(!isCorrect(lines)){
      return 0;
    }
    if(!hasLostLetters(lines)){
      return 0;
    }
    int result = 0;
    int numDoubleStarPair = numDoubleStarPair(lines);
    if(hasSingleStarPair(lines) && numDoubleStarPair == 0){
      result = 1;
    }
    if(numDoubleStarPair >= 1){
      result += power2(numDoubleStarPair);
    }
    return result;
  }

  private static boolean isCorrect(String[] lines){
    int length = lines[0].length();
    for(int i = 0; i <= length - 1; i++){
      char a = lines[0].charAt(i);
      char b = lines[1].charAt(i);
      if(a != '*' && b != '*'){
        if(a != b){
          return false;
        }
      }
    }
    return true;
  }

  private static int numDoubleStarPair(String[] lines){
    int length = lines[0].length();
    int result = 0;
    for(int i = 0; i <= length - 1; i++){
      char a = lines[0].charAt(i);
      char b = lines[1].charAt(i);
      if(a == '*' && b == '*'){
        result++;
      }
    }
    return result;
  }

  private static int power2(int n){
    if(n == 0){
      return 1;
    }
    int result = 2;
    for(int i = 1; i <= n - 1; i++){
      result *= 2;
    }
    return result;
  }

  private static boolean hasSingleStarPair(String[] lines){
    int length = lines[0].length();
    for(int i = 0; i <= length - 1; i++){
      char a = lines[0].charAt(i);
      char b = lines[1].charAt(i);
      if((a == '*' && b != '*') || (a != '*' && b == '*')){
        return true;
      }
    }
    return false;
  }

  private static boolean hasLostLetters(String[] lines){
    int length = lines[0].length();
    for(int i = 0; i <= length - 1; i++){
      char a = lines[0].charAt(i);
      char b = lines[1].charAt(i);
      if(a == '*' || b == '*'){
        return true;
      }
    }
    return false;
  }

  private static String[] split(String data){
    String[] result = new String[2];
    int length = data.length();
    result[0] = data.substring(0, length / 2);
    result[1] = data.substring(length / 2, length);
    return result;
  }
}