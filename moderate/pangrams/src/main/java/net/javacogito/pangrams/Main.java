package net.javacogito.pangrams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";
  private static final String NULL = "NULL";
  private static final SortedSet<Character> all = new TreeSet<Character>();
  static {
    all.add('a');
    all.add('b');
    all.add('c');
    all.add('d');
    all.add('e');
    all.add('f');
    all.add('g');
    all.add('j');
    all.add('h');
    all.add('i');
    all.add('g');
    all.add('k');
    all.add('l');
    all.add('m');
    all.add('n');
    all.add('o');
    all.add('p');
    all.add('q');
    all.add('r');
    all.add('s');
    all.add('t');
    all.add('u');
    all.add('v');
    all.add('w');
    all.add('x');
    all.add('y');
    all.add('z');
  }

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(difference(inputLine.toLowerCase()));
      }
    }
  }

  private static String difference(String inputLine){
    char[] inputAsCharArray = inputLine.toCharArray();
    Set<Character> inputAsSet = new HashSet<>();
    for(char currentChar : inputAsCharArray){
      inputAsSet.add(currentChar);
    }
    SortedSet<Character> difference = new TreeSet<>(all);
    difference.removeAll(inputAsSet);
    return setToString(difference);
  }


  private static String setToString(Set<Character> characters){
    if(characters.isEmpty()){
      return NULL;
    }
    StringBuilder sb = new StringBuilder();
    for(Character character : characters){
      sb.append(character);
    }
    return sb.toString();
  }
}