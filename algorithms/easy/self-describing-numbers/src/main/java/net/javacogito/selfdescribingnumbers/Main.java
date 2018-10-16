package net.javacogito.selfdescribingnumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
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
        System.out.println(isSelfDescribing(inputLine));
      }
    }
  }

  private static int isSelfDescribing(String data){
    int length = data.length();
    for (char position = 0; position <= length - 1; position++){
      int value = Integer.parseInt(Character.toString(data.charAt(position)));
      if(value != numSymbols(data, position)){
        return 0;
      }
    }
    return 1;
  }

  private static int numSymbols(String data, int symbol){
    int numSymbols = 0;
    int length = data.length();
    for(int i = 0; i <= length - 1; i++){
      if(Integer.parseInt(Character.toString(data.charAt(i))) == symbol){
        numSymbols++;
      }
    }
    return numSymbols;
  }
}