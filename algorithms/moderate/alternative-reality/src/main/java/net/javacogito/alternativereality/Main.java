package net.javacogito.alternativereality;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static int numAlternatives = 0;
  private static int sum = 0;


  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        sum = parseSum(inputLine);
        numAlternatives = 0;
        evaluate();
        System.out.println(numAlternatives);
      }
    }
  }

  private static int parseSum(String inputLine){
    return Integer.parseInt(inputLine);
  }

  private static void evaluate(){
    Expression e = new Expression();
    boolean added = false;
    while (e.value() <= sum){
      if(e.value() == sum && !added){
        numAlternatives++;
        added = true;
      }
      while (e.value() <= sum && !added){
        if(e.value() == sum){
          numAlternatives++;
          added = true;
        }
        while (e.value() <= sum && !added){
          if(e.value() == sum){
            numAlternatives++;
            added = true;
          }
          while (e.value() <= sum && !added){
            if(e.value() == sum){
              numAlternatives++;
              added = true;
            }
            while (e.value() <= sum && !added){
              if(e.value() == sum){
                numAlternatives++;
                added = true;
              }
              e.incFactor(0);
              added = false;
            }
            e.resetFactor(0);
            e.incFactor(1);
            added = false;
          }
          e.resetFactor(1);
          e.incFactor(2);
          added = false;
        }
        e.resetFactor(2);
        e.incFactor(3);
        added = false;
      }
      e.resetFactor(3);
      e.incFactor(4);
      added = false;
    }
  }

  private static class Expression {
    private static final int COEFFICIENTS[] = new int[]{1, 5, 10, 25, 50};
    private int factors[] = new int[COEFFICIENTS.length];

    private Expression(){}

    private void incFactor(int index){
      factors[index]++;
    }

    private void resetFactor(int index){
      factors[index] = 0;
    }

    private int value(){
      int value = 0;
      for (int i = 0; i <= COEFFICIENTS.length - 1; i++){
        value += COEFFICIENTS[i] * factors[i];
      }
      return value;
    }
  }
}