package net.javacogito.nmodm;

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
        System.out.println(mod(parseN(inputLine), parseM(inputLine)));
      }
    }
  }

  private static int parseN(String data){
    return Integer.parseInt(data.split(",")[0]);
  }

  private static int parseM(String data){
    return Integer.parseInt(data.split(",")[1]);
  }


  private static int mod(int n, int m){
    int value = n;
    while (value > m){
      value -= m;
    }
    return value;
  }
}