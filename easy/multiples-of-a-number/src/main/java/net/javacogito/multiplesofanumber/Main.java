package net.javacogito.multiplesofanumber;

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
        System.out.println(smallestMultiple(parseX(inputLine), parseN(inputLine)));
      }
    }
  }

  private static int parseX(String data){
    return Integer.parseInt(data.split(",")[0]);
  }

  private static int parseN(String data){
    return Integer.parseInt(data.split(",")[1]);
  }

  private static int smallestMultiple(int x, int n) {
    int smallestMultiple = n;
    int i = 2;
    while (smallestMultiple * i < x){
      i++;
    }
    return smallestMultiple * i;
  }
}