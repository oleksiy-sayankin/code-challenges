package net.javacogito.romannumerals;

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
        System.out.println(toRoman(Integer.parseInt(inputLine)));
      }
    }
  }

  private static String toRoman(int value){
    int thousands = value / 1000;
    int hundreds = (value - 1000 * thousands) / 100;
    int tens = (value - 1000 * thousands - 100 * hundreds) / 10;
    int units = (value - 1000 * thousands - 100 * hundreds - 10 * tens);
    StringBuilder sb = new StringBuilder();
    sb.append(thousandsToRoman(thousands));
    sb.append(hundredsToRoman(hundreds));
    sb.append(tensToRoman(tens));
    sb.append(unitsToRoman(units));
    return sb.toString();
  }

  private static String thousandsToRoman(int thousands){
    switch (thousands){
      case 0 : return "";
      case 1 : return "M";
      case 2 : return "MM";
      case 3 : return "MMM";
    }
    return "";
  }

  private static String hundredsToRoman(int hundreds){
    switch (hundreds){
      case 0 : return "";
      case 1 : return "C";
      case 2 : return "CC";
      case 3 : return "CCC";
      case 4 : return "CD";
      case 5 : return "D";
      case 6 : return "DC";
      case 7 : return "DCC";
      case 8 : return "DCCC";
      case 9 : return "CM";
    }
    return "";
  }

  private static String tensToRoman(int tens){
    switch (tens){
      case 0: return "";
      case 1: return "X";
      case 2: return "XX";
      case 3: return "XXX";
      case 4: return "XL";
      case 5: return "L";
      case 6: return "LX";
      case 7: return "LXX";
      case 8: return "LXXX";
      case 9: return "XC";
    }
    return "";
  }

  private static String unitsToRoman(int units){
    switch (units){
      case 0: return "";
      case 1: return "I";
      case 2: return "II";
      case 3: return "III";
      case 4: return "IV";
      case 5: return "V";
      case 6: return "VI";
      case 7: return "VII";
      case 8: return "VIII";
      case 9: return "IX";
    }
    return "";
  }
}