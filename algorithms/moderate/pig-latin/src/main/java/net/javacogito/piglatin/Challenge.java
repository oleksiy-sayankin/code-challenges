package net.javacogito.piglatin;

public class Challenge {
  private static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };

  public static String translateWord(String word) {
    if (word.isEmpty()) {
      return "";
    }
    String result;
    boolean isFirstUpperCase = isFirstUpperCase(word);
    char[] letters = word.toLowerCase().toCharArray();
    if (isVowel(letters[0])) {
      result = new String(letters) + "yay";
    } else {
      while (!isVowel(letters[0])) {
        letters = shiftRight(letters);
      }
      result = new String(letters) + "ay";
    }
    return isFirstUpperCase ? makeFirstUpperCase(result) : result;
  }

  public static String translateSentence(String sentence) {
    char[] symbols = sentence.toCharArray();
    int length = symbols.length;
    StringBuilder result = new StringBuilder();
    StringBuilder word = new StringBuilder();
    char currentChar = 0;
    char prevChar = 0;
    int i = 0;
    while (i <= length - 1) {
      currentChar = symbols[i];
      boolean isLineStart = i == 0;
      boolean isLineEnd = i == length - 1;
      if ((isLineStart || isPunctuationMark(prevChar)) && !isPunctuationMark(currentChar)) {
        while (!isPunctuationMark(currentChar)) {
          word.append(currentChar);
          i++;
          isLineEnd = i == length;
          if (isLineEnd) {
            break;
          }
          currentChar = symbols[i];
        }
        result.append(translateWord(word.toString()));
        word.setLength(0); // clear world
      }
      if (isPunctuationMark(currentChar)) {
        result.append(currentChar);
      }
      i++;
      prevChar = currentChar;
    }
    return result.toString();
  }

  private static String makeFirstUpperCase(String word) {
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }

  private static boolean isFirstUpperCase(String word) {
    return Character.isUpperCase(word.charAt(0));
  }

  private static boolean isPunctuationMark(char symbol) {
    return Character.toString(symbol).matches("[^\\w]");
  }

  private static char[] shiftRight(char[] word) {
    int length = word.length;
    char[] result = new char[length];
    System.arraycopy(word, 1, result, 0, length - 1);
    result[length - 1] = word[0];
    return result;
  }

  private static boolean isVowel(char letter) {
    for (char vowel : VOWELS) {
      if (vowel == letter) {
        return true;
      }
    }
    return false;
  }
}
