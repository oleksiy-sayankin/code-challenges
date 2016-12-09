package net.javacogito.batschallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      int wireLength = parseWireLength(inputLine);
      int distanceToWall = parseDistanceToWall(inputLine);
      int existingBatsNumber = parseExistingBatsNumber(inputLine);
      int[] existingBatsArray = parseExistingBatsArray(inputLine);
      System.out.println(calcAddBats(wireLength, distanceToWall, existingBatsNumber, existingBatsArray));
    }
  }

  private static int parseWireLength(String inputLine){
    String[] rawData = inputLine.split(" ");
    return Integer.parseInt(rawData[0]);
  }

  private static int parseDistanceToWall(String inputLine){
    String[] rawData = inputLine.split(" ");
    return Integer.parseInt(rawData[1]);
  }

  private static int parseExistingBatsNumber(String inputLine){
    String[] rawData = inputLine.split(" ");
    return Integer.parseInt(rawData[2]);
  }

  private static int[] parseExistingBatsArray(String inputLine){
    String[] rawData = inputLine.split(" ");
    int existingBatsNumber = Integer.parseInt(rawData[2]);
    int length = rawData.length;
    int[] existingBatsArray = new int[existingBatsNumber];
    for (int i = 3; i <= length - 1; i++){
      existingBatsArray[i - 3] = Integer.parseInt(rawData[i]);
    }
    Arrays.sort(existingBatsArray);
    return existingBatsArray;
  }

  private static int calcAddBats(int wireLength, int distanceBetweenBats, int existingBatsNumber, int[] existingBatsArray){
    int distanceToWall = 6;
    int addBats = 0;
    if(existingBatsNumber == 0){
      int freeSpace = wireLength - distanceToWall * 2;
      int maxBats = freeSpace / distanceBetweenBats + 1;
      addBats = maxBats - existingBatsNumber;
    } else {
      int startAvailableWire = distanceToWall;
      int endAvailableWire = wireLength - distanceToWall;
      int leftSideBatsNumber = calcLeftSideBats(startAvailableWire, existingBatsArray[0], distanceBetweenBats);
      int rightSideBatsNumber = calcRightSideBats(endAvailableWire, existingBatsArray[existingBatsNumber - 1], distanceBetweenBats);
      int middleBatsNumber = 0;
      for(int i = 0; i <= existingBatsNumber - 2; i++){
        middleBatsNumber += calcMiddleBats(existingBatsArray[i], existingBatsArray[i + 1], distanceBetweenBats);
      }
      addBats = leftSideBatsNumber + middleBatsNumber + rightSideBatsNumber;
    }

    return addBats;
  }

  private static int calcLeftSideBats(int statAvailableWire, int firstBat, int distanceBetweenBats){
    int leftSideFreeSpace = firstBat - statAvailableWire - distanceBetweenBats;
    if(leftSideFreeSpace < 0){
      return 0;
    }
    return leftSideFreeSpace / distanceBetweenBats + 1;
  }

  private static int calcRightSideBats(int endAvailableWire, int lastBat, int distanceBetweenBats){
    int rightSideFreeSpace = endAvailableWire - lastBat  - distanceBetweenBats;
    if(rightSideFreeSpace < 0){
      return 0;
    }
    return rightSideFreeSpace / distanceBetweenBats + 1;
  }

  private static int calcMiddleBats(int leftBat, int rightBat, int distanceBetweenBats){
    int middleFreeSpace = rightBat - leftBat - 2 * distanceBetweenBats;
    if (middleFreeSpace < 0){
      return 0;
    }
    return middleFreeSpace / distanceBetweenBats + 1;
  }
}