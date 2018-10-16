package net.javacogito.numberpairs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;

import java.util.TreeMap;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toString(findPairs(parseNumbers(inputLine), parseSum(inputLine))));
      }
    }
  }

  private static int[] parseNumbers(String data){
    String[] rawData = data.split(";")[0].split(",");
    int length = rawData.length;
    int[] result = new int[length];
    for(int i = 0; i <= length - 1; i++){
      result[i] = Integer.parseInt(rawData[i]);
    }
    return result;
  }

  private static int parseSum(String data){
    return Integer.parseInt(data.split(";")[1]);
  }

  private static Map<Integer, Integer> findPairs(int[] numbers, int sum){
    SortedMap<Integer, Integer> result = new TreeMap<>();
    int length = numbers.length;
    for(int i = 0; i <= length - 2; i++){
      for(int j = i + 1; j <= length - 1; j++){
        int a = numbers[i];
        int b = numbers[j];
        if(a + b == sum && !contains(result, a, b)){
          result.put(a, b);
        }
      }
    }
    return result;
  }

  private static boolean contains(Map<Integer, Integer> pairs, int i, int j){
    if((pairs.containsKey(i) && pairs.get(i) == j) ||
      (pairs.containsKey(j) && pairs.get(j) == i)) return true;
    return false;
  }

  private static String toString(Map<Integer, Integer> pairs){
    if(pairs.isEmpty()){
      return "NULL";
    }
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(Map.Entry<Integer, Integer> pair : pairs.entrySet()){
      if(first){
        sb.append(toString(pair));
        first = false;
        continue;
      }
      sb.append(";");
      sb.append(toString(pair));
    }
    return sb.toString();
  }

  private static String toString(Map.Entry<Integer, Integer> pair){
    return pair.getKey() + "," + pair.getValue();
  }
}