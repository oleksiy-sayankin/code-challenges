package net.javacogito.passtriangle;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  private static long maxSum = 0;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    int[][] data = new int[100][];
    long[][] sums = new long[100][];
    ;
    int rowCount = 0;
    while ((inputLine = buffer.readLine()) != null) {
      inputLine = inputLine.trim();
      String numbers[] = inputLine.split(" ");
      data[rowCount] = new int[rowCount + 1];
      sums[rowCount] = new long[rowCount + 1];
      for (int i = 0; i <= rowCount; i++) {
        data[rowCount][i] = Integer.parseInt(numbers[i]);
      }
      rowCount++;
    }

    sums[0][0] = data[0][0];
    for (int i = 1; i <= rowCount - 1; i++) {
      for (int j = 0; j <= i; j++) {
        if (j == 0) {
          sums[i][j] = data[i][j] + sums[i - 1][j];
          updateMaxSum(sums[i][j]);
          continue;
        }
        if (j == i) {
          sums[i][j] = data[i][j] + sums[i - 1][j - 1];
          updateMaxSum(sums[i][j]);
          continue;
        }

        long rightSum = sums[i - 1][j];
        long leftSum = sums[i - 1][j - 1];
        if (rightSum > leftSum) {
          sums[i][j] = data[i][j] + rightSum;
        } else {
          sums[i][j] = data[i][j] + leftSum;
        }
        updateMaxSum(sums[i][j]);
      }
    }
    System.out.println(maxSum);
  }

  private static void updateMaxSum(long currentSum) {
    if (currentSum > maxSum) {
      maxSum = currentSum;
    }
  }
}