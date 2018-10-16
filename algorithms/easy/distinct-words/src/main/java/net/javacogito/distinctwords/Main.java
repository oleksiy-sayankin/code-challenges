package net.javacogito.distinctwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Prints a list of distinct words.
 */

public class Main {
  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    StringBuilder allLines = new StringBuilder();
    boolean first = true;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        if(first){
          allLines.append(inputLine);
          first = false;
        } else {
          allLines.append(" ");
          allLines.append(inputLine);
        }
      }
    }
    System.out.println(distinctWords(removeIgnoredChars(allLines.toString())));
  }

  /**
   * Removes chars that are ignored from source string
   * @param data String with input words
   * @return String without .,/-;:
   */

  public static String removeIgnoredChars(String data){
    return data.replace('.', ' ')
        .replace(',', ' ')
        .replace('/', ' ')
        .replace('-', ' ')
        .replace(';', ' ')
        .replace(':', ' ');
  }

  /**
   * Finds list of distinct words ignoring case and sorts it alphabetically. So Aaa equals aaa.
   * @param inputLine Input data
   * @return Sorted list of distinct words.
   */

  public static String distinctWords(String inputLine) {
    Set<String> set = new TreeSet<>();
    String[] words = inputLine.split(" ");
    for (String word : words) {
      if (!word.isEmpty()) {
        set.add(word.toLowerCase());
      }
    }
    return toString(set);
  }

  /**
   * Converts set of words to string.
   * @param words Set with words as an element.
   * @return String with all words with space between them
   */
  private static String toString(Set<String> words) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (String word : words) {
      if (first) {
        sb.append(word);
        first = false;
      } else {
        sb.append(" ");
        sb.append(word);
      }
    }
    return sb.toString();
  }
}
