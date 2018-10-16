package net.javacogito.filenamepattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toString(filter(parsePattern(inputLine), parseFiles(inputLine))));
      }
    }
  }

  private static String parsePattern(String data){
    return data.split(" ")[0];
  }

  private static String[] parseFiles(String data){
    String[] rawData = data.split(" ");
    return Arrays.copyOfRange(rawData, 1, rawData.length);
  }

  private static String toString(List<String> files){
    if(files.isEmpty()){
      return "-";
    }
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (String file : files){
      if(first){
        sb.append(file);
        first = false;
        continue;
      }
      sb.append(" ");
      sb.append(file);
    }
    return sb.toString();
  }

  static List<String> filter(String pattern, String[] files){
    List<String> result = new ArrayList<>();
    for (String file : files){
      if(file.matches(buildRegExpPattern(pattern))){
        result.add(file);
      }
    }
    return result;
  }

  static String buildRegExpPattern(String pattern){
    String result = pattern.replaceAll("\\.", "\\\\.");
    result = result.replaceAll("\\*", ".*");
    result = result.replaceAll("\\?", ".");
    result = result + "\\b";
    return result;
  }
}