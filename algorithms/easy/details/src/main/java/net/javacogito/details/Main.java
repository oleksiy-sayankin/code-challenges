package net.javacogito.details;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(sellsToMove(parseInput(inputLine)));
      }
    }
  }

  private static int sellsToMove(char[][] data){
    int minSellsToMove = Integer.MAX_VALUE;
    for(int i = 0; i <= data.length - 1; i++){
      int sellsToMove = leftestYIndex(data[i]) - rightestXIndex(data[i]) - 1;
      if(sellsToMove < minSellsToMove){
        minSellsToMove = sellsToMove;
      }
    }
    return minSellsToMove;
  }

  private static int leftestYIndex(char[] row){
    for(int i = 0; i <= row.length - 1; i++){
      if(row[i] == 'Y'){
        return i;
      }
    }
    return 0;
  }

  private static int rightestXIndex(char[] row){
    for(int i = row.length - 1; i >= 0; i--){
      if(row[i] == 'X'){
        return i;
      }
    }
    return 0;
  }


  private static char[][] parseInput(String inputLine){
    String[] rawData = inputLine.split(",");
    int numRows = rawData.length;
    int numCols = rawData[0].length();
    char[][] result = new char[numRows][numCols];
    for(int i = 0; i <= numRows - 1; i++){
      for (int j = 0; j <= numCols - 1; j++){
        if(rawData[i].charAt(j) == 'X'){
          result[i][j] = 'X';
        }
        if(rawData[i].charAt(j) == 'Y'){
          result[i][j] = 'Y';
        }
      }
    }
    return result;
  }
}