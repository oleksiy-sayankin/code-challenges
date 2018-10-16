package net.javacogito.lostintranslation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
  private static final Map<Character, Character> code = new HashMap<>();

  static {
    code.put(' ', ' ');
    code.put('a', 'y');
    code.put('b', 'h');
    code.put('c', 'e');
    code.put('d', 's');
    code.put('e', 'o');
    code.put('f', 'c');
    code.put('g', 'v');
    code.put('h', 'x');
    code.put('i', 'd');
    code.put('j', 'u');
    code.put('k', 'i');
    code.put('l', 'g');
    code.put('m', 'l');
    code.put('n', 'b');
    code.put('o', 'k');
    code.put('p', 'r');
    code.put('q', 'z');
    code.put('r', 't');
    code.put('s', 'n');
    code.put('t', 'w');
    code.put('u', 'j');
    code.put('v', 'p');
    code.put('w', 'f');
    code.put('x', 'm');
    code.put('y', 'a');
    code.put('z', 'q');
  }

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      System.out.println(decodeString(inputLine));
    }
  }

  private static String decodeString(String inputLine) {
    StringBuilder sb = new StringBuilder();
    int length = inputLine.length();
    for (int i = 0; i <= length - 1; i++) {
      sb.append(decodeChar(inputLine.charAt(i)));
    }
    return sb.toString();
  }

  private static char decodeChar(char inputChar) {
    return code.get(inputChar);
  }
}