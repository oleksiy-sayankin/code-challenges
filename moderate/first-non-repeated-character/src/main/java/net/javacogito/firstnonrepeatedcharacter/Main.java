package net.javacogito.firstnonrepeatedcharacter;

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
        String data = inputLine;
        while (isRepeated(data)){
          data = data.replaceAll(Character.toString(data.charAt(0)), "");
        }
        System.out.println(data.charAt(0));
      }
    }
  }

  private static boolean isRepeated(String data){
    return data.indexOf(data.charAt(0), 1) != -1;
  }
}