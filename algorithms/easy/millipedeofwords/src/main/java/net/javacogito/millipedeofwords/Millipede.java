package net.javacogito.millipedeofwords;

import java.util.*;

public class Millipede {
  private static boolean CAN_BE_COMBINED;

  public static boolean check(String[] millipede) {
    CAN_BE_COMBINED = false;
    for (int i = 0; i <= millipede.length - 1; i++) {
      String firstWord = millipede[i];
      List<String> theRestWords = new ArrayList<>(Arrays.asList(millipede));
      theRestWords.remove(firstWord);
      List<String> result = new ArrayList<>();
      result.add(0, firstWord);
      verify(firstWord, theRestWords, result);
    }
    return CAN_BE_COMBINED;
  }

  private static void verify(String firstWord, List<String> theRestWords, List<String> result) {
    if (theRestWords.isEmpty()) {
      CAN_BE_COMBINED = true;
      return;
    }
    for (String nextWord : theRestWords) {
      if (canBeCombined(firstWord, nextWord)) {
        result.add(nextWord);
        List<String> newWords = new ArrayList<>(theRestWords);
        newWords.remove(nextWord);
        verify(nextWord, newWords, result);
      }
    }
  }

  private static boolean canBeCombined(String firstWord, String secondWord) {
    return firstWord.charAt(firstWord.length() - 1) == secondWord.charAt(0);
  }
}
