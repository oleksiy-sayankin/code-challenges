package net.javacogito.mersenneprime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  private static final String EMPTY = "";
  private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
    53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137};

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(listToString(buildMersenne(Integer.parseInt(inputLine))));
      }
    }
  }

  private static String listToString(List<Integer> integers){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(int integer : integers){
      if(first){
        sb.append(integer);
        first = false;
        continue;
      }
      sb.append(", ");
      sb.append(integer);
    }
    return sb.toString();
  }

  private static List<Integer> buildMersenne(int k){
    List<Integer> result =  new ArrayList<>();
    for (int prime : PRIMES){
      int mersenneNumber = pow2(prime) - 1;
      if(mersenneNumber <= k){
        result.add(mersenneNumber);
      } else {
        break;
      }
    }
    return result;
  }

  private static int pow2(int value){
    int result = 2;
    for(int i = 2; i <= value; i++){
      result *= 2;
    }
    return result;
  }
}