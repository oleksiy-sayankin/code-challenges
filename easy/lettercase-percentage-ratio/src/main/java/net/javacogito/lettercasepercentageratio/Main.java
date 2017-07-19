package net.javacogito.lettercasepercentageratio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {

  private static final String EMPTY = "";
  private static DecimalFormat df =  new DecimalFormat("0.00");

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

  private static String process(String data) {
    char[] symbols = data.toCharArray();
    int numUpper = 0;
    int total = symbols.length;
    for(char symbol : symbols){
      if(isUpper(symbol)){
        numUpper++;
      }
    }
    float lowerPercent = (float) (total - numUpper) / total * 100;
    float upperPercent = (float) numUpper / total * 100;
    return "lowercase: " + df.format(lowerPercent) + " uppercase: " + df.format(upperPercent);
  }

  private static boolean isUpper(char symbol){
    if(symbol >= 'A' && symbol <= 'Z'){
      return true;
    }
    return false;
  }
}