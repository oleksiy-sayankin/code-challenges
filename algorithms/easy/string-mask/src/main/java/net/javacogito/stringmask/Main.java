package net.javacogito.stringmask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(mask(inputLine));
      }
    }
  }

  private static String mask(String data) {
    String[] rawData = data.split(" ");
    char[] symbols = rawData[0].toCharArray();
    char[] commands = rawData[1].toCharArray();
    int length = symbols.length;
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i <= length - 1; i++){
      if(commands[i] == '1'){
        sb.append(Character.toUpperCase(symbols[i]));
      } else {
        sb.append(symbols[i]);
      }
    }
    return sb.toString();
  }
}