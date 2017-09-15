package net.javacogito.sudoku;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  @Test
  public void mainTest() throws IOException {
    long startTime = System.currentTimeMillis();
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = {url.getPath()};
    Main.main(input);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }

  @Test
  public void checkRowTrueTest(){
    int[][] matrix01 = {{1,4,2,3}, {2,3,1,4}, {4,2,3,1}, {3,1,4,2}};
    int size01 = 4;
    Assert.assertTrue(Main.isRowCorrect(matrix01, 0, size01));
    Assert.assertTrue(Main.isRowCorrect(matrix01, 1, size01));
    Assert.assertTrue(Main.isRowCorrect(matrix01, 2, size01));
    Assert.assertTrue(Main.isRowCorrect(matrix01, 3, size01));
  }


  @Test
  public void checkSudokuTest(){
    int[][] matrix01 = {{1,4,2,3}, {2,3,1,4}, {4,2,3,1}, {3,1,4,2}};
    int[][] matrix02 = {{4,1,2,3}, {2,3,1,4}, {4,2,3,1}, {3,1,4,2}};
    int[][] matrix03 = {{2,1,3,2}, {3,2,1,4}, {1,4,2,3}, {2,3,4,1}};
    int size = 4;
    Assert.assertEquals("True", Main.checkSudoku(matrix01, size));
    Assert.assertEquals("False", Main.checkSudoku(matrix02, size));
    Assert.assertEquals("False", Main.checkSudoku(matrix03, size));
  }



  @Test
  public void checkRowFalseTest(){
    int[][] matrix01 = {{4,1,2,4}, {1,3,1,4}, {3,2,3,1}, {2,1,4,2}};
    int size01 = 4;
    Assert.assertFalse(Main.isRowCorrect(matrix01, 0, size01));
    Assert.assertFalse(Main.isRowCorrect(matrix01, 1, size01));
    Assert.assertFalse(Main.isRowCorrect(matrix01, 2, size01));
    Assert.assertFalse(Main.isRowCorrect(matrix01, 3, size01));
  }


  @Test
  public void checkColTrueTest(){
    int[][] matrix01 = {{1,4,2,3}, {2,3,1,4}, {4,2,3,1}, {3,1,4,2}};
    int size01 = 4;
    Assert.assertTrue(Main.isColCorrect(matrix01, 0, size01));
    Assert.assertTrue(Main.isColCorrect(matrix01, 1, size01));
    Assert.assertTrue(Main.isColCorrect(matrix01, 2, size01));
    Assert.assertTrue(Main.isColCorrect(matrix01, 3, size01));
  }

  @Test
  public void checkColFalseTest(){
    int[][] matrix01 = {{2,4,2,3}, {2,4,1,4}, {2,2,2,1}, {3,1,4,4}};
    int size01 = 4;
    Assert.assertFalse(Main.isColCorrect(matrix01, 0, size01));
    Assert.assertFalse(Main.isColCorrect(matrix01, 1, size01));
    Assert.assertFalse(Main.isColCorrect(matrix01, 2, size01));
    Assert.assertFalse(Main.isColCorrect(matrix01, 3, size01));
  }


  @Test
  public void checkSquareTrueTest(){
    int[][] matrix01 = {{1,4,2,3}, {2,3,1,4}, {4,2,3,1}, {3,1,4,2}};
    int size01 = 2;
    Assert.assertTrue(Main.isSquareCorrect(matrix01, 0, 0, size01));
    Assert.assertTrue(Main.isSquareCorrect(matrix01, 2, 0, size01));
    Assert.assertTrue(Main.isSquareCorrect(matrix01, 0, 2, size01));
    Assert.assertTrue(Main.isSquareCorrect(matrix01, 2, 2, size01));
  }
}