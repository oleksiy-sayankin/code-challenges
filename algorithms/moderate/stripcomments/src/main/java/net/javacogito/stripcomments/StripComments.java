package net.javacogito.stripcomments;

import java.util.Arrays;

public class StripComments {
  private static String[] commentSymbols;

  public static String stripComments(String text, String[] commentSymbols) {
    StripComments.commentSymbols = commentSymbols;
    String[] lines = text.split("\n");
    StringBuilder sb = new StringBuilder();
    boolean isFirst = true;
    for (String line : lines) {
      if (isFirst) {
        sb.append(removeCommentsIn(line));
        isFirst = false;
      } else {
        sb.append("\n");
        sb.append(removeCommentsIn(line));
      }
    }
    return sb.toString();
  }

  private static String removeCommentsIn(String line) {
    String result = line;
    while (hasCommentSymbolIn(result)) {
      int length = result.length();
      for (int i = length - 1; i >= 0; i--) {
        if (isCommentSymbol(stringAt(result, i))) {
          result = result.substring(0, i);
          break;
        }
      }
    }
    return result.stripTrailing();
  }

  private static boolean isCommentSymbol(String symbol) {
    return Arrays.asList(commentSymbols).contains(symbol);
  }

  private static String stringAt(String string, int index) {
    return string.substring(index, index + 1);
  }

  private static boolean hasCommentSymbolIn(String line) {
    boolean result = false;
    for (String commentSymbol : commentSymbols) {
      if (line.contains(commentSymbol)) {
        return true;
      }
    }
    return result;
  }
}
