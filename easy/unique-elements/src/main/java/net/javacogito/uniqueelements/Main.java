package net.javacogito.uniqueelements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(makeUnique(inputLine));
      }
    }
  }

  private static String makeUnique(String data){
    String[] elements = data.split(",");
    SortedSet<Integer> set =  new TreeSet<>();
    for(String element : elements){
      set.add(Integer.parseInt(element));
    }
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(Integer element : set){
      if(first){
        sb.append(element);
        first = false;
      } else {
        sb.append(",").append(element);
      }
    }
    return sb.toString();
  }
}