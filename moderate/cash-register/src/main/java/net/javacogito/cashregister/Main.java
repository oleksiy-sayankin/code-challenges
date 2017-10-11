package net.javacogito.cashregister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
  private static final String EMPTY = "";
  private static final Map<Integer, String> MAP = new HashMap<>();
  private static final int[] COIN_VALUES = {10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5, 1};


  static {
    MAP.put(1, "PENNY");
    MAP.put(5, "NICKEL");
    MAP.put(10, "DIME");
    MAP.put(25, "QUARTER");
    MAP.put(50, "HALF DOLLAR");
    MAP.put(100, "ONE");
    MAP.put(200, "TWO");
    MAP.put(500, "FIVE");
    MAP.put(1000, "TEN");
    MAP.put(2000, "TWENTY");
    MAP.put(5000, "FIFTY");
    MAP.put(10000, "ONE HUNDRED");
  }

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toString(findChange(parsePrice(inputLine), parseCash(inputLine))));
      }
    }
  }

  private static int parsePrice(String data){
    String rawPrice = data.split(";")[0];
    return (int) Math.round(Double.parseDouble(rawPrice) * 100);
  }

  private static int parseCash(String data){
    String rawCash = data.split(";")[1];
    return (int) Math.round(Double.parseDouble(rawCash) * 100);

  }

  static int[] findChange(int price, int cash){
    int[] result =  new int[COIN_VALUES.length];
    if(cash < price){
      Arrays.fill(result, -1);
      return result;
    }
    if(cash == price){
      return result;
    }
    int rest = cash - price;
    int index = COIN_VALUES.length - 1;
    for(int coinValue : COIN_VALUES){
      int count = rest / coinValue;
      if (count > 0){
        result[index] = count;
        rest = rest - coinValue * count;
      }
      index--;
    }
    return result;
  }

  
  static String toString(int[] change){
    if(hasAllValues(change, -1)){
      return "ERROR";
    }
    if(hasAllValues(change, 0)){
      return "ZERO";
    }
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    int index = COIN_VALUES.length - 1;
    for(int numCoins : change){
      if(numCoins == 0){
        index--;
        continue;
      }
      if(first){
          sb.append(getCoinName(COIN_VALUES[index], numCoins));
        first = false;
        index--;
        continue;
      }
      sb.append(",");
      sb.append(getCoinName(COIN_VALUES[index], numCoins));
      index--;
    }
    return sb.toString();
  }




  private static String getCoinName(int coinValue, int numCoins){
    StringBuilder sb =  new StringBuilder();
    for(int i = 0; i <= numCoins - 1; i++){
      if(i == 0){
        sb.append(MAP.get(coinValue));
        continue;
      }
      sb.append(",");
      sb.append(MAP.get(coinValue));
    }
    return sb.toString();
  }

  private static boolean hasAllValues(int[] numbers, int value){
    for(int number : numbers){
      if(number != value){
        return false;
      }
    }
    return true;
  }
}