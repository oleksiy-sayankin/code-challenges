package net.javacogito.burrowswhellertransform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
  private static final String EMPTY = "";
  private static final String EOL = "$";

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
    String[] lines = new String[length];
    Arrays.fill(lines, EMPTY);
    lines = add(lines, symbols);
    for(int i = 1; i <= length - 1; i++){
      String[] sortedLines = Arrays.copyOf(lines, length);
      Arrays.sort(sortedLines);
      lines = add(lines, lastCol(sortedLines));
    }
    return solution(lines);
  }

  private static char[] lastCol(String[] lines){
    int length = lines.length;
    char[] result = new char[length];
    int i = 0;
    for(String line : lines){
      result[i] = line.charAt(line.length() - 1);
      i++;
    }
    return result;
  }

  private static String[] add(String[] lines, char[] symbols){
    int length = symbols.length;
    for(int i = 0; i <= length - 1; i++){
      lines[i] = lines[i] + Character.toString(symbols[i]);
    }
    return lines;
  }

  private static String solution(String[] lines){
    for (String line : lines){
      if(line.endsWith(EOL)){
        return line;
      }
    }
    return EMPTY; // never happens
  }


  static String encode(String data){
    int length = data.length();
    String[] matrix = new String[length];
    matrix[0] = data;
    System.out.println(matrix[0]);
    for(int i = 1; i <= length - 1; i++){
      matrix[i] = rotateRight(matrix[i - 1]);
      System.out.println(matrix[i]);
    }
    Arrays.sort(matrix);
    System.out.println("------------");
    char[] result = new char[length];
    for(int i = 0; i <= length - 1; i++) {
      result[i] = matrix[i].charAt(length - 1);
      System.out.println(matrix[i]);
    }
    System.out.println("------------");
    System.out.println(String.valueOf(result)  + "|");
    System.out.println("============");
    return (String.valueOf(result));
  }

  private static String rotateRight(String data){
    int length = data.length();
    return  data.substring(length - 1, length) + data.substring(0, length - 1);
  }
}