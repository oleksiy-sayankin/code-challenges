package net.javacogito.jugglingwithzeroes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static final String ZERO = "0";

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

  private static long process(String data) {
    String[] rawData = data.split(" ");
    int length = rawData.length;
    int numPairs = length / 2;
    Pair[] pairs = new Pair[numPairs];
    int j = 0;
    for(int i = 0; i <= length - 1; i = i + 2){
      pairs[j] = new Pair(rawData[i], rawData[i + 1]);
      j++;
    }
    return integerFromBinary(buildBinary(pairs));
  }

  private static String buildBinary(Pair[] pairs){
    StringBuilder sb = new StringBuilder();
    for(Pair pair : pairs){
      sb.append(pair.value());
    }
    return sb.toString();
  }

  private static class Pair{
    private String flag;
    private String digits;
    private Pair(String flag, String digits){
      this.flag = flag;
      this.digits = digits;
    }

    @Override
    public String toString(){
      return flag + "-->" + digits;
    }

    private String value(){
      int length = digits.length();
      StringBuilder sb = new StringBuilder();
      if(ZERO.equals(flag)){
        for (int i = 0; i < length; i++){
          sb.append("0");
        }
        return sb.toString();
      }
      for (int i = 0; i < length; i++){
        sb.append("1");
      }
      return sb.toString();
    }
  }

  private static long integerFromBinary(String str){
    long j = 0;
    int length = str.length();
    for(int i = 0; i <= length - 1; i++){
      if(str.charAt(i)== '1'){
        j = j + pow2(length - 1 - i);
      }
    }
    return j;
  }

  private static long pow2(int pow){
    long result = 2;
    if(pow == 0){
      return 1;
    }
    for(int i = 1; i <= pow - 1; i++){
      result *= 2;
    }
    return result;
  }
}