package net.javacogito.racingchars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static final int MAX_NUM_ROWS = 50;
  private static final int NUM_COLS = 12;
  private static final String NL = "\n";
  private static final char GATE = '_';
  private static final char CHECKPOINT = 'C';
  private static final char STRAIGHT = '|';
  private static final char LEFT_TURN = '/';
  private static final char RIGHT_TURN = '\\';
  private static int numRows = 0;
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    final String[] track = new String[MAX_NUM_ROWS];
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        track[numRows] = inputLine;
        numRows++;
      }
    }
    System.out.println(resultToString(findWay(track)));
  }

  private static String[] findWay(String[] track){
    String[] way = new String[numRows];
    int gateIndex = findGateIndex(track[0]);
    way[0] = putCharAt(track[0], STRAIGHT, gateIndex);
    int wayIndex = gateIndex;
    for(int i = 1; i <= numRows - 1; i++){
      int checkpointIndex = findCheckpointIndex(track[i]);
      gateIndex = findGateIndex(track[i]);
      if(canReachCheckpoint(wayIndex, checkpointIndex)){
        way[i] = buildNewWay(track[i], wayIndex, checkpointIndex);
        wayIndex = checkpointIndex;
        continue;
      }
      way[i] = buildNewWay(track[i], wayIndex, gateIndex);
      wayIndex = gateIndex;
    }
    return way;
  }

  private static String buildNewWay(String row, int wayIndex, int newWayIndex){
    boolean isLeft = wayIndex - 1 == newWayIndex;
    boolean isRight = wayIndex + 1 == newWayIndex;
    if(isLeft){
      return putCharAt(row, LEFT_TURN, newWayIndex);
    }
    if(isRight){
      return putCharAt(row, RIGHT_TURN, newWayIndex);
    }
    return putCharAt(row, STRAIGHT, newWayIndex);
  }

  private static boolean canReachCheckpoint(int wayIndex, int checkpointIndex){
    return (wayIndex == checkpointIndex) || (wayIndex - 1 == checkpointIndex) || (wayIndex + 1 == checkpointIndex);
  }

  private static int findGateIndex(String row){
    char[] rowAsChars = row.toCharArray();
    for(int i = 0; i <= NUM_COLS - 1; i++){
      if(rowAsChars[i] == GATE) return i;
    }
    return -1;
  }

  private static int findCheckpointIndex(String row){
    char[] rowAsChars = row.toCharArray();
    for(int i = 0; i <= NUM_COLS - 1; i++){
      if(rowAsChars[i] == CHECKPOINT) return i;
    }
    return -1;
  }

  private static String putCharAt(String row, char value, int index){
    return row.substring(0, index) + Character.toString(value) + row.substring(index + 1, row.length());
  }

  private static String resultToString(String[] result){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= numRows - 1; i++){
      sb.append(result[i]);
      sb.append(NL);
    }
    return sb.toString();
  }
}