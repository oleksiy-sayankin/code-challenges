package net.javacogito.hiddendigits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static final String EMPTY = "";

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
    data = data.replaceAll("[^abcdefghij0-9]", "")
               .replaceAll("a", "0")
               .replaceAll("b", "1")
               .replaceAll("c", "2")
               .replaceAll("d", "3")
               .replaceAll("e", "4")
               .replaceAll("f", "5")
               .replaceAll("g", "6")
               .replaceAll("h", "7")
               .replaceAll("i", "8")
               .replaceAll("j", "9");
    return data.isEmpty() ? "NONE" : data;
  }
}