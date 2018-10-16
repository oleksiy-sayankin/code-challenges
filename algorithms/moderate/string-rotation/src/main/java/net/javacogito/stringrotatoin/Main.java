package net.javacogito.stringrotatoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(isRotation(parseFirst(inputLine), parseSecond(inputLine)));
      }
    }
  }

  private static String parseFirst(String data){
    return data.split(",")[0];
  }

  private static String parseSecond(String data){
    return data.split(",")[1];
  }


  private static String isRotation(String first, String second){
    int length = first.length();
    for(int i = 0; i <= length - 1; i++){
      if(first.equals(second)){
        return "True";
      }
      first = rotate(first);
    }
    return "False";
  }

  private static String rotate(String string){
    int length = string.length();
    return string.substring(length - 1, length) + string.substring(0, length - 1);
  }
}