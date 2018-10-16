package net.javacogito.maxrangesum;

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
        System.out.println(maxRangeSum(parseNumDays(inputLine), parseEarnings(inputLine)));
      }
    }
  }

  private static int parseNumDays(String data){
    return Integer.parseInt(data.split(";")[0]);
  }

  private static int[] parseEarnings(String data){
    String[] rawData = data.split(";")[1].split(" ");
    int length = rawData.length;
    int[] earnings = new int[length];
    for(int i = 0; i <= length - 1; i++){
      earnings[i] = Integer.parseInt(rawData[i]);
    }
    return earnings;
  }

  private static int maxRangeSum(int numDays, int[] earnings){
    int maxSum = 0;
    int endDay = earnings.length - numDays;
    for(int i = 0; i <= endDay; i++){
      int currentSum = sum(earnings, i, numDays);
      if (currentSum > maxSum){
        maxSum = currentSum;
      }
    }
    return maxSum < 0 ? 0 : maxSum;
  }

  private static int sum(int[] earnings, int startDay, int numDays){
    int sum = 0;
    int length = earnings.length;
    int endDay = startDay + numDays <= length ? startDay + numDays : length;
    for(int i = startDay; i <= endDay - 1; i++){
      sum += earnings[i];
    }
    return sum;
  }
}