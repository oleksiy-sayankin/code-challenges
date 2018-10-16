package net.javacogito.themajorelement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        int major = findMajor(parseInput(inputLine));
        if(major != Integer.MAX_VALUE){
          System.out.println(major);
        } else{
          System.out.println("None");
        }
      }
    }
  }

  private static int[] parseInput(String inputLine){
    String[] rawData = inputLine.split(",");
    int length = rawData.length;
    int[] result = new int[length];
    for(int i = 0; i <= length - 1; i++){
      result[i] = Integer.parseInt(rawData[i]);
    }
    return result;
  }

  private static int findMajor(int[] numbers){
    int length = numbers.length;
    for(int number : numbers){
      if(findAmount(numbers, number) > length / 2){
        return number;
      }
    }
    return Integer.MAX_VALUE;
  }

  private static int findAmount(int[] numbers, int value){
    int amount = 0;
    for(int number : numbers){
      if (number == value){
        amount++;
      }
    }
    return amount;
  }
}