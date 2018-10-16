package net.javacogito.jollyjumpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";
  private static final String JOLLY = "Jolly";
  private static final String NOT_JOLLY = "Not jolly";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        if(isJollyJumper(parseValues(inputLine), parseNumElements(inputLine))){
          System.out.println(JOLLY);
        }
        else {
          System.out.println(NOT_JOLLY);
        }
      }
    }
  }

  private static int parseNumElements(String inputLine){
    String[] rawData = inputLine.split(" ");
    return Integer.parseInt(rawData[0]);
  }

  private static int[] parseValues(String inputLine){
    String[] rawData = inputLine.split(" ");
    int length = rawData.length;
    int[] values = new int[length - 1];
    for(int i = 1; i <= length - 1; i++){
      values[i - 1] = Integer.parseInt(rawData[i]);
    }
    return values;
  }

  private static boolean isJollyJumper(int[] values, int numElements){
    if(numElements == 1){
      return true;
    }
    boolean [] elements = new boolean[numElements];
    elements[0] = true;
    for(int i = 0; i <= numElements - 2; i++){
      int index = Math.abs(values[i + 1] - values[i]);
      if(index > numElements - 1){
        return false;
      }
      elements[index] = true;
    }

    for(boolean element : elements){
      if(!element){
        return false;
      }
    }
    return true;
  }
}