package net.javacogito.minimumdistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        int numValues = parseNumValues(inputLine);
        int[] values = parseData(inputLine, numValues);
        int minSum = checkNearByValues(values);
        System.out.println(minSum);
      }
    }
  }


  private static int checkNearByValues(int[] values){
    int minSum = Integer.MAX_VALUE;
    int start = values[0];
    int end = values[values.length - 1];
    for (int i = start; i <= end; i++){
      int currentMin = minSum(values, i);
      if(currentMin < minSum){
        minSum = currentMin;
      }
    }
    return minSum;
  }

  private static int parseNumValues(String data){
    String[] rawData = data.split(" ");
    return Integer.parseInt(rawData[0]);
  }

  private static int minSum(int[] values, int minDistance){
    int minSum = 0;
    for(int value: values){
      minSum += Math.abs(minDistance - value);
    }
    return minSum;
  }

  private static int[] parseData(String data, int numValues){
    String[] rawData = data.split(" ");
    int[] result = new int[numValues];
    for(int i = 1; i <= numValues; i++){
      result[i - 1] = Integer.parseInt(rawData[i]);
    }
    Arrays.sort(result);
    return result;
  }
}