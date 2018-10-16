package net.javacogito.cardnumbervalidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      System.out.println(validate(parseCardNumber(inputLine)));
    }
  }

  private static int[] parseCardNumber(String inputString){
    String rawData = inputString.trim().replaceAll("\\s+","");
    int cardDigitsCount = rawData.length();
    int[] cardDigits = new int[cardDigitsCount];
    for (int i = 0; i <= cardDigitsCount - 1; i++){
      cardDigits[i] = Integer.parseInt(Character.toString(rawData.charAt(i)));
    }
    return cardDigits;
  }

  private static int validate(int[] cardDigits){
    int cardDigitsCount = cardDigits.length;
    int[] doubleSums = Arrays.copyOf(cardDigits, cardDigitsCount);

    for(int i = cardDigitsCount - 2; i >= 0; i -= 2){
      doubleSums[i] = 2 * cardDigits[i];
    }

    int checkSum = 0;
    for(int i = cardDigitsCount - 1; i >= 0; i--){
      if(doubleSums[i] >= 10){
        checkSum += sumDigits(doubleSums[i]);
      } else {
        checkSum += doubleSums[i];
      }
    }

    return checkSum % 10 == 0 ? 1 : 0;
  }

  private static int sumDigits(int number){
    String numberAsString = Integer.toString(number);
    int firstDigit = Integer.parseInt(numberAsString.substring(0, 1));
    int secondDigit = Integer.parseInt(numberAsString.substring(1, 2));
    return firstDigit + secondDigit;
  }
}