package net.javacogito.stringpermutations;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;


public class Main {
  private static TreeSet<String> result = new TreeSet<String>();

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      inputLine = inputLine.trim();
      permutations("", inputLine);
      System.out.println(makeString(result));
      result.clear();
    }
  }

  public static void permutations(String first, String rest) {
    result.add(first + rest);
    String nextRest = rest;
    if (rest.length() == 1) {
      return;
    }
    do {
      nextRest = rotate(nextRest);
      permutations(first + getFirst(nextRest), getRest(nextRest));
    } while (!rest.equals(nextRest));
  }

  private static String getFirst(String in) {
    return in.substring(0, 1);
  }

  private static String getRest(String in) {
    return in.substring(1);
  }

  private static String rotate(String in) {
    return getRest(in) + getFirst(in);
  }

  private static String makeString(Set<String> lines) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (String line : lines) {
      if (first) {
        sb.append(line);
        first = false;
        continue;
      }
      sb.append(",");
      sb.append(line);
    }
    return sb.toString();
  }
}
