package net.javacogito.realfake;

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
        System.out.println(check(inputLine));
      }
    }
  }

  private static String check(String data){
    String cardNumber = data.replaceAll(" ", "");
    int length = cardNumber.length();
    int sum = 0;
    for(int i = 0; i <= length - 1; i++){
      int digit = cardNumber.charAt(i) - '0';
      if(i % 2 == 0){
        sum += 2 * digit;
      } else {
        sum += digit;
      }
    }
    if(sum % 10 == 0){
      return "Real";
    }
    return "Fake";
  }
}