package net.javacogito.apileofbricks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {

      String[] rowData = inputLine.split("\\|");
      String[] holeRawData = rowData[0].split(" ");
      String startHoleData = holeRawData[0].substring(1, holeRawData[0].length() - 1);
      String endHoleData = holeRawData[1].substring(1, holeRawData[1].length() - 1);

      int xStartHole = Integer.parseInt(startHoleData.split(",")[0]);
      int yStartHole = Integer.parseInt(startHoleData.split(",")[1]);

      int xEndHole = Integer.parseInt(endHoleData.split(",")[0]);
      int yEndHole = Integer.parseInt(endHoleData.split(",")[1]);

      Hole hole = new Hole(xStartHole, yStartHole, xEndHole, yEndHole);


      String[] bricksRawData = rowData[1].split(";");
      Brick[] bricks = new Brick[15];

      int bricksCount = 0;
      for (String brickRawData : bricksRawData) {
        String[] brickSplittedData = brickRawData.substring(1, brickRawData.length() - 1).split(" ");
        int index = Integer.parseInt(brickSplittedData[0]);

        String startBrickData = brickSplittedData[1].substring(1, brickSplittedData[1].length() - 1);
        int xStartBrick = Integer.parseInt(startBrickData.split(",")[0]);
        int yStartBrick = Integer.parseInt(startBrickData.split(",")[1]);
        int zStartBrick = Integer.parseInt(startBrickData.split(",")[2]);

        String endBrickData = brickSplittedData[2].substring(1, brickSplittedData[2].length() - 1);
        int xEndBrick = Integer.parseInt(endBrickData.split(",")[0]);
        int yEndBrick = Integer.parseInt(endBrickData.split(",")[1]);
        int zEndBrick = Integer.parseInt(endBrickData.split(",")[2]);

        bricks[bricksCount] = new Brick(index, xStartBrick, yStartBrick, zStartBrick, xEndBrick, yEndBrick, zEndBrick);
        bricksCount++;
      }

      List<Integer> passedBricksIndexes = new ArrayList<Integer>();

      for (int i = 0; i <= bricksCount - 1; i++) {
        if (bricks[i].canPass(hole)) {
          passedBricksIndexes.add(bricks[i].getIndex());
        }
      }

      Collections.sort(passedBricksIndexes);

      if (passedBricksIndexes.isEmpty()) {
        System.out.println("-");
        continue;
      }

      boolean isFirst = true;
      for (Integer brickIndex : passedBricksIndexes) {
        if (isFirst) {
          System.out.print(brickIndex);
          isFirst = false;
          continue;
        }
        System.out.print("," + brickIndex);
      }

      System.out.println();
    }
  }

  private static class Hole {
    private final int xStart;
    private final int yStart;
    private final int xEnd;
    private final int yEnd;

    public Hole(int xStart, int yStart, int xEnd, int yEnd) {
      this.xStart = xStart;
      this.yStart = yStart;
      this.xEnd = xEnd;
      this.yEnd = yEnd;
    }

    public int getxLength() {
      return Math.abs(xEnd - xStart);
    }

    public int getyLength() {
      return Math.abs(yEnd - yStart);
    }

  }

  private static class Brick {
    private final int index;
    private final Side[] sides = new Side[3];

    public Brick(int index, int xStart, int yStart, int zStart, int xEnd, int yEnd, int zEnd) {
      this.index = index;
      sides[0] = new Side(xStart, yStart, xEnd, yEnd);
      sides[1] = new Side(xStart, zStart, xEnd, zEnd);
      sides[2] = new Side(yStart, zStart, yEnd, zEnd);

    }

    public int getIndex() {
      return index;
    }

    public boolean canPass(Hole hole) {
      boolean[] isSideCanPass = new boolean[3];
      isSideCanPass[0] = (hole.getxLength() >= sides[0].getxLength() && hole.getyLength() >= sides[0].getyLength()) ||
        (hole.getxLength() >= sides[0].getyLength() && hole.getyLength() >= sides[0].getxLength());
      isSideCanPass[1] = (hole.getxLength() >= sides[1].getxLength() && hole.getyLength() >= sides[1].getyLength()) ||
        (hole.getxLength() >= sides[1].getyLength() && hole.getyLength() >= sides[1].getxLength());
      isSideCanPass[2] = (hole.getxLength() >= sides[2].getxLength() && hole.getyLength() >= sides[2].getyLength()) ||
        (hole.getxLength() >= sides[2].getyLength() && hole.getyLength() >= sides[2].getxLength());

      return isSideCanPass[0] || isSideCanPass[1] || isSideCanPass[2];
    }

    private static class Side {
      private final int xStart;
      private final int yStart;
      private final int xEnd;
      private final int yEnd;

      public Side(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
      }

      public int getxLength() {
        return Math.abs(xEnd - xStart);
      }

      public int getyLength() {
        return Math.abs(yEnd - yStart);
      }
    }
  }
}