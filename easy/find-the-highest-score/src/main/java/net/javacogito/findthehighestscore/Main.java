package net.javacogito.findthehighestscore;

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
        System.out.println(findTheHighestScore(parseArray(inputLine)));
      }
    }
  }

  private static int[][] parseArray(String data){
    String[] rows = data.split(" \\| ");
    int numRow = rows.length;
    String[] cols = rows[0].split(" ");
    int numCols = cols.length;
    int[][] result = new int[numRow][numCols];
    int i = 0;
    for(String row : rows){
      cols = row.split(" ");
      int j = 0;
      for (String number: cols){
        result[i][j] = Integer.parseInt(number);
        j++;
      }
      i++;
    }
    return result;
  }

  private static String findTheHighestScore(int[][] array) {
    int numCols = array[0].length;
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(int j = 0; j <= numCols - 1; j++){
      int highestScore = maxAtCol(array, j);
      if(first){
        sb.append(highestScore);
        first = false;
        continue;
      }
      sb.append(" ");
      sb.append(highestScore);
    }
    return sb.toString();
  }

  private static int maxAtCol(int[][] array, int calIndex){
    int numRows = array.length;
    int max = array[0][calIndex];
    for(int i = 1; i <= numRows - 1; i++){
      if(array[i][calIndex] > max){
        max = array[i][calIndex];
      }
    }
    return max;
  }
}