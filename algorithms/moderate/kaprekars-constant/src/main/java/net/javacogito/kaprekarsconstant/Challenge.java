package net.javacogito.kaprekarsconstant;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class Challenge {
  public static int kaprekar(int num) {
    final int kaprekar = 6174;
    int currentNum = num;
    int iteration = 0;
    do {
      currentNum = toInt(toArrayDesc(currentNum)) - toInt(toArrayAsc(currentNum));
      iteration ++;
    } while (currentNum != kaprekar);
    return iteration;
  }

  public static Integer[] toArrayDesc(int num) {
    Integer[] result =  toArray(num);
    Arrays.sort(result, Collections.reverseOrder());
    return result;
  }

  public static Integer[] toArrayAsc(int num) {
    Integer[] result =  toArray(num);
    Arrays.sort(result);
    return result;
  }

  public static int toInt(Integer[] digits){
    return digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3];
  }

  public static Integer[] toArray(int num) {
    DecimalFormat myFormatter = new DecimalFormat("0000");
    String digits = myFormatter.format(num);
    Integer[] result =  new Integer[4];
    int i = 0;
    for (char digit : digits.toCharArray()) {
      result[i] = digit - '0';
      i++;
    }
    return result;
  }
}
