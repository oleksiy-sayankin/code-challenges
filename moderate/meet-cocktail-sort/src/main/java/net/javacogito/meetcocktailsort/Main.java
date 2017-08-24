package net.javacogito.meetcocktailsort;

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
        System.out.println(toString(cocktailSort(parseArray(inputLine), parseLastIteration(inputLine))));
      }
    }
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

  private static int parseLastIteration(String data){
    return Integer.parseInt(data.split(" \\| ")[1]);
  }

  private static int[] cocktailSort(int[] numbers, int lastIteration){
    int startIndex;
    int length = numbers.length;
    int endIndex;
    for(int iteration = 0; iteration <= length / 2; iteration++) {
      if(iteration >= lastIteration){
        return numbers;
      }
      startIndex = iteration;
      endIndex = length - iteration;
      for (int i = startIndex; i <= endIndex - 2; i++) {
        if (numbers[i] > numbers[i + 1]) {
          exchange(numbers, i, i + 1);
        }
      }
      for (int i = endIndex - 2; i >= startIndex; i--) {
        if (numbers[i] > numbers[i + 1]) {
          exchange(numbers, i, i + 1);
        }

      }
    }
    return numbers;
  }

  private static void exchange(int[] numbers, int firstIndex, int secondIndex){
    int temp = numbers[firstIndex];
    numbers[firstIndex] = numbers[secondIndex];
    numbers[secondIndex] = temp;
  }

  private static String toString(int[] numbers){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(int number : numbers){
      if(first){
        sb.append(number);
        first = false;
        continue;
      }
      sb.append(" ");
      sb.append(number);
    }
    return sb.toString();
  }
}