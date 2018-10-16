package net.javacogito.countingprimes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(numPrimes(parseFirst(inputLine), parseSecond(inputLine)));
      }
    }
  }

  private static int parseFirst(String data){
    return Integer.parseInt(data.split(",")[0]);
  }

  private static int parseSecond(String data){
    return Integer.parseInt(data.split(",")[1]);
  }


  private static int numPrimes(int first, int second){
    int result = 0;
    for(int i = first; i <= second; i++){
      if(isPrime(i)){
        result++;
      }
    }
    return result;
  }

  private static boolean isPrime(int n){
    int max = (int)Math.round(Math.sqrt(n));
    for(int i = 2; i <= max; i++){
      if(n % i == 0){
        return false;
      }
    }
    return true;
  }
}