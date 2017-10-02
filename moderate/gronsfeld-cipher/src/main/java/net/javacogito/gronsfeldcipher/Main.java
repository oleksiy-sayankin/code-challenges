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
        System.out.println(decrypt(parseKey(inputLine), parseMessage(inputLine)));
      }
    }
  }

  private static String parseKey(String data){
    return data.split(";")[0];
  }

  private static String parseMessage(String data){
    return data.split(";")[1];
  }

  static String decrypt(String smallKey, String message){
    int messageLength =  message.length();
    int[] fullKey = buildFullKey(smallKey, messageLength);
    StringBuilder sb =  new StringBuilder();
    for(int i = 0; i <= messageLength - 1; i++){
      sb.append(shift(message.charAt(i), -fullKey[i]));
    }
    return sb.toString();
  }


  static String encrypt(String smallKey, String message){
    int messageLength =  message.length();
    int[] fullKey = buildFullKey(smallKey, messageLength);
    StringBuilder sb =  new StringBuilder();
    for(int i = 0; i <= messageLength - 1; i++){
      sb.append(shift(message.charAt(i), fullKey[i]));
    }
    return sb.toString();
  }

  static int[] buildFullKey(String smallKey, int messageLength){
    StringBuilder sb = new StringBuilder();
    while (sb.length() < messageLength){
      sb.append(smallKey);
    }
    String rawKey = sb.toString().substring(0, messageLength);
    int[] result = new int[messageLength];
    for(int i = 0; i <= messageLength - 1; i++){
      result[i] = rawKey.charAt(i) - '0';
    }
    return result;
  }

  static char shift(char symbol, int shift){
    int index = indexOf(symbol);
    int resultIndex = index + shift;
    if(resultIndex >= 0 && resultIndex <= ALPHABET_LENGTH - 1){
      return ALPHABET[resultIndex];
    }
    if(resultIndex > ALPHABET_LENGTH){
      resultIndex = resultIndex - ALPHABET_LENGTH;
    }
    if(resultIndex < 0){
      resultIndex = ALPHABET_LENGTH + resultIndex;
    }
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