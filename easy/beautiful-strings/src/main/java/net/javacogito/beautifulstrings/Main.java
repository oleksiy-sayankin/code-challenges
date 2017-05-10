package net.javacogito.beautifulstrings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

  public static final String EMPTY = "";


  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(maxBeauty(inputLine));
      }
    }
  }

  private static int maxBeauty(String data) {
    Map<Character, Integer> frequency =  new HashMap<>();
    String input = preProcess(data);
    int length = input.length();
    for(int i = 0; i <= length - 1; i++){
      char symbol = input.charAt(i);
      if(frequency.containsKey(symbol)){
        int value = frequency.get(symbol);
        frequency.put(symbol, ++value);
      } else{
        frequency.put(symbol, 1);
      }
    }

    List<Map.Entry<Character, Integer>> frequencyAsList = new ArrayList<>(frequency.entrySet());
    Collections.sort(frequencyAsList, new Comparator<Map.Entry<Character, Integer>>(){
      @Override
      public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
        return o2.getValue() - o1.getValue();
      }
    });
    int result = 0;
    int currentBeauty = 26;
    for(Map.Entry<Character, Integer> entry : frequencyAsList){
      result += currentBeauty * entry.getValue();
      currentBeauty--;
    }
    return result;
  }

  private static String preProcess(String data){
    return data.toLowerCase().replaceAll("[^a-z]*", "");
  }
}