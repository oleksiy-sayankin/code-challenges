package net.javacogito.datarecovery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        System.out.println(recover(inputLine));
      }
    }
  }

  private static String recover(String data){
    List<String> words = Arrays.asList(data.split(";")[0].split(" "));
    List<String> positions = Arrays.asList(data.split(";")[1].split(" "));
    int length = words.size();
    List<String> unusedPositions = new ArrayList<>();
    List<String> unusedWords = new ArrayList<>(words);
    for(int i = 1; i <= length; i++){
      unusedPositions.add(Integer.toString(i));
    }
    SortedMap<Integer, String> result = new TreeMap<>();

    for (int i = 0; i <= length - 2; i++){
      int position = Integer.parseInt(positions.get(i));
      String word = words.get(i);
      result.put(position, word);
      unusedPositions.remove(positions.get(i));
      unusedWords.remove(word);
    }

    result.put(Integer.parseInt(unusedPositions.get(0)), unusedWords.get(0));
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(String word : result.values()){
      if(first){
        first = false;
        sb.append(word);
        continue;
      }
      sb.append(" ").append(word);
    }
    return sb.toString();
  }
}