package net.javacogito.football;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toString(findCountries(parseTeams(inputLine))));
      }
    }
  }

  private static int[][] parseTeams(String data){
    String[] rawData = data.split(" \\| ");
    int numRows = rawData.length;
    int result[][] = new int[numRows][];
    for(int i = 0; i <= numRows - 1; i++){
      String[] rawCols = rawData[i].split(" ");
      int numCols = rawCols.length;
      result[i] = new int[numCols];
      for(int j = 0; j <= numCols - 1; j++){
        result[i][j] = Integer.parseInt(rawCols[j]);
      }
    }
    return result;
  }

  private static SortedMap<Integer, SortedSet<Integer>> findCountries(int[][] teams){
    SortedMap<Integer, SortedSet<Integer>> result = new TreeMap<>();
    int numRows = teams.length;
    for(int i = 0; i <= numRows - 1; i++){
      int numCols = teams[i].length;
      for(int j = 0; j <= numCols - 1; j++){
        int team = teams[i][j];
        int country = i + 1;
        if(!result.containsKey(team)){
          result.put(team, new TreeSet<Integer>());
        }
        result.get(team).add(country);
      }
    }
    return result;
  }

  private static String toString(SortedMap<Integer, SortedSet<Integer>> teams){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(Map.Entry<Integer, SortedSet<Integer>> team : teams.entrySet()){
      if(first){
        sb.append(team.getKey());
        sb.append(":");
        sb.append(toString(team.getValue()));
        first = false;
        continue;
      }
      sb.append(" ");
      sb.append(team.getKey());
      sb.append(":");
      sb.append(toString(team.getValue()));
    }
    return sb.toString();
  }

  private static String toString(SortedSet<Integer> countries){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(int country : countries){
      if(first){
        sb.append(country);
        first = false;
        continue;
      }
      sb.append(",");
      sb.append(country);
    }
    sb.append(";");
    return sb.toString();
  }
}