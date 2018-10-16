package net.javacogito.comparepoints;

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
        System.out.println(findDirection(parsePoints(inputLine)));
      }
    }
  }

  private static String findDirection(int[] values){
    int xStart = values[0];
    int yStart = values[1];
    int xEnd = values[2];
    int yEnd = values[3];
    int deltaX = xEnd - xStart;
    int deltaY = yEnd - yStart;

    if(deltaX > 0 && deltaY > 0){
      return "NE";
    }
    if(deltaX < 0 && deltaY > 0){
      return "NW";
    }
    if(deltaX < 0 && deltaY < 0){
      return "SW";
    }
    if(deltaX > 0 && deltaY < 0){
      return "SE";
    }

    if(deltaX == 0 && deltaY > 0){
      return "N";
    }
    if(deltaX == 0 && deltaY < 0){
      return "S";
    }
    if(deltaX > 0 && deltaY == 0){
      return "E";
    }
    if(deltaX < 0 && deltaY == 0){
      return "W";
    }
    return "here";
  }

  private static int[] parsePoints(String data){
    String[] rawData = data.split(" ");
    int length = rawData.length;
    int[] result = new int[length];
    for(int i = 0; i <= length - 1; i++){
      result[i] = Integer.parseInt(rawData[i]);
    }
    return result;
  }
}