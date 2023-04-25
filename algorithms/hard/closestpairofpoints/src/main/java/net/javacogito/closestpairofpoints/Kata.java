package net.javacogito.closestpairofpoints;

import java.util.*;

// FIXME: Passed: 7 Failed: 2 Exit Code: 1
public class Kata {
  public static List<Point> closestPair(List<Point> points) {
    List<Point> sortedByX = new ArrayList<>(points);
    List<Point> sortedByY = new ArrayList<>(points);
    sortedByX.sort(Comparator.comparingDouble(o -> o.x));
    sortedByY.sort(Comparator.comparingDouble(o -> o.y));
    return recursive(sortedByX, sortedByY);
  }

  private static List<Point> recursive(List<Point> sortedByX, List<Point> sortedByY) {
    int size = sortedByX.size();
    if (size <= 3) {
      return bruteForce(sortedByX);
    }
    int middlePointIndex = size / 2;
    List<Point> sortedByXLeftSide = sortedByX.subList(0, middlePointIndex);
    List<Point> sortedByXRightSide = sortedByX.subList(middlePointIndex, size);
    List<Point> sortedByYLeftSide = new ArrayList<>();
    List<Point> sortedByYRightSide = new ArrayList<>();

    for (int i = 0; i <= sortedByY.size() - 1; i++) {
      Point currentPoint = sortedByY.get(i);
      if (currentPoint.x < sortedByX.get(middlePointIndex).x) {
        sortedByYLeftSide.add(currentPoint);
      } else {
        sortedByYRightSide.add(currentPoint);
      }

    }
    // Find min distance to the left side of the middle point
    List<Point> leftMinPoints = recursive(sortedByXLeftSide, sortedByYLeftSide);
    // Find min distance to the right side of the middle point
    List<Point> rightMinPoints = recursive(sortedByXRightSide, sortedByYRightSide);
    double leftMinDistance = distance(leftMinPoints.get(0), leftMinPoints.get(1));
    double rightMinDistance = distance(rightMinPoints.get(0), rightMinPoints.get(1));
    double minDistance;
    List<Point> minPoints;
    if (leftMinDistance < rightMinDistance) {
      minDistance = leftMinDistance;
      minPoints = leftMinPoints;
    } else {
      minDistance = rightMinDistance;
      minPoints = rightMinPoints;
    }
    List<Point> inBand = new ArrayList<>();
    int i = 0;
    for (Point point : sortedByY) {
      if (Math.abs(point.x - sortedByX.get(middlePointIndex).x) < minDistance) {
        inBand.add(point);
      }
      i++;
    }
    // process only six nearest points starting from each other in the list
    for (i = 0; i <= inBand.size() - 1; i++) {
      for (int j = i + 1; j <= Math.min(i + 7, inBand.size()) - 1; j++) {
        double currentDistance = distance(inBand.get(i), inBand.get(j));
        if (currentDistance < minDistance) {
          minPoints = new ArrayList<>();
          minPoints.add(inBand.get(i));
          minPoints.add(inBand.get(j));
        }
      }
    }
    return minPoints;
  }

  /**
   * Finds two nearest points when n equals 3 or 2.
   *
   * @param sortedByX list of three points sorted by X value
   * @return two nearest points
   */
  private static List<Point> bruteForce(List<Point> sortedByX) {
    if (sortedByX.size() == 2) {
      List<Point> result = new ArrayList<>();
      result.add(sortedByX.get(0));
      result.add(sortedByX.get(1));
      return result;
    }
    double distance0to1 = distance(sortedByX.get(0), sortedByX.get(1));
    double distance0to2 = distance(sortedByX.get(0), sortedByX.get(2));
    double distance1to2 = distance(sortedByX.get(1), sortedByX.get(2));
    double min = Math.min(distance0to1, Math.min(distance0to2, distance1to2));
    List<Point> result = new ArrayList<>();
    if (min == distance0to1) {
      result.add(sortedByX.get(0));
      result.add(sortedByX.get(1));
    } else if (min == distance0to2) {
      result.add(sortedByX.get(0));
      result.add(sortedByX.get(2));
    } else {
      result.add(sortedByX.get(1));
      result.add(sortedByX.get(2));
    }
    return result;
  }

  /**
   * Calculates distance between two points.
   *
   * @param p1 first point
   * @param p2 second point
   * @return distance between two points
   */
  private static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }
}
