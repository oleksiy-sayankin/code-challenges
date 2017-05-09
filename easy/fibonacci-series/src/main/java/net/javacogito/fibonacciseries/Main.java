package net.javacogito.fibonacciseries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(fibonacci(Integer.parseInt(inputLine)));
      }
    }
  }

 private static int fibonacci(int n){
   if(n > 1){
     return fibonacci(n - 1) + fibonacci(n - 2);
   }
   if(n == 1) {
     return 1;
   }
   return 0;
 }
}