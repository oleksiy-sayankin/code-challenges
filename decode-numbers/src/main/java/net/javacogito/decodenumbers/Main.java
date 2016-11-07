package net.javacogito.decodenumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  private static int numberOfWays = 0;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      decode(inputLine);
      System.out.println(numberOfWays);
      numberOfWays = 0;
    }
  }

  private static void decode(String line){
    int length = line.length();
    if(length == 0){
      numberOfWays++;
      return;
    }

    if(length == 1){
      if(Integer.parseInt(line) > 0) {
        decode(removeFistSingleDigit(line));
      }
      return;
    }

    if(Integer.parseInt(getFirstSingleDigit(line)) > 0) {
      decode(removeFistSingleDigit(line));
    }

    if(Integer.parseInt(getFirstTwoDigit(line)) <= 26){
      decode(removeFistTwoDigit(line));
    }
  }

  private static String getFirstSingleDigit(String data){
    return data.substring(0, 1);
  }

  private static String getFirstTwoDigit(String data){
    return data.substring(0, 2);
  }

  private static String removeFistSingleDigit(String data){
    return data.substring(1);
  }

  private static String removeFistTwoDigit(String data){
    return data.substring(2);
  }

}