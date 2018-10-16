package net.javacogito.arrayabsurdity;

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
        System.out.println(findDuplicate(parseArray(inputLine)));
      }
    }
  }

  private static int[] parseArray(String data){
    String[] rawData = data.split(";")[1].split(",");
    int length = rawData.length;
    int[] result = new int[length];
    for(int i = 0; i <= length - 1; i++){
      result[i] = Integer.parseInt(rawData[i]);
    }
    return result;
  }

  static int findDuplicate(int[] numbers){
    int n = numbers.length + 1;
    boolean[] available = new boolean[n];
    for(int number : numbers) {
      if(available[number]){
        return number;
      } else {
        available[number] = true;
      }
    }
    return 0;
  }
}