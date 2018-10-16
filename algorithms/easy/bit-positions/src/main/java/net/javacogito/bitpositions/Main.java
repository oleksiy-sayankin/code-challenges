package net.javacogito.bitpositions;

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
        System.out.println(bitsAreTheSame(parseN(inputLine), parseP1(inputLine), parseP2(inputLine)));
      }
    }
  }

  private static int parseN(String data){
    return Integer.parseInt(data.split(",")[0]);
  }

  private static int parseP1(String data){
    return Integer.parseInt(data.split(",")[1]);
  }

  private static int parseP2(String data){
    return Integer.parseInt(data.split(",")[2]);
  }

  private static boolean bitsAreTheSame(int n, int p1, int p2){
    return ((n >> p1 - 1) & 1) == ((n >> p2 - 1) & 1);
  }
}