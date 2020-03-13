package net.javacogito.ulamsequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Challenge {
  private static final List<Integer> ULAM = new ArrayList<>();

  public static int ulam(int n) {
    init();
    while (ULAM.size() < n) {
      ULAM.add(getNextNumber());
    }
    return ULAM.get(ULAM.size() - 1);
  }

  private static int getNextNumber() {
    int size = ULAM.size();
    int last = ULAM.get(size - 1);
    Map<Integer, Integer> sums = new HashMap<>();
    for (int i = 0; i <= size - 2; i++) {
      for (int j = i + 1; j <= size - 1; j++) {
        int sum = ULAM.get(i) + ULAM.get(j);
        if (sum > last) {
          sums.put(sum, sums.get(sum) == null ? 1 : sums.get(sum) + 1);
        }
      }
    }
    int result = Integer.MAX_VALUE;
    for (Map.Entry<Integer, Integer> entry : sums.entrySet()) {
      int key = entry.getKey();
      if (entry.getValue() == 1 && key < result) {
        result = key;
      }
    }
    return result;
  }

  private static void init() {
    ULAM.clear();
    ULAM.add(1);
    ULAM.add(2);
  }
}
