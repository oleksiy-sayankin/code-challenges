package net.javacogito.sumofintervals;

import org.junit.Test;

import static net.javacogito.sumofintervals.Interval.sumIntervals;
import static org.junit.Assert.assertEquals;

public class IntervalTest {
  @Test
  public void shouldHandleEmptyIntervals() {
    assertEquals(0, sumIntervals(new int[][]{}));
    assertEquals(0, sumIntervals(new int[][]{{4, 4}, {6, 6}, {8, 8}}));
  }

  @Test
  public void shouldAddDisjoinedIntervals() {
    assertEquals(9, sumIntervals(new int[][]{{1, 2}, {6, 10}, {11, 15}}));
    assertEquals(11, sumIntervals(new int[][]{{4, 8}, {9, 10}, {15, 21}}));
    assertEquals(7, sumIntervals(new int[][]{{-1, 4}, {-5, -3}}));
    assertEquals(78, sumIntervals(new int[][]{{-245, -218}, {-194, -179}, {-155, -119}}));
  }

  @Test
  public void shouldHandleLargeIntervals() {
    assertEquals(2_000_000_000, sumIntervals(new int[][]{{-1_000_000_000, 1_000_000_000}}));
    assertEquals(100_000_030, sumIntervals(new int[][]{{0, 20}, {-100_000_000, 10}, {30, 40}}));
  }

  @Test
  public void shouldAddAdjacentIntervals() {
    assertEquals(54, sumIntervals(new int[][]{{1, 2}, {2, 6}, {6, 55}}));
    assertEquals(23, sumIntervals(new int[][]{{-2, -1}, {-1, 0}, {0, 21}}));
  }

  @Test
  public void shouldAddOverlappingIntervals() {
    assertEquals(7, sumIntervals(new int[][]{{1, 4}, {7, 10}, {3, 5}}));
    assertEquals(6, sumIntervals(new int[][]{{5, 8}, {3, 6}, {1, 2}}));
    assertEquals(19, sumIntervals(new int[][]{{1, 5}, {10, 20}, {1, 6}, {16, 19}, {5, 11}}));
  }

  @Test
  public void shouldHandleMixedIntervals() {
    assertEquals(13, sumIntervals(new int[][]{{2, 5}, {-1, 2}, {-40, -35}, {6, 8}}));
    assertEquals(1234, sumIntervals(new int[][]{{-7, 8}, {-2, 10}, {5, 15}, {2000, 3150}, {-5400, -5338}}));
    assertEquals(158, sumIntervals(new int[][]{{-101, 24}, {-35, 27}, {27, 53}, {-105, 20}, {-36, 26},}));
  }

  @Test
  public void shouldHandleSmallRandomIntervals() {
    assertEquals(114, sumIntervals(new int[][]{{-313, -311}, {-473, -460}, {-99, -81}, {-367, -354}, {-357, -354}, {4, 5}, {-92, -73}, {-314, -297}, {-415, -408}, {-204, -185}, {-357, -340}, {369, 373}}));
  }
}
