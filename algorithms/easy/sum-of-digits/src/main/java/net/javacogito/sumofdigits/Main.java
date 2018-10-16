package net.javacogito.sumofdigits;

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
        System.out.println(sumOfDigits(inputLine));
      }
    }
  }

  private static int sumOfDigits(String data){
    int length = data.length();
    int sum = 0;
    final int base = 48;
    for (int i = 0; i <= length - 1; i++){
      char digit = data.charAt(i);
      sum += (digit - base);

    }
    return sum;
  }
}