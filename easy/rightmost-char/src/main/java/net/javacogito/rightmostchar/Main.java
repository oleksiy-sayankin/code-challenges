package net.javacogito.rightmostchar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(rightmostChar(parseString(inputLine), parseChar(inputLine)));
      }
    }
  }

  private static String parseString(String data){
    return data.split(",")[0];
  }

  private static char parseChar(String data){
    return data.split(",")[1].charAt(0);
  }

  private static int rightmostChar(String data, char symbol) {
    int index = data.length() - 1;
    while(index >= 0){
      if(data.charAt(index) == symbol){
        return index;
      }
      index--;
    }
    return -1;
  }
}