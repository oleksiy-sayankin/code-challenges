package net.javacogito.stringandarrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static final String LEFT_ARROW = "<--<<";
  private static final String RIGHT_ARROW = ">>-->";
  private static final int ARROW_LENGTH = LEFT_ARROW.length();

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(findNumArrows(inputLine));
      }
    }
  }

  private static int findNumArrows(String data) {
    int numArrows = 0;
    int length = data.length();
    for (int i = 0; i <= length - ARROW_LENGTH; i++){
      if(isArrowAt(data, i)){
        numArrows++;
      }
    }

    return numArrows;
  }

  private static boolean isArrowAt(String data, int index){
    if(index + ARROW_LENGTH > data.length()){
      return false;
    }
    String possibleArrow = data.substring(index, index + ARROW_LENGTH);
    return LEFT_ARROW.equals(possibleArrow) || RIGHT_ARROW.equals(possibleArrow);
  }
}