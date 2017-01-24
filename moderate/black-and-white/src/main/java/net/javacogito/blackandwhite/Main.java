package net.javacogito.blackandwhite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      Matrix c = parseMatrix(inputLine);
      int maxSize = c.getSize();
      nextSize:
      for (int size = 1; size <= maxSize; size++) {
        c.setSubMatrixSize(size);
        c.resetPosition();
        int numBlack = c.getSubMatrix().getNumBlack();
        for (SubMatrix m : c) {
          if(numBlack != m.getNumBlack()){
            continue nextSize;
          }
        }
        printResult(size, numBlack);
        break;
      }
    }

  }

  private static Matrix parseMatrix(String inputLine){
    String[] rawData = inputLine.split(" \\| ");
    int size = rawData.length;
    Matrix result = new Matrix(size);
    int i = 0;
    for (String rawRow : rawData) {
      for (int j = 0; j <= size - 1; j++) {
        if(rawRow.charAt(j) == '1'){
          result.enable(i, j);
        }
      }
      i++;
    }
    return result;
  }

  private static void printResult(int size, int numBlack){
    System.out.println(size + "x" + size + ", " + numBlack);
  }

  private static class Matrix implements Iterable<SubMatrix>{
    private final boolean[][] data;
    private final int size;
    private int subMatrixSize = 1;
    private int row = 0;
    private int coll = 0;

    public Matrix(int size){
      this.size = size;
      data = new boolean[size][size];
    }

    public void enable(int i, int j){
      data[i][j] = true;
    }

    @Override
    public Iterator<SubMatrix> iterator() {
      final Matrix mainMatrix = this;
      return new Iterator<SubMatrix>() {
        @Override
        public boolean hasNext() {
          int maxSize = mainMatrix.size - subMatrixSize;
          return (row <= maxSize) && (coll <= maxSize);
        }

        @Override
        public SubMatrix next() {
          SubMatrix next = new SubMatrix(mainMatrix, subMatrixSize, row, coll);
          increasePosition();
          return next;
        }

        @Override
        public void remove() {
        }
      };
    }

    private void increasePosition(){
      coll++;
      int maxSize = this.size - subMatrixSize ;
      if (coll >= maxSize + 1){
        coll = 0;
        row++;
      }
    }

    public SubMatrix getSubMatrix(){
      return new SubMatrix(this, subMatrixSize, row, coll);
    }


    public void setSubMatrixSize(int subMatrixSize){
      this.subMatrixSize = subMatrixSize;
    }

    public int getSize(){
      return size;
    }

    public void resetPosition(){
      row = 0;
      coll = 0;
    }
  }

  private static class SubMatrix{
    private final Matrix parentMatrix;
    private final int size;
    private final int row;
    private final int coll;

    public SubMatrix(Matrix parentMatrix, int size, int row, int coll){
      this.parentMatrix = parentMatrix;
      this.size = size;
      this.row = row;
      this.coll = coll;
    }

    public int getNumBlack(){
      int numBlack = 0;
      for(int i = row; i <= row + size - 1; i++){
        for (int j = coll; j <= coll + size - 1; j++){
          if(parentMatrix.data[i][j]){
            numBlack++;
          }
        }
      }
      return numBlack;
    }
  }
}
