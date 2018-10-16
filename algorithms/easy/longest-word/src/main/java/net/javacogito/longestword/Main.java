package net.javacogito.longestword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(findLongestWord(inputLine.split(" ")));
      }
    }
  }


  private static String findLongestWord(String[] words){
    String longestWord = words[0];
    for(String word : words){
      if(word.length() > longestWord.length()){
        longestWord = word;
      }
    }
    return longestWord;
  }
}