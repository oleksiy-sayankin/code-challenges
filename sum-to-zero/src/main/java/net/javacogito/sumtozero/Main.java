package net.javacogito.sumtozero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      int[] values = parseValues(inputLine);
      System.out.println(calcNumberOfWays(values));
    }
  }


  private static int[] parseValues(String inputLine){
    String[] rawData = inputLine.split(",");
    int length = rawData.length;
    int[] result = new int[length];
    int i = 0;
    for(String value : rawData){
      result[i] = Integer.parseInt(value);
      i++;
    }
    return result;
  }

  private static int calcNumberOfWays(int[] values){
    int numberOfWays = 0;
    int length = values.length;

    for (int i = 0; i <= length - 4; i++){
      for(int j = i + 1; j <= length - 3; j++) {
        for (int k = j + 1; k <= length - 2; k++) {
          for (int n = k + 1; n <= length - 1; n++) {
            if (sum(values[i], values[j], values[k], values[n]) == 0) {
              numberOfWays++;
            }
          }
        }
      }
    }

    return numberOfWays;
  }

  private static int sum(int a, int b, int c, int d){
    return a + b + c + d;
  }
}