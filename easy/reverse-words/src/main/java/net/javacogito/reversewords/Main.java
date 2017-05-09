package net.javacogito.reversewords;

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
        System.out.println(reverse(inputLine));
      }
    }
  }

  private static String reverse (String data){
    String[] input =  data.trim().split(" ");
    StringBuilder sb = new StringBuilder();
    int length = input.length;
    for(int i = length - 1; i >= 0; i--){
      if(length - 1 == i){
        sb.append(input[i]);
      } else {
        sb.append(" ").append(input[i]);
      }
    }
    return  sb.toString();
  }
}

