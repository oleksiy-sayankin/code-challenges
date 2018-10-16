package net.javacogito.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static final String[] RESOLUTIONS = {"Done","Low", "Medium", "High", "Critical"};

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(test(inputLine));
      }
    }
  }

  private static String test(String data) {
    return buildResolution(findNumBugs(parseExpected(data), parseActual(data)));
  }

  private static String parseActual(String data){
    return data.split(" \\| ")[0];
  }

  private static String parseExpected(String data){
    return data.split(" \\| ")[1];
  }

  private static int findNumBugs(String expected, String actual){
    int length = expected.length();
    int numBugs = 0;
    for(int i = 0; i <= length - 1; i++){
      if(expected.charAt(i) != actual.charAt(i)){
        numBugs++;
      }
    }
    return numBugs;
  }

  private static String buildResolution(int numBugs){
    if(numBugs == 0){
      return RESOLUTIONS[0];
    }
    if(numBugs <= 2){
      return RESOLUTIONS[1];
    }
    if(numBugs <= 4){
      return RESOLUTIONS[2];
    }
    if(numBugs <= 6){
      return RESOLUTIONS[3];
    }
    return RESOLUTIONS[4];
  }
}