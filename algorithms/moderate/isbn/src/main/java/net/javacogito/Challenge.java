package net.javacogito;

public class Challenge {
  private static final String VALID = "Valid";
  private static final String INVALID = "Invalid";

  public static String isbn13(String str) {
    boolean isISBN13 = str.length() == 13;
    if (isISBN13) {
      return validateISBN13(str);
    }
    String result10 = validateISBN10(str);
    if (INVALID.equals(result10)) {
      return result10;
    }
    return convertToIsbn13(str);
  }

  private static String convertToIsbn13(String isbn10) {
    String isbn13 = "978" + isbn10;
    if (VALID.equals(validateISBN13(isbn13))) {
      return isbn13;
    }
    for (int digit = 0; digit <= 9; digit++) {
      isbn13 = replaceLast(isbn13, Integer.toString(digit));
      if (VALID.equals(validateISBN13(isbn13))) {
        return isbn13;
      }
    }
    return ""; // never happens
  }

  private static String replaceLast(String isbn13, String character) {
    return isbn13.substring(0, 12) + character;
  }

  private static String validateISBN13(String isbn13) {
    int[] numbers = new int[13];
    for (int i = 0; i <= 12; i++) {
      numbers[i] = parse(isbn13.charAt(i));
    }
    int sum13 = sum13(numbers);
    return sum13 % 10 == 0? VALID : INVALID;
  }

  private static int sum13(int[] numbers) {
    int[] factor = { 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1 };
    int sum13 = 0;
    int index = 0;
    for (int number : numbers) {
      sum13 += number * factor[index];
      index++;
    }
    return sum13;
  }


  private static String validateISBN10 (String isbn10) {
    int[] numbers =  new int[10];
    for (int i = 0; i <= 9; i++) {
      numbers[i] = parse(isbn10.charAt(i));
    }
    int sum = sum10(numbers);
    return sum % 11 == 0 ? VALID : INVALID;
  }

  private static int sum10(int[] numbers) {
    int sum10 = 0;
    int index = 10;
    for (int number : numbers) {
      sum10 += number * index;
      index--;
    }
    return sum10;
  }

  private static int parse(char number) {
    switch (number) {
    case '0' : return 0;
    case '1' : return 1;
    case '2' : return 2;
    case '3' : return 3;
    case '4' : return 4;
    case '5' : return 5;
    case '6' : return 6;
    case '7' : return 7;
    case '8' : return 8;
    case '9' : return 9;
    case 'X' : return 10;
    }
    return 0; // never happens
  }
}