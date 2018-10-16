package net.javacogito.setintersection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
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
        System.out.println(setToString(intersect(parseSet(parseA(inputLine)), parseSet(parseB(inputLine)))));
      }
    }
  }

  private static String parseA(String data) {
    return data.split(";")[0];
  }

  private static String parseB(String data) {
    return data.split(";")[1];
  }

  private static Set<Integer> parseSet(String data){
    String[] elements = data.split(",");
    SortedSet<Integer> set = new TreeSet<>();
    for (String element : elements) {
      set.add(Integer.parseInt(element));
    }
    return set;
  }

  private static Set<Integer> intersect(Set<Integer> a, Set<Integer> b){
    Set<Integer> result = new TreeSet<>();
    Set<Integer> aMinusB = new TreeSet<>(a);
    Set<Integer> bMinusA = new TreeSet<>(b);
    result.addAll(a);
    result.addAll(b);
    aMinusB.removeAll(b);
    bMinusA.removeAll(a);
    result.removeAll(aMinusB);
    result.removeAll(bMinusA);
    return result;
  }

  private static String setToString(Set<Integer> set){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (Integer element : set) {
      if (first) {
        sb.append(element);
        first = false;
      } else {
        sb.append(",").append(element);
      }
    }
    return sb.toString();
  }
}