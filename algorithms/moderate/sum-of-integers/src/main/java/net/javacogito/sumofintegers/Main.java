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
    String[] rawData = data.split(",");
    int length = rawData.length;
    int[] result = new int[length];
    for(int i = 0; i <= length - 1; i++){
      result[i] = Integer.parseInt(rawData[i]);
    }
    return result;
  }

  static int maxSum(int[] numbers){
    int length = numbers.length;
    int maxSum = Integer.MIN_VALUE;
    for(int i = 1; i <= length; i++){
      for(int j = 0; j <= length - i; j++){
        int startIndex = j;
        int endIndex = j + i - 1;
        int sum = sum(numbers, startIndex, endIndex);
        if(sum > maxSum){
          maxSum = sum;
        }
      }
    }
    return maxSum;
  }

  private static int sum(int[] numbers, int startIndex, int endIndex){
    int result = 0;
    for(int i = startIndex; i <= endIndex; i++){
      result += numbers[i];
    }
    return result;
  }
}