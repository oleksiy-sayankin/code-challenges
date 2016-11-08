package net.javacogito.sortmatrixcolumns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
  private static final int MIN = -100;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      String[] rows = inputLine.split("\\|");
      int elemCount = rows.length;
      int[][] elem = new int[elemCount][elemCount];
      int currentRow = 0;
      for (String row : rows) {
        String[] elemsAsString = row.trim().split(" ");
        int currentCol = 0;
        for (String elemAsString : elemsAsString) {
          elem[currentRow][currentCol] = Integer.parseInt(elemAsString);
          currentCol++;
        }
        currentRow++;
      }

      String[] colsAsString = new String[elemCount];

      for (int i = 0; i <= elemCount - 1; i++) {
        String colAsString = "";
        for (int j = 0; j <= elemCount - 1; j++) {
          colAsString += String.format("%03d", elem[j][i] - MIN);
          if (j < elemCount - 1) {
            colAsString += " ";
          }
        }
        colsAsString[i] = colAsString;
      }

      Arrays.sort(colsAsString);

      for (int j = 0; j <= elemCount - 1; j++) {
        String[] elemsAsString = colsAsString[j].split(" ");
        for (int i = 0; i <= elemCount - 1; i++) {
          elem[i][j] = Integer.parseInt(elemsAsString[i]) + MIN;
        }
      }

      for (int i = 0; i <= elemCount - 1; i++) {
        for (int j = 0; j <= elemCount - 1; j++) {
          if (i != 0 || j != 0) {
            System.out.print(" ");
          }
          System.out.print(elem[i][j]);
        }
        if (i < elemCount - 1) {
          System.out.print(" |");
        }
      }

      System.out.println();
    }
  }
}