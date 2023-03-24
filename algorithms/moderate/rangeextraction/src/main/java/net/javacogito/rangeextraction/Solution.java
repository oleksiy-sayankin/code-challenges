package net.javacogito.rangeextraction;

public class Solution {

  private static class Range {
    private int start;
    private int end;
    private int length;

    @Override
    public String toString() {
      return start + "-" + end;
    }
  }

  public static String rangeExtraction(int[] arr) {
    int i = 0;
    StringBuilder sb = new StringBuilder();
    while (i <= arr.length - 1) {
      if (isRange(arr, i)) {
        Range range = buildRange(arr, i);
        sb.append(range);
        i += range.length;
      } else {
        sb.append(arr[i]);
        i++;
      }
      sb.append(i != arr.length ? "," : "");
    }
    return sb.toString();
  }

  private static boolean isRange(int[] arr, int startIndex) {
    if (arr.length - startIndex < 3) {
      return false;
    }
    int first = arr[startIndex];
    int second = arr[startIndex + 1];
    int third = arr[startIndex + 2];
    return second - first == 1 && third - second == 1;
  }

  private static Range buildRange(int[] arr, int startIndex) {
    Range range = new Range();
    range.start = arr[startIndex];
    int i = startIndex + 2;
    while (i <= arr.length - 2) {
      if (arr[i + 1] - arr[i] == 1) {
        i++;
      } else {
        break;
      }
    }
    range.end = arr[i];
    range.length = range.end - range.start + 1;
    return range;
  }
}
