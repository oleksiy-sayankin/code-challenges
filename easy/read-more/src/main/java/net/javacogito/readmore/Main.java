package net.javacogito.readmore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";
  private static final int NUM_CHARS_IN_LINE = 55;
  private static final int TRIMMED_NUM_CHARS_IN_LINE = 40;
  private static final String READ_MORE = "... <Read More>";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(process(inputLine));
      }
    }
  }

  private static String process(String data){
    if(data.length() <= NUM_CHARS_IN_LINE){
      return data;
    }
    String trimmedLine = data.substring(0, TRIMMED_NUM_CHARS_IN_LINE);
    if(trimmedLine.contains(" ")){
      return trimmedLine.substring(0, trimmedLine.lastIndexOf(" ")).trim() + READ_MORE;
    }
    return trimmedLine.trim() + READ_MORE;
  }
}