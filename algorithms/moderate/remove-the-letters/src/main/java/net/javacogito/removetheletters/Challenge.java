package net.javacogito.removetheletters;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Challenge {
  public static String[] removeLetters(String[] letters, String word) {
    List<String> output = new LinkedList<>(Arrays.asList(letters));
    List<String> input = new LinkedList<>();
    for (char data : word.toCharArray()) {
      input.add(Character.toString(data));
    }
    for (String letter : input) {
      output.remove(letter);
    }
    return output.toArray(new String[0]);
  }
}
