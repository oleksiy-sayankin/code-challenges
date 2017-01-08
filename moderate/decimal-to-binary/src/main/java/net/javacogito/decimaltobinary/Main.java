package net.javacogito.decimaltobinary;

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
      if(!EMPTY.equals(inputLine.trim())){
        int value = Integer.parseInt(inputLine);
        System.out.println(Integer.toBinaryString(value));
      }
    }
  }
}