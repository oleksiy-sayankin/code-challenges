package net.javacogito.characterwithlongest;

import java.util.Comparator;
import java.util.TreeSet;

public class Solution {
  private static class CharSequence {
    private final int length;
    private final int startIndex;
    private final String symbol;

    private CharSequence(String symbol, int length, int startIndex) {
      this.symbol = symbol;
      this.length = length;
      this.startIndex = startIndex;
    }

    @Override
    public String toString() {
      return "[" + symbol + ", length = " + length + ", startIndex = " + startIndex + "]";
    }
  }

  public static Object[] longestRepetition(String s) {
    if (s == null || s.isEmpty()) {
      return new Object[]{"", 0};
    }
    TreeSet<CharSequence> repetitions = new TreeSet<>(new Comparator<CharSequence>() {
      @Override
      public int compare(CharSequence o1, CharSequence o2) {
        if (o1.length > o2.length) {
          return -1;
        } else if (o1.length < o2.length) {
          return 1;
        } else {
          return o1.startIndex - o2.startIndex;
        }
      }
    });

    char sequenceChar = s.charAt(0);
    int currentAmount = 0;
    int length = s.length();
    int startIndex = 0;
    for (int i = 0; i <= length - 1; i++) {
      char currentChar = s.charAt(i);
      boolean isLastChar = i == length - 1;
      if (sequenceChar != currentChar) {
        repetitions.add(new CharSequence(Character.toString(sequenceChar), currentAmount, startIndex));
        sequenceChar = currentChar;
        currentAmount = 1;
        startIndex = i;
        continue;
      }
      currentAmount++;
      if (isLastChar) {
        repetitions.add(new CharSequence(Character.toString(sequenceChar), currentAmount, startIndex));
      }
    }
    return new Object[]{repetitions.first().symbol, repetitions.first().length};
  }
}
