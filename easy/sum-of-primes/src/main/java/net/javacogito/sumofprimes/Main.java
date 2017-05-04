package net.javacogito.sumofprimes;

import java.io.IOException;
import java.util.Arrays;

public class Main {

  private static int maxNum = 10000;
  private static byte primes[] = new byte[maxNum + 1];
  static {Arrays.fill(primes, (byte) 1);}

  public static void main(String[] args) throws IOException {
    makePrimes();
    int sumPrimes = 0;
    int primesCount = 0;

    for(int i = 2; i <= maxNum; i++){
      if(primes[i] == 1){
        primesCount++;
        sumPrimes += i;
        if(primesCount == 1000){
          break;
        }
      }
    }
    System.out.println(sumPrimes);
  }

  private static void makePrimes() {
    int n = (int) Math.sqrt(maxNum);
    for (int i = 2; i <= n; i++){
      crossOut(i);
    }
  }

  private static void crossOut(int value){
    for(int i = 2 * value; i <= maxNum; i+=value){
      primes[i] = 0;
    }
  }
}


