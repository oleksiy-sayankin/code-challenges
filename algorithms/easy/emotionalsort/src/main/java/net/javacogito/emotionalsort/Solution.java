package net.javacogito.emotionalsort;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
  private static final Map<String, Integer> codes = new HashMap<>();
  private static final Map<Integer, String> emoji = new HashMap<>();

  static {
    codes.put(":D", 0);
    codes.put(":)", 1);
    codes.put(":|", 2);
    codes.put(":(", 3);
    codes.put("T_T", 4);
  }

  static {
    emoji.put(0, ":D");
    emoji.put(1, ":)");
    emoji.put(2, ":|");
    emoji.put(3, ":(");
    emoji.put(4, "T_T");
  }


  public static String[] sortEmotions(boolean order, String[] emotions) {
    int length = emotions.length;
    int[] resultCodes = new int[length];
    int i = 0;
    for (String emotion : emotions) {
      resultCodes[i++] = codes.get(emotion);
    }
    Arrays.sort(resultCodes);
    if (!order) {
      resultCodes = reverse(resultCodes);
    }
    String[] result = new String[length];
    i = 0;
    for (int resultCode : resultCodes) {
      result[i++] = emoji.get(resultCode);
    }
    return result;
  }


  private static int[] reverse(int[] values) {
    int[] result = new int[values.length];
    int i = values.length - 1;
    for (int value : values) {
      result[i--] = value;
    }
    return result;
  }
}
