package net.javacogito.mergesortedarrays;

import org.junit.Test;

import static net.javacogito.mergesortedarrays.Kata.*;
import static org.junit.Assert.assertArrayEquals;

public class KataTest {

  @Test
  public void testMergeSortedWithPriorityQueue() {
    assertArrayEquals(new int[]{1, 3, 5, 7, 9, 11, 13, 14, 16, 18, 19, 20, 21, 22}, mergeSortedWithPriorityQueue(new int[]{1, 3, 5, 7, 9, 11, 13}, new int[]{14, 16, 18}, new int[]{19, 20, 21, 22}));
    assertArrayEquals(new int[]{1, 5, 7, 10, 15, 17, 20, 25, 27, 30, 35, 37}, mergeSortedWithPriorityQueue(new int[]{1, 10, 20, 30}, new int[]{5, 15, 25, 35}, new int[]{7, 17, 27, 37}));
    assertArrayEquals(new int[]{3, 5, 7, 33, 55, 77, 333, 555, 777}, mergeSortedWithPriorityQueue(new int[]{777, 77, 7}, new int[]{555, 55, 5}, new int[]{333, 33, 3}));
  }

  @Test
  public void testMergeSortedWithSortedSet() {
    assertArrayEquals(new int[]{1, 3, 5, 7, 9, 11, 13, 14, 16, 18, 19, 20, 21, 22}, mergeSortedWithTreeSet(new int[]{1, 3, 5, 7, 9, 11, 13}, new int[]{14, 16, 18}, new int[]{19, 20, 21, 22}));
    assertArrayEquals(new int[]{1, 5, 7, 10, 15, 17, 20, 25, 27, 30, 35, 37}, mergeSortedWithTreeSet(new int[]{1, 10, 20, 30}, new int[]{5, 15, 25, 35}, new int[]{7, 17, 27, 37}));
    assertArrayEquals(new int[]{3, 5, 7, 33, 55, 77, 333, 555, 777}, mergeSortedWithTreeSet(new int[]{777, 77, 7}, new int[]{555, 55, 5}, new int[]{333, 33, 3}));
  }

  @Test
  public void testMergeSortedWithLinkedList() {
    assertArrayEquals(new int[]{1, 3, 5, 7, 9, 11, 13, 14, 16, 18, 19, 20, 21, 22}, mergeSortedWithLinkedList(new int[]{1, 3, 5, 7, 9, 11, 13}, new int[]{14, 16, 18}, new int[]{19, 20, 21, 22}));
    assertArrayEquals(new int[]{1, 5, 7, 10, 15, 17, 20, 25, 27, 30, 35, 37}, mergeSortedWithLinkedList(new int[]{1, 10, 20, 30}, new int[]{5, 15, 25, 35}, new int[]{7, 17, 27, 37}));
    assertArrayEquals(new int[]{3, 5, 7, 33, 55, 77, 333, 555, 777}, mergeSortedWithLinkedList(new int[]{777, 77, 7}, new int[]{555, 55, 5}, new int[]{333, 33, 3}));
  }


}
