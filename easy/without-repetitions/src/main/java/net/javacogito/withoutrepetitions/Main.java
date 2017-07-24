package net.javacogito.withoutrepetitions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(removeRepetitions(inputLine));
      }
    }
  }

  private static String removeRepetitions(String data) {
    StringBuilder sb = new StringBuilder();
    char[] symbols = data.toCharArray();
    int length = symbols.length;
    char previous = symbols[0];
    for(int i = 1; i <= length - 1; i++){
      if(previous != symbols[i]){
        sb.append(previous);
        previous = symbols[i];
      }
    }
    sb.append(symbols[length - 1]);
    return sb.toString();
  }
}