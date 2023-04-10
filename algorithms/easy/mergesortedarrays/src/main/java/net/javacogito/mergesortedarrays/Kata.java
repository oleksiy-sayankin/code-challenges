package net.javacogito.mergesortedarrays;

import java.util.*;

public class Kata {

  public static int[] mergeSortedWithLinkedList(int[]... arrays) {
    List<Integer> integers = new LinkedList<>();
    for (int[] array : arrays) {
      for (int value : array) {
        integers.add(value);
      }
    }
    integers.sort(Comparator.comparingInt(o -> o));
    int size = integers.size();
    int[] result = new int[size];
    int i = 0;
    for (Integer integer : integers) {
      result[i++] = integer;
    }
    return result;
  }


  public static int[] mergeSortedWithTreeSet(int[]... arrays) {
    SortedSet<Integer> integers = new TreeSet<>();
    for (int[] array : arrays) {
      for (int value : array) {
        integers.add(value);
      }
    }
    int size = integers.size();
    int[] result = new int[size];
    int i = 0;
    for (Integer integer : integers) {
      result[i++] = integer;
    }
    return result;
  }


  public static int[] mergeSortedWithPriorityQueue(int[]... arrays) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    int length = 0;
    for (int[] array : arrays) {
      length += array.length;
      for (int value : array) {
        queue.add(value);
      }
    }
    int[] result = new int[length];
    int i = 0;
    while (!queue.isEmpty()) {
      result[i] = queue.poll();
      i++;
    }
    return result;
  }
}