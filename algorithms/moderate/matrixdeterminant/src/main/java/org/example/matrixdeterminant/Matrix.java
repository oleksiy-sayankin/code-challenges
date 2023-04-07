package org.example.matrixdeterminant;

public class Matrix {
  public static int determinant(int[][] matrix) {
    return det(matrix);
  }


  private static int det(int[][] m) {
    int length = m.length;
    if (length == 1) {
      return m[0][0];
    }
    if (length == 2) {
      return m[0][0] * m[1][1] - m[0][1] * m[1][0];
    }
    int sum = 0;
    for (int i = 0; i <= length - 1; i++) {
      int sign = i % 2 == 0 ? 1 : -1;
      sum += sign * m[0][i] * det(minor(m, i));
    }
    return sum;
  }


  private static int[][] minor(int[][] m, int column) {
    int length = m.length;
    int[][] result = new int[length - 1][];
    for (int i = 0; i <= length - 2; i++) {
      result[i] = new int[length - 1];
      for (int j = 0; j <= length - 2; j++) {
        int sourceI = i + 1;
        int sourceJ = j >= column ? j + 1 : j;
        result[i][j] = m[sourceI][sourceJ];
      }
    }
    return result;
  }
}
