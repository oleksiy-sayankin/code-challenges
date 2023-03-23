package net.javacogito.unknowndigit;

public class Runes {

  public static int solveExpression(final String expression) {
    int missingDigit = -1;
    for (int currentDigit = 0; currentDigit <= 9; currentDigit++) {
      if (existsInSExpression(expression, currentDigit)) {
        continue;
      }
      String currentExpression = expression.replaceAll("\\?", Integer.toString(currentDigit));
      if (hasLeadingZero(currentExpression)) {
        continue;
      }
      if (isValid(currentExpression)) {
        missingDigit = currentDigit;
        break;
      }
    }
    return missingDigit;
  }

  private static boolean hasLeadingZero(String expression) {
    char first = expression.charAt(0);
    char second = expression.charAt(1);
    if (first == '0' && second >= '0' && second <= '9') {
      return true;
    }
    for (int digit = 0; digit <= 9; digit++) {
      String[] leadingZeros = {"+0" + digit, "-0" + digit, "*0" + digit, "=0" + digit};
      for (String leadingZero : leadingZeros) {
        if (expression.contains(leadingZero)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean existsInSExpression(String expression, int digit) {
    return expression.indexOf(Integer.toString(digit)) > 0;
  }

  public static boolean isValid(String expression) {
    int firstArg = parseFirstArg(expression);
    int secondArg = parseSecondArg(expression);
    char operation = parseOperation(expression);
    int resultInExpression = parseResult(expression);
    int actualResult = calculateResult(firstArg, secondArg, operation);
    return resultInExpression == actualResult;
  }

  private static int calculateResult(int firstArg, int secondArg, char operation) {
    switch (operation) {
      case '+':
        return firstArg + secondArg;
      case '-':
        return firstArg - secondArg;
      case '*':
        return firstArg * secondArg;
    }
    return 0;
  }

  public static int parseFirstArg(String expression) {
    boolean hasLeadingMinus = expression.charAt(0) == '-';
    int plusIndex = expression.indexOf('+', hasLeadingMinus ? 1 : 0);
    int minusIndex = expression.indexOf('-', hasLeadingMinus ? 1 : 0);
    int multiplyIndex = expression.indexOf('*', hasLeadingMinus ? 1 : 0);
    int operationIndex = min(plusIndex > 0 ? plusIndex : Integer.MAX_VALUE, minusIndex > 0 ? minusIndex : Integer.MAX_VALUE, multiplyIndex > 0 ? multiplyIndex : Integer.MAX_VALUE);
    return Integer.parseInt(expression.substring(0, operationIndex));
  }

  public static int parseSecondArg(String expression) {
    boolean hasLeadingMinus = expression.charAt(0) == '-';
    if (hasLeadingMinus) {
      expression = expression.substring(1);
    }
    int plusIndex = expression.indexOf('+');
    int minusIndex = expression.indexOf('-');
    int multiplyIndex = expression.indexOf('*');
    int operationIndex = min(plusIndex > 0 ? plusIndex : Integer.MAX_VALUE, minusIndex > 0 ? minusIndex : Integer.MAX_VALUE, multiplyIndex > 0 ? multiplyIndex : Integer.MAX_VALUE);
    return Integer.parseInt(expression.substring(operationIndex + 1, expression.indexOf("=")));
  }

  public static char parseOperation(String expression) {
    boolean hasLeadingMinus = expression.charAt(0) == '-';
    if (hasLeadingMinus) {
      expression = expression.substring(1);
    }
    int plusIndex = expression.indexOf('+');
    int minusIndex = expression.indexOf('-');
    int multiplyIndex = expression.indexOf('*');
    int operationIndex = min(plusIndex > 0 ? plusIndex : Integer.MAX_VALUE, minusIndex > 0 ? minusIndex : Integer.MAX_VALUE, multiplyIndex > 0 ? multiplyIndex : Integer.MAX_VALUE);
    return expression.charAt(operationIndex);
  }

  public static int parseResult(String expression) {
    int equalsIndex = expression.indexOf('=');
    return Integer.parseInt(expression.substring(equalsIndex + 1));
  }

  private static int min(int a, int b, int c) {
    return Math.min(Math.min(a, b), c);
  }
}
