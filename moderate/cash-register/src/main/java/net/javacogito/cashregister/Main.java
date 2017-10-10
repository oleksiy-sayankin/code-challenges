package net.javacogito.cashregister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println();
      }
    }
  }

  private static int parsePrice(String data){
    return Integer.parseInt(data.split(";")[0].replaceAll("\\.", ""));
  }

  private static int parseCash(String data){
    return Integer.parseInt(data.split(";")[0].replaceAll("\\.", ""));
  }

  static class Coin implements Comparable<Coin>{
    private final String name;
    private final int value;

    public Coin(String name, int value) {
      this.name = name;
      this.value = value;
    }

    @Override
    public int compareTo(Coin o) {
      return this.value - o.value;
    }

    @Override
    public String toString(){
      return name;
    }
  }
  
  static String toString(List<Coin> coins){
    StringBuilder sb = new StringBuilder();
    boolean first = false;
    for(Coin coin : coins){
      if(first){
        sb.append(coin);
        first = false;
        continue;
      }
      sb.append(",");
      sb.append(coin);
    }
    return sb.toString();
  }

}