package net.javacogito.bigdigits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static final String EMPTY = "";
  public static final String NL = "\n";

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
    String rawDigits = data.replaceAll("[^0-9]", "");
    int length = rawDigits.length();
    Digit[] digits = new Digit[length];
    for(int i = 0; i <= length - 1; i++){
      int value = Integer.parseInt(Character.toString(rawDigits.charAt(i)));
      digits[i] = new Digit(value);
    }
    StringBuilder sb = new StringBuilder();
    final int maxLines = 6;
    for (int i = 0; i <= maxLines - 1; i++){
      for (Digit digit : digits) {
        sb.append(digit.getLine(i));
      }
      boolean isLastLine = i == maxLines - 1;
      if(!isLastLine) {
        sb.append(NL);
      }
    }
    return sb.toString();
  }

  private static class Digit{
    private static final String[] _0 = {"-**--", "*--*-", "*--*-", "*--*-", "-**--", "-----"};
    private static final String[] _1 = {"--*--", "-**--", "--*--", "--*--", "-***-", "-----"};
    private static final String[] _2 = {"***--", "---*-", "-**--", "*----", "****-", "-----"};
    private static final String[] _3 = {"***--", "---*-", "-**--", "---*-", "***--", "-----"};
    private static final String[] _4 = {"-*---", "*--*-", "****-", "---*-", "---*-", "-----"};
    private static final String[] _5 = {"****-", "*----", "***--", "---*-", "***--", "-----"};
    private static final String[] _6 = {"-**--", "*----", "***--", "*--*-", "-**--", "-----"};
    private static final String[] _7 = {"****-", "---*-", "--*--", "-*---", "-*---", "-----"};
    private static final String[] _8 = {"-**--", "*--*-", "-**--", "*--*-", "-**--", "-----"};
    private static final String[] _9 = {"-**--", "*--*-", "-***-", "---*-", "-**--", "-----"};
    private final int digit;

    public Digit(int digit){
      this.digit = digit;
    }
    public String getLine(int lineNumber){
      switch (digit){
        case 0: return _0[lineNumber];
        case 1: return _1[lineNumber];
        case 2: return _2[lineNumber];
        case 3: return _3[lineNumber];
        case 4: return _4[lineNumber];
        case 5: return _5[lineNumber];
        case 6: return _6[lineNumber];
        case 7: return _7[lineNumber];
        case 8: return _8[lineNumber];
        case 9: return _9[lineNumber];
      }
      return "";
    }
  }
}