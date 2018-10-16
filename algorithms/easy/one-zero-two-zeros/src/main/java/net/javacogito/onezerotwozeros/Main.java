package net.javacogito.onezerotwozeros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        int upperLimit = parseUpperLimit(inputLine);
        int numZeros = parseNumZeros(inputLine);
        System.out.println(numNumbersWithZeros(upperLimit, numZeros));
      }
    }
  }

  private static int parseUpperLimit(String data){
    return Integer.parseInt(data.split(" ")[1]);
  }

  private static int parseNumZeros(String data){
    return Integer.parseInt(data.split(" ")[0]);
  }


  private static int numZeros(String binary){
    int length = binary.length();
    int numZeros = 0;
    for(int i = 0; i <= length - 1; i++){
      if(binary.charAt(i) == '0'){
        numZeros++;
      }
    }
    return numZeros;
  }

  private static int numNumbersWithZeros(int upperLimit, int numZeros){
    int numNumbersWithZeros = 0;
    for(int i = 1; i <= upperLimit; i++){
      if(numZeros(Integer.toBinaryString(i)) == numZeros){
        numNumbersWithZeros++;
      }
    }
    return numNumbersWithZeros;
  }
}