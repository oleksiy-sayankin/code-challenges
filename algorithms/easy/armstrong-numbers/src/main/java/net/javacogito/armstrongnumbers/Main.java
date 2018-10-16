package net.javacogito.armstrongnumbers;

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
        System.out.println(isArmstrongNumber(inputLine));
      }
    }
  }

  private static String isArmstrongNumber(String data) {
    int value = Integer.parseInt(data);
    int n = data.length();
    if(value == sumNthDigit(data, n)){
      return "True";
    }
    return "False";
  }

  private static int sumNthDigit(String valueAsString, int n){
    int sum = 0;
    int length = valueAsString.length();
    final int base = 48;
    for(int i = 0; i <= length - 1; i++){
      int digit = valueAsString.charAt(i) - base;
      sum += power(digit, n);
    }
    return sum;
  }

  private static int power(int n, int p){
    int result = 1;
    for(int i = 0; i <= p - 1; i++){
      result *= n;
    }
    return result;
  }

}