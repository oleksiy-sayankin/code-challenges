package net.javacogito.guessthenumber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

  private static final String EMPTY = "";
  private static final String LOWER = "Lower";
  private static final String HIGHER = "Higher";
  private static final String YAY = "Yay!";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(guessTheNumber(parseUpperBound(inputLine), parseAnswers(inputLine)));
      }
    }
  }

  private static int parseUpperBound(String data){
    return Integer.parseInt(data.split(" ")[0]);
  }

  private static String[] parseAnswers(String data){
    String[] rawData = data.split(" ");
    return  Arrays.copyOfRange(rawData, 1, rawData.length);
  }

  private static int guessTheNumber(int max, String[] answers){
    int lowerBound = 0;
    int upperBound = max;
    int result = newGuess(upperBound, lowerBound);
    for(String answer : answers){
      if(YAY.equals(answer)){
        break;
      }
      if(LOWER.equals(answer)){
       upperBound = result - 1;
      }

      if(HIGHER.equals(answer)){
        lowerBound = result + 1;
      }
      result = newGuess(upperBound, lowerBound);
    }
    return result;
  }

  private static int newGuess(int upperBound, int lowerBound){
    int numElem = upperBound - lowerBound + 1;
    if(isEven(numElem)){
      return (upperBound + lowerBound) / 2 + 1;
    }
    return (upperBound + lowerBound) / 2;
  }

  private static boolean isEven(int number){
    return number % 2 == 0;
  }
}