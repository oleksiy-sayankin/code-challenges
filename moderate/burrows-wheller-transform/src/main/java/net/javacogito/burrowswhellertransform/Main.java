package net.javacogito.burrowswhellertransform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
  private static final String EMPTY = "";
  private static final char EOL = '$';

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(decode(parseInput(inputLine)));
      }
    }
  }

  private static char[] parseInput(String data){
    return data.substring(0, data.length() - 1).toCharArray();
  }

  static String decode(char[] symbols){
    int length = symbols.length;
    char[] keys = Arrays.copyOf(symbols, length);
    Arrays.sort(keys);
    char[] values = symbols;
    boolean[] usedKeys = new boolean[length];
    char[] word = new char[length];
    word[length - 1] = EOL;
    for(int i = length - 1; i >= 1; i--){
      word[i - 1] = getElem(keys, values, usedKeys, word[i]);
    }
    return String.valueOf(word);
  }



  private static char getElem(char[] keys, char[] values, boolean[] usedKeys, char key){
    int length = keys.length;
    for(int i = 0; i <= length - 1; i++){
      if(key == keys[i] && !usedKeys[i]){
        usedKeys[i] = true;
        return values[i];
      }
    }
    return (char)0; // never happens
  }

  static String encode(String data){
    int length = data.length();
    String[] matrix = new String[length];
    matrix[0] = data;
    for(int i = 1; i <= length - 1; i++){
      matrix[i] = rotateRight(matrix[i - 1]);
    }
    Arrays.sort(matrix);
    char[] result = new char[length];
    for(int i = 0; i <= length - 1; i++) {
      result[i] = matrix[i].charAt(length - 1);
    }
    return String.valueOf(result);
  }

  private static String rotateRight(String data){
    int length = data.length();
    return  data.substring(length - 1, length) + data.substring(0, length - 1);
  }
}