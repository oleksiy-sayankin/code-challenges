package net.javacogito.burrowswheelertransform;

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
    char[][] lines = new char[length][length];
    add(lines, symbols, 0);
    for(int i = 1; i <= length - 1; i++){
      char[][] sortedLines = Arrays.copyOf(lines, length);
      sort(sortedLines);
      add(lines, lastCol(sortedLines, i - 1), i);
    }
    return solution(lines);
  }

  private static void sort(char[][] lines){
    boolean hasSwitch = true;
    int length = lines.length;
    while (hasSwitch){
      hasSwitch = false;
      for(int i = 0; i <= length - 2; i++){
        if(compare(lines[i], lines[i + 1]) > 0){
          switchLines(lines, i, i + 1);
          hasSwitch = true;
        }
      }
    }
  }


  private static void switchLines(char[][] lines, int row1, int row2){
    char[] temp = lines[row2];
    lines[row2] = lines[row1];
    lines[row1] = temp;
  }

  private static int compare(char[] line1, char[] line2){
    if (line1[0] < line2[0]){
      return -1;
    }
    if (line1[0] > line2[0]){
      return 1;
    }
    return 0;
  }


  private static char[] lastCol(char[][] lines, int col){
    int length = lines.length;
    char[] result = new char[length];
    int i = 0;
    for(char[] line : lines){
      result[i] = line[col];
      i++;
    }
    return result;
  }

  private static void add(char[][] lines, char[] symbols, int col){
    int length = symbols.length;
    for(int i = 0; i <= length - 1; i++){
      lines[i][col] = symbols[i] ;
    }
  }

  private static String solution(char[][] lines){
    int length = lines.length;
    for (char[] line : lines){
      if(line[length - 1] == EOL){
        return new String(line);
      }
    }
    return EMPTY; // never happens
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
    return (String.valueOf(result));
  }

  private static String rotateRight(String data){
    int length = data.length();
    return  data.substring(length - 1, length) + data.substring(0, length - 1);
  }
}