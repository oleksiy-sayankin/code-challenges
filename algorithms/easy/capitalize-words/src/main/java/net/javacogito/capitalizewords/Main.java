package net.javacogito.capitalizewords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(capitalize(inputLine));
      }
    }
  }


  private static String capitalize(String data){
    String[] words = data.trim().split(" ");
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (String word : words){
      if(first){
        first = false;
        sb.append(capitalizeWord(word));
      } else {
        sb.append(" ").append(capitalizeWord(word));
      }
    }
    return sb.toString();
  }

  private static String capitalizeWord(String word){
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }
}

