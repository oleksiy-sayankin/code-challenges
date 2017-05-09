package net.javacogito.happynumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(isHappy(Integer.parseInt(inputLine)));
      }
    }
  }

  private static int isHappy(int number){
    int currentValue = number;
    Set<Integer> sumSquaresDigits = new HashSet<>();
    int currentSumSquaresDigit = sumSquaresDigit(currentValue);
    while (!sumSquaresDigits.contains(currentSumSquaresDigit)){
      if(currentSumSquaresDigit == 1) {
        return 1;
      }
      sumSquaresDigits.add(currentSumSquaresDigit);
      currentValue = currentSumSquaresDigit;
      currentSumSquaresDigit = sumSquaresDigit(currentValue);
    }
    return 0;
  }


  private static int sumSquaresDigit(int value){
    String valueAsString = String.valueOf(value);
    int sum = 0;
    int length = valueAsString.length();
    final int base = 48;
    for(int i = 0; i <= length - 1; i++){
      int digit = valueAsString.charAt(i) - base;
      sum += digit * digit;
    }
    return sum;
  }
}