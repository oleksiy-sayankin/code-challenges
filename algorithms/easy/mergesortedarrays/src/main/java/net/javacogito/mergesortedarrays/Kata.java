package net.javacogito.mergesortedarrays;

import java.util.PriorityQueue;

public class Kata {

  public static int[] mergeSorted(int[]... arrays) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    int length = 0;
    for(int[] array : arrays){
      length += array.length;
      for(int value : array){
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