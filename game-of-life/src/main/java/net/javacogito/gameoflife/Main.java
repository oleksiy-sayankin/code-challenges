package net.javacogito.gameoflife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  private static byte[][] grid = new byte[100][100];
  private static int elemCount = 0;
  private static final char STAR = '*';
  private static int iterationCount = 10;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    int currentRow = 0;
    while ((inputLine = buffer.readLine()) != null) {
      elemCount = inputLine.length();
      for (int j = 0; j <= elemCount - 1; j++) {
        if (inputLine.charAt(j) == STAR) {
          grid[currentRow][j] = 1;
        }
      }
      currentRow++;
    }
    for (int i = 0; i <= iterationCount - 1; i++) {
      iterate();
    }
    printGrid();
  }

  private static void iterate() {
    byte[][] nextGrid = new byte[elemCount][elemCount];
    for (int i = 0; i <= elemCount - 1; i++) {
      for (int j = 0; j <= elemCount - 1; j++) {
        if (isUnderPopulation(i, j)) {
          nextGrid[i][j] = 0;
        }
        if (isOverCrowding(i, j)) {
          nextGrid[i][j] = 0;
        }
        if (isReproduction(i, j)) {
          nextGrid[i][j] = 1;
        }
        if (isNextGeneration(i, j)) {
          nextGrid[i][j] = 1;
        }
      }
    }
    grid = nextGrid;
  }

  private static boolean isUnderPopulation(int i, int j) {
    return neighbours(i, j) < 2;
  }

  private static boolean isOverCrowding(int i, int j) {
    return neighbours(i, j) > 3;
  }

  private static boolean isReproduction(int i, int j) {
    return isAlive(i, j) == 0 && neighbours(i, j) == 3;
  }

  private static boolean isNextGeneration(int i, int j) {
    return isAlive(i, j) == 1 && (neighbours(i, j) == 2 || neighbours(i, j) == 3);
  }

  private static int neighbours(int i, int j) {
    return isAlive(i, j + 1) + isAlive(i - 1, j + 1) + isAlive(i - 1, j) + isAlive(i - 1, j - 1) + isAlive(i, j - 1)
      + isAlive(i + 1, j - 1) + isAlive(i + 1, j) + isAlive(i + 1, j + 1);
  }

  private static int isAlive(int i, int j) {
    if (i >= 0 && i <= elemCount - 1 && j >= 0 && j <= elemCount - 1) {
      return grid[i][j];
    }
    return 0;
  }


  private static void printGrid() {
    for (int i = 0; i <= elemCount - 1; i++) {
      for (int j = 0; j <= elemCount - 1; j++) {
        if (grid[i][j] == 1) {
          System.out.print("*");
        } else {
          System.out.print(".");
        }
      }
      System.out.println();
    }
  }
}