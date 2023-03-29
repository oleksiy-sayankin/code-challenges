package net.javacogito.removetheparentheses;

public class Kata {
  public static String removeParentheses(final String str) {
    StringBuilder sb = new StringBuilder();
    int length = str.length();
    int depth = 0;
    for (int i = 0; i <= length - 1; i++) {
      char symbol = str.charAt(i);
      if (symbol != '(' && symbol != ')' && depth == 0) {
        sb.append(symbol);
      }
      if(symbol == '(') {
        depth ++;
        continue;
      }
      if (symbol == ')') {
        depth--;
      }
    }
    return sb.toString();
  }
}
