package net.javacogito.gronsfeldcipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  private static final String EMPTY = "";
  private static final char[] ALPHABET = " !\"#$%&'()*+,-./0123456789:<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
  private static final int ALPHABET_LENGTH = ALPHABET.length;

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

  static String encrypt(int[] key, String message){
    return "";
  }

  static char shift(char symbol, int shift){
    int index = indexOf(symbol);
    int resultIndex = index + shift;
    if(index + shift <= ALPHABET_LENGTH - 1){
      return ALPHABET[resultIndex];
    }
    resultIndex = resultIndex - ALPHABET_LENGTH;
    return ALPHABET[resultIndex];
  }

  static int indexOf(char value){
    int result = 0;
    for(char symbol : ALPHABET){
      if(value == symbol){
        return result;
      }
      result++;
    }
    return -1; // never happens
  }
}