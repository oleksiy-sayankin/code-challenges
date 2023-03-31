package net.javacogito.buildtower;

public class Kata {
  public static String[] towerBuilder(int numFloors) {
    String[] result = new String[numFloors];
    for (int i = 0; i <= numFloors - 1; i++) {
      int numStars = i * 2 + 1;
      int numSpaces = numFloors - i - 1;
      String spaces = repeat(" ", numSpaces);
      String stars = repeat("*", numStars);
      result[i] = spaces + stars + spaces;
    }
    return result;
  }

  private static String repeat(String symbol, int amount) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= amount - 1; i++) {
      sb.append(symbol);
    }
    return sb.toString();
  }
}
