package net.javacogito.binomialexpansion;

import java.math.BigInteger;

public class KataSolution {
  public static String expand(String expr) {
    int n = parseN(expr);
    if (n == 0) {
      return "1";
    }
    String x = parseX(expr);
    int a = parseA(expr);
    int b = parseB(expr);
    BigInteger[] c = buildCoefficients(n);
    StringBuilder sb = new StringBuilder();
    for (int i = n; i >= 0; i--) {
      int j = n - i;
      c[i] = c[i].multiply(BigInteger.valueOf(a).pow(i)).multiply(BigInteger.valueOf(b).pow(j));
      if (c[i].abs().compareTo(BigInteger.ZERO) != 0) {
        if (i == n && c[i].abs().compareTo(BigInteger.ONE) == 0) {
          if (c[i].compareTo(BigInteger.ZERO) < 0) {
            sb.append("-");
          }
        }
        if (i < n) {
          if (c[i].compareTo(BigInteger.ZERO) > 0) {
            sb.append("+");
          }
        }
        if (c[i].abs().compareTo(BigInteger.ONE) != 0 && i > 0) {
          sb.append(c[i]);
        }
        if (i == 0) {
          sb.append(c[i]);
        }
        if (i > 0) {
          sb.append(x);
        }
        if (i > 1) {
          sb.append("^");
          sb.append(i);
        }
      }
    }
    return sb.toString();
  }


  public static int parseB(String expr) {
    String x = parseX(expr);
    String b = expr.substring(expr.indexOf(x) + 1, expr.indexOf(")"));
    if (b.isEmpty()) {
      return 1;
    }
    if ("-".equals(b)) {
      return -1;
    }
    return Integer.parseInt(b);
  }


  public static int parseA(String expr) {
    String x = parseX(expr);
    String a = expr.substring(1, expr.indexOf(x));
    if (a.isEmpty()) {
      return 1;
    }
    if ("-".equals(a)) {
      return -1;
    }
    return Integer.parseInt(a);
  }


  private static String stringAt(String str, int index) {
    return str.substring(index, index + 1);
  }

  public static String parseX(String expr) {
    String validVariable = "abcdefghijklmnopqrstuvwxyz";
    int length = expr.length();
    for (int i = 0; i <= length - 1; i++) {
      String currentSymbol = stringAt(expr, i);
      if (validVariable.contains(currentSymbol)) {
        return currentSymbol;
      }
    }
    return "";
  }

  public static int parseN(String expr) {
    return Integer.parseInt(expr.substring(expr.indexOf("^") + 1));
  }

  public static BigInteger[] buildCoefficients(int power) {
    BigInteger[][] c = new BigInteger[power][power + 1];
    c[0][0] = BigInteger.valueOf(1);
    c[0][1] = BigInteger.valueOf(1);
    for (int i = 1; i <= power - 1; i++) {
      c[i][0] = BigInteger.valueOf(1);
      c[i][i + 1] = BigInteger.valueOf(1);
      for (int j = 1; j <= i; j++) {
        c[i][j] = c[i - 1][j - 1].add(c[i - 1][j]);
      }
    }
    return c[power - 1];
  }
}
