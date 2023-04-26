package net.javacogito.closestpairofpoints;

import java.util.*;

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
    Point middlePoint = sortedByX.get(middlePointIndex);
    List<Point> sortedByXLeftSide = sortedByX.subList(0, middlePointIndex);
    List<Point> sortedByXRightSide = sortedByX.subList(middlePointIndex, size);
    List<Point> sortedByYLeftSide = new ArrayList<>();
    List<Point> sortedByYRightSide = new ArrayList<>();

    // Split presorted by Y points into two sets: from left side of the middle point and from the right side
    for (Point currentPoint : sortedByY) {
      if (currentPoint.x < middlePoint.x) {
        sortedByYLeftSide.add(currentPoint);
      } else {
        sortedByYRightSide.add(currentPoint);
      }
    }
    // Find min distance to the left side of the middle point
    List<Point> leftMinPoints = recursive(sortedByXLeftSide, sortedByYLeftSide);
    // Find min distance to the right side of the middle point
    List<Point> rightMinPoints = recursive(sortedByXRightSide, sortedByYRightSide);

    // Find min distance between min from left and min from right side of the middle point
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
    /*
     Find all points in the interval of minDistance around middlePoint

      | <-minDistance->  middlePoint
      |-------------------X-------------------|
    */
    List<Point> inBand = new ArrayList<>();
    for (Point currentPoint : sortedByY) {
      if (Math.abs(currentPoint.x - middlePoint.x) < minDistance) {
        inBand.add(currentPoint);
      }
    }
    // process only six nearest points starting from each other in the list
    for (int i = 0; i <= inBand.size() - 1; i++) {
      for (int j = i + 1; j <= Math.min(i + 7, inBand.size()) - 1; j++) {
        double currentDistance = distance(inBand.get(i), inBand.get(j));
        if (currentDistance < minDistance) {
          minDistance = currentDistance;
          minPoints.clear();
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
   * @param points list of three points sorted by X value
   * @return two nearest points
   */
  private static List<Point> bruteForce(List<Point> points) {
    List<Point> result = new ArrayList<>();
    double min = Double.MAX_VALUE;
    int size = points.size();
    for (int i = 0; i <= size - 1; i++) {
      for (int j = i + 1; j <= size - 1; j++) {
        double distance = distance(points.get(i), points.get(j));
        if (distance < min) {
          min = distance;
          result.clear();
          result.add(points.get(i));
          result.add(points.get(j));
        }
      }
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
