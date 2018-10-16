package net.javacogito.sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static final String TRUE = "True";
  private static final String FALSE = "False";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(checkSudoku(parseMatrix(inputLine), parseSize(inputLine)));
      }
    }
  }

  private static int[][] parseMatrix(String data){
    int size = parseSize(data);
    int[][] result = new int[size][size];
    String[] rawData = data.split(";")[1].split(",");
    int index = 0;
    for(int i = 0; i <= size - 1; i++){
      for(int j = 0; j <= size - 1; j++){
        result[i][j] = Integer.parseInt(rawData[index]);
        index++;
      }
    }
    return result;
  }

  private static int parseSize(String data){
    return Integer.parseInt(data.split(";")[0]);
  }

  static String checkSudoku(int[][] matrix, int size){
    for(int i = 0; i <= size - 1; i++){
      if(!isRowCorrect(matrix, i, size)){
        return FALSE;
      }
      if(!isColCorrect(matrix, i, size)){
        return FALSE;
      }
    }
    int squareSize = findSquareSize(size);
    for(int i = 0; i <= size - 1; i += squareSize){
      for(int j = 0; j <= size - 1; j += squareSize) {
        if(!isSquareCorrect(matrix, i, j, squareSize)){
          return FALSE;
        }
      }
    }

    return TRUE;
  }

  private static int findSquareSize(int size){
    return size == 4? 2: 3;
  }

  static boolean isRowCorrect(int[][] matrix, int rowIndex, int size){
    boolean[] available = new boolean[size];
    for(int i = 0; i <= size - 1; i++){
      if(available[matrix[rowIndex][i] - 1]){
        return false;
      } else {
        available[matrix[rowIndex][i] - 1] = true;
      }
    }
    return true;
  }

  static boolean isColCorrect(int[][] matrix, int colIndex, int size){
    boolean[] available = new boolean[size];
    for(int i = 0; i <= size - 1; i++){
      if(available[matrix[i][colIndex] - 1]){
        return false;
      } else {
        available[matrix[i][colIndex] - 1] = true;
      }
    }
    return true;
  }

  static boolean isSquareCorrect(int[][] matrix, int rowIndex, int colIndex, int size){
    boolean[] available = new boolean[size * size];
    for(int i = rowIndex; i <= rowIndex + size - 1; i++){
      for(int j = colIndex; j <= colIndex + size - 1; j++){
        if(available[matrix[i][j] - 1]){
          return false;
        } else {
          available[matrix[i][j] - 1] = true;
        }
      }
    }
    return true;
  }
}