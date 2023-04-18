package net.javacogito.sumofintervals;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Interval implements Comparable<Interval> {
  public Interval(int left, int right) {
    this.left = left;
    this.right = right;
  }

  public static int sumIntervals(int[][] inputs) {
    if (inputs.length == 0) {
      return 0;
    }

    SortedSet<Interval> intervals = new TreeSet<>();
    for (int[] input : inputs) {
      intervals.add(new Interval(input[0], input[1]));
    }
    if (intervals.size() == 1) {
      return intervals.first().length();
    }
    boolean first = true;
    Interval previous = intervals.first();
    Interval last = intervals.last();
    int length = 0;
    for (Interval current : intervals) {
      if (first) {
        first = false;
        continue;
      }
      if (current.equals(last)) {
        if (previous.canBeMerged(current)) {
          previous = previous.merge(current);
          length += previous.length();
        } else {
          length += previous.length() + last.length();
        }
      } else {
        if (previous.canBeMerged(current)) {
          previous = previous.merge(current);
        } else {
          length += previous.length();
          previous = current;
        }
      }
    }
    return length;
  }


  private boolean canBeMerged(Interval o) {
    if (o.equals(this)) {
      return true;
    }
    /*
     *        XXXXXXXXX <-- this
     *             XXXXXXXX   <-- o
     */
    if (this.left <= o.left) {
      return this.right >= o.left;
    }
    /*
     *        XXXXXXXXX <-- this
     *     XXXXXXXX   <-- o
     */
    if (this.right >= o.right) {
      return this.left >= o.right;
    }
    return false;
  }

  private Interval merge(Interval o) {
    if (o.equals(this)) {
      return this;
    }
    if (this.left <= o.left) {
      /*
       *        XXXXXXXXX <-- this
       *             XXXXXXXX   <-- o
       */
      if (this.right <= o.right) {
        return new Interval(this.left, o.right);
      }
      /*
       *        XXXXXXXXXXXXXXXXX <-- this
       *             XXXXXXXX   <-- o
       */
      return new Interval(this.left, this.right);
    }

    /*
     *        XXXXXXXXX <-- this
     *     XXXXXXXX   <-- o
     */
    if (this.right >= o.right) {
      return new Interval(o.left, this.right);
    }
    /*
     *        XXXXXXXXX <-- this
     *     XXXXXXXXXXXXXXXX   <-- o
     */

    return new Interval(o.left, o.right);
  }

  private int length() {
    return right - left;
  }


  private final int left;
  private final int right;


  @Override
  public int compareTo(Interval o) {
    return this.left != o.left ? this.left - o.left : this.right - o.right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Interval interval = (Interval) o;
    return left == interval.left && right == interval.right;
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }

  @Override
  public String toString() {
    return "[" + left + ", " + right + ']';
  }
}
