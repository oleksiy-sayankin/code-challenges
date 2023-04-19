package net.javacogito.addingbignumbers;

public class Kata {
  public static String add(String a, String b) {
    int lengthA = a.length();
    int lengthB = b.length();
    int length = Math.max(lengthA, lengthB) + 1;
    int[] first = new int[length];
    int[] second = new int[length];
    int[] result = new int[length];
    for (int i = 0; i <= lengthA - 1; i++) {
      first[length - lengthA + i] = Integer.parseInt(stringAt(a, i));
    }
    for (int i = 0; i <= lengthB - 1; i++) {
      second[length - lengthB + i] = Integer.parseInt(stringAt(b, i));
    }
    int overSum = 0;
    for (int i = length - 1; i >= 0; i--) {
      int sum = first[i] + second[i] + overSum;
      overSum = 0;
      if (sum >= 10) {
        sum -= 10;
        overSum++;
      }
      result[i] = sum;
    }
    StringBuilder sb = new StringBuilder();
    boolean isUpcomingZero = true;
    for (int i = 0; i <= length - 1; i++) {
      if (result[i] == 0 && isUpcomingZero) {
        continue;
      }
      isUpcomingZero = false;
      sb.append(result[i]);
    }
    return sb.toString();
  }

  private static String stringAt(String input, int index) {
    return input.substring(index, index + 1);
  }
}
