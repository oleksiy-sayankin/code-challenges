package net.javacogito.puzzletiles;

public class ASCIIFun {
  public static String puzzleTiles(int width, int height) {
    if (width == 1 && height == 1) {
      return generateOutput(buildLines1x1(), 1, 1);
    }

    return generateOutput(buildLinesNxN(width, height), width, height);
  }

  private static String[][][] buildLines1x1() {
    String[][][] lines = new String[4][1][1];
    lines[0][0][0] = "   _( )__";
    lines[1][0][0] = " _|     _|";
    lines[2][0][0] = "(_   _ (_";
    lines[3][0][0] = " |__( )_|";
    return lines;
  }


  private static String[][][] buildLinesNxN(int width, int height) {
    String[][][] lines = new String[4][height][width];
    for (int i = 0; i <= height - 1; i++) {
      for (int j = 0; j <= width - 1; j++) {
        String[] pattern = findPatter(i, j, height, width);
        for (int k = 0; k <= 3; k++) {
          lines[k][i][j] = pattern[k];
        }
      }
    }
    return lines;
  }

  private static String generateOutput(String[][][] lines, int width, int height) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= height - 1; i++) {
      for (int k = 0; k <= 3; k++) {
        for (int j = 0; j <= width - 1; j++) {
          if (lines[k][i][j] != null) {
            sb.append(lines[k][i][j]);
          }
        }
        if (k == 3 && i == height - 1) {
          continue;
        }
        if (lines[k][i][0] != null) {
          sb.append("\n");
        }
      }
    }
    System.out.println(sb);
    return sb.toString();
  }

  private static String[] findPatter(int i, int j, int height, int width) {
    // 0 0
    if (i == 0 && j == 0) {
      return new String[]{"   _( )__", " _|     _", "(_   _ (_", null};
    }
    // N 0
    if (i == height - 1 && j == 0) {
      return new String[]{" |__( )_", " _|     ", "(_   _ (", " |__( )_", null};
    }
    // N j
    if (i == height - 1 && j > 0 && j < width - 1) {
      return new String[]{"|__( )_", "_|     ", "_   _ (", "|__( )_"};
    }
    // N N
    if (i == height - 1 && j == width - 1) {
      return new String[]{"|__( )_|", "_|     _|", "_   _ (_", "|__( )_|"};
    }
    // 0 j
    if (i == 0 && j > 0 && j < width - 1) {
      return new String[]{" _( )__", "|     _", "   _ (_", null};
    }
    // 0 N
    if (i == 0 && j == width - 1) {
      return new String[]{" _( )__", "|     _|", "   _ (_", null};
    }
    // i 0
    if (i > 0 && i < width - 2 && j == 0) {
      return new String[]{" |__( )_", " |_     ", "  _) _  ", null};
    }
    // i N
    if (i > 0 && i <= height - 2 && j == width - 1) {
      return new String[]{"|__( )_|", "|_     |_", " _) _   _)", null};
    }
    // center
    if (i > 0 && i < height - 1 && j > 0 && j < width - 1) {
      return new String[]{"|__( )_", "|_     ", " _) _  ", null};
    }
    return new String[]{"        ", "        ", "        ", ""};
  }
}
