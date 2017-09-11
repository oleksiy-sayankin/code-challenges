package net.javacogito.sumofintegers;

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
        System.out.println(maxSum(parseArray(inputLine)));
      }
    }
  }

  private static int[] parseArray(String data){
    return new int[0];
  }

  private static int maxSum(int[] numbers){
    return 0;
  }
}