package net.javacogito.notsoclever;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
  private static final String EMPTY = "";
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toString(sort(parseArray(inputLine), parseMaxIteration(inputLine))));
      }
    }
  }

  private static int parseMaxIteration(String data){
    return Integer.parseInt(data.split(" \\| ")[1]);
  }

  private static int[] parseArray(String data){
    String[] rawData = data.split(" \\| ")[0].split(" ");
    int length = rawData.length;
    int[] result = new int[length];
    for(int i = 0; i <= length - 1; i++){
      result[i] = Integer.parseInt(rawData[i]);
    }
    return result;
  }

  private static int[] sort(int[] array, int maxIteration){
    int currentIteration = 1;
    int length = array.length;
    int[] result = Arrays.copyOf(array, length);
    while (currentIteration <= maxIteration) {
      boolean hasChange = false;
      for (int i = 0; i <= length - 2; i++) {
        if (result[i] > result[i + 1]) {
          int temp = result[i];
          result[i] = result[i + 1];
          result[i + 1] = temp;
          currentIteration++;
          hasChange = true;
          break;
        }
      }
      if(!hasChange){
        break;
      }
    }
    return result;
  }

  private static String toString(int[] numbers){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(int number : numbers){
      if(first){
        sb.append(Integer.toString(number));
        first = false;
        continue;
      }
      sb.append(" ");
      sb.append(Integer.toString(number));
    }
    return  sb.toString();
  }
}