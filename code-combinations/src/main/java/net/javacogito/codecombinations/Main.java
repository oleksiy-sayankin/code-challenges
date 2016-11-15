package net.javacogito.codecombinations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
  private static Set<Character> code = new HashSet<>();

  static {
    code.add('c');
    code.add('o');
    code.add('d');
    code.add('e');
  }


  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      char[][] matrix = parseMatrix(inputLine);
      int rowsCount = matrix.length;
      int colsCount = matrix[0].length;
      int codeCount = 0;
      for (int i = 0; i <= rowsCount - 2; i++) {
        for (int j = 0; j <= colsCount - 2; j++) {
          if (containsCode(getSubMatrix2x2(i, j, matrix))) {
            codeCount++;
          }
        }
      }
      System.out.println(codeCount);
    }
  }

  private static char[][] parseMatrix(String inputLine) {
    String[] rawLines = inputLine.split(" \\| ");
    int rowsCount = rawLines.length;
    int colsCount = rawLines[0].length();
    char[][] matrix = new char[rowsCount][colsCount];
    for (int i = 0; i <= rowsCount - 1; i++) {
      for (int j = 0; j <= colsCount - 1; j++) {
        matrix[i][j] = rawLines[i].charAt(j);
      }
    }
    return matrix;
  }

  private static char[][] getSubMatrix2x2(int i, int j, char[][] matrix) {
    char[][] result = new char[2][2];
    result[0][0] = matrix[i][j];
    result[0][1] = matrix[i][j + 1];
    result[1][0] = matrix[i + 1][j];
    result[1][1] = matrix[i + 1][j + 1];
    return result;
  }

  private static boolean containsCode(char[][] matrix2x2) {
    return code.equals(matrix2x2AsSet(matrix2x2));
  }

  private static Set<Character> matrix2x2AsSet(char[][] matrix2x2) {
    Set<Character> result = new HashSet<>();
    result.add(matrix2x2[0][0]);
    result.add(matrix2x2[0][1]);
    result.add(matrix2x2[1][0]);
    result.add(matrix2x2[1][1]);
    return result;
  }
}