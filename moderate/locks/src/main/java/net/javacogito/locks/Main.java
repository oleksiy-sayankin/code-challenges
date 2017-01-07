package net.javacogito.locks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      String[] rowData = inputLine.split(" ");
      int numDoors = Integer.parseInt(rowData[0]);
      int numIterations = Integer.parseInt(rowData[1]);
      boolean[] doorStates = new boolean[numDoors];
      for (int i = 0; i <= numIterations - 2; i++){
        for (int j = 1; j <= numDoors - 1; j += 2){
          closeDoor(doorStates, j);
        }
        for (int j = 2; j <= numDoors - 1; j += 3){
          changeDoor(doorStates, j);
        }
      }
      changeDoor(doorStates, numDoors - 1);

      int openDoorCount = 0;
      for (boolean doorState : doorStates){
        if(isOpen(doorState)){
          openDoorCount++;
        }
      }
      System.out.println(openDoorCount);
    }
  }

  private static void closeDoor(boolean[] doorStates, int doorIndex){
    doorStates[doorIndex] = true;
  }

  private static void changeDoor(boolean[] doorStates, int doorIndex){
    doorStates[doorIndex] = !doorStates[doorIndex];
  }

  private static boolean isOpen(boolean doorState){
    return !doorState;
  }
}