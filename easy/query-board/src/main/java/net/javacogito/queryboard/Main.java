package net.javacogito.queryboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";
  private static final String SET_COL = "SetCol";
  private static final String QUERY_COL = "QueryCol";
  private static final String SET_ROW = "SetRow";
  private static final String QUERY_ROW = "QueryRow";
  private static final int NUM_ELEM = 256;
  private static byte[][] data = new byte[NUM_ELEM][NUM_ELEM];

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        parseInput(inputLine);
      }
    }
  }

  private static void parseInput(String inputLine){
    String[] rawData = inputLine.split(" ");
    String keyWord = rawData[0];
    if(SET_COL.equals(keyWord)){
      int j = Integer.parseInt(rawData[1]);
      byte x = Byte.parseByte(rawData[2]);
      setCol(j, x);
    }
    if(SET_ROW.equals(keyWord)){
      int i = Integer.parseInt(rawData[1]);
      byte x = Byte.parseByte(rawData[2]);
      setRow(i, x);
    }
    if(QUERY_COL.equals(keyWord)){
      int j = Integer.parseInt(rawData[1]);
      System.out.println(queryCol(j));
    }
    if(QUERY_ROW.equals(keyWord)){
      int i = Integer.parseInt(rawData[1]);
      System.out.println(queryRow(i));
    }
  }

  private static void setCol(int j, byte x){
    for (int i = 0; i <= NUM_ELEM - 1; i++){
      data[i][j] = x;
    }
  }

  private static int queryCol(int j){
    int sum = 0;
    for (int i = 0; i <= NUM_ELEM - 1; i++){
      sum += data[i][j];
    }
    return sum;
  }

  private static void setRow(int i, byte x){
    for (int j = 0; j <= NUM_ELEM - 1; j++){
      data[i][j] = x;
    }
  }

  private static int queryRow(int i){
    int sum = 0;
    for (int j = 0; j <= NUM_ELEM - 1; j++){
      sum += data[i][j];
    }
    return sum;
  }
}