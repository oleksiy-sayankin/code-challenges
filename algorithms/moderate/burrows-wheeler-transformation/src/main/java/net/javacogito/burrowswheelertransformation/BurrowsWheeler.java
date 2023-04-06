package net.javacogito.burrowswheelertransformation;

import java.util.Arrays;

public class BurrowsWheeler {
  public static BWT encode(String s) {
    if(s.isEmpty()) {
      return new BWT("", 0);
    }
    int length = s.length();
    String source = s;
    String[] lines = new String[length];
    lines[0] = s;
    for (int i = 1; i <= length - 1; i++) {
      s = rotate(s);
      lines[i] = s;
    }
    Arrays.sort(lines);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= length - 1; i++) {
      sb.append(lastChar(lines[i]));
    }
    return new BWT(sb.toString(), Arrays.asList(lines).indexOf(source));
  }

  public static String decode(String source, int n) {
    if(source.isEmpty()) {
      return "";
    }
    int length = source.length();
    String[] asStringArray = splitToStringArray(source);
    String[] lines = new String[length];
    System.arraycopy(asStringArray, 0, lines, 0, length);;
    for (int i = 0; i <= length - 2; i++) {
      Arrays.sort(lines);
      lines = concat(asStringArray, lines);
    }
    Arrays.sort(lines);
    return lines[n];
  }

  private static String[] concat(String[] a, String[] b) {
    int length = a.length;
    String[] result = new String[length];
    for(int i = 0; i <= length - 1; i++) {
      result[i] = a[i] + b[i];
    }
    return result;
  }

  private static String[] splitToStringArray(String source) {
    int length = source.length();
    String[] result = new String[length];
    for (int i = 0; i <= length - 1; i++) {
      result[i] = Character.toString(source.charAt(i));
    }
    return result;
  }

  private static String rotate(String value) {
    return lastChar(value) + value.substring(0, value.length() - 1);
  }

  private static String lastChar(String value) {
    return Character.toString(value.charAt(value.length() - 1));
  }
}
