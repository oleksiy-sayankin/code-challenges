package net.javacogito.closestpairofpoints;

import java.util.*;

public class Kata {
  public static List<Point> closestPair(List<Point> points) {
    List<Point> sortedByX = new ArrayList<>(points);
    List<Point> sortedByY = new ArrayList<>(points);

    sortedByX.sort(Comparator.comparingDouble(o -> o.x));
    sortedByY.sort(Comparator.comparingDouble(o -> o.y));

    return Arrays.asList();
  }

}
