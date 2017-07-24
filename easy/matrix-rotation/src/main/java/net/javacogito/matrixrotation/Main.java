package net.javacogito.matrixrotation;

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
        System.out.println(toString(rotate(parseMatrix(inputLine))));
      }
    }
  }

  private static char[][] parseMatrix(String data){
    char[] symbols = data.replaceAll(" ", "").toCharArray();
    int length = (int) Math.sqrt(symbols.length);
    char[][] matrix = new char[length][length];
    int index = 0;
    for(int i = 0; i <= length - 1; i++){
      for (int j = 0; j <= length - 1; j++){
        matrix[i][j] = symbols[index];
        index++;
      }
    }
    return matrix;
  }

  private static char[][] rotate(char[][] matrix){
    int length = matrix.length;
    char[][] rotated =  new char[length][length];
    for(int i = 0; i <= length - 1; i++){
      for (int j = 0; j <= length - 1; j++){
        rotated[j][length - i - 1] = matrix[i][j];
      }
    }
    return rotated;
  }

  private static String toString(char[][] matrix){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (char[] row : matrix){
      for (char symbol : row){
        if(first){
          first = false;
          sb.append(symbol);
          continue;
        }
        sb.append(" ");
        sb.append(symbol);
      }
    }
    return sb.toString();
  }
}