package net.javacogito.meetcombsort;

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
        System.out.println(combSort(parseArray(inputLine)));
      }
    }
  }

  private static int[] parseArray(String data) {
    String[] rawData = data.split(" ");
    int length = rawData.length;
    int[] result = new int[length];
    for (int i = 0; i <= length - 1; i++) {
      result[i] = Integer.parseInt(rawData[i]);
    }
    return result;
  }

  private static int combSort(int[] numbers) {
    double decreaseFactor = 1.25;
    int length = numbers.length;
    int numIterations = 0;
    int range = length;
    while (!isSortedAsc(numbers)) {
      range = (int)Math.floor(((double) range) / decreaseFactor);
      numIterations++;
      for (int i = 0; i <= length - range - 1; i++) {
        int first = numbers[i];
        int second = numbers[i + range];
        if (first > second) {
          numbers[i] = second;
          numbers[i + range] = first;
        }
      }
    }
    return numIterations;
  }

  private static boolean isSortedAsc(int[] numbers) {
    int length = numbers.length;
    for (int i = 0; i <= length - 2; i++) {
      if (numbers[i] > numbers[i + 1]) {
        return false;
      }
    }
    return true;
  }
}