package net.javacogito.cityblocksflyover;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final double ERROR = 0.0000001;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      int intersectionCount = 0;
      Rectangle[] rectangles = parseInputRectangles(inputLine);
      Line helicopterPath = parseHelicopterPath(inputLine);
      for (Rectangle rectangle : rectangles) {
        if (rectangle.hasIntersection(helicopterPath)) {
          intersectionCount++;
        }
      }
      System.out.println(intersectionCount);
    }
  }

  private static Line parseHelicopterPath(String inputLine) {
    String[] rowData = inputLine.split(" ");
    String[] streetsAsString = rowData[0].substring(1, rowData[0].length() - 1).split(",");
    String[] avenuesAsString = rowData[1].substring(1, rowData[1].length() - 1).split(",");
    int streetsCount = streetsAsString.length;
    int[] streets = new int[streetsCount];
    for (int i = 0; i <= streetsCount - 1; i++) {
      streets[i] = Integer.parseInt(streetsAsString[i]);
    }

    int avenuesCount = avenuesAsString.length;
    int[] avenues = new int[avenuesCount];
    for (int i = 0; i <= avenuesCount - 1; i++) {
      avenues[i] = Integer.parseInt(avenuesAsString[i]);
    }


    Point start = new Point(streets[0], avenues[0]);
    Point end = new Point(streets[streetsCount - 1], avenues[avenuesCount - 1]);
    Line helicopterPath = new Line(start, end);
    return helicopterPath;
  }

  private static Rectangle[] parseInputRectangles(String inputLine) {
    String[] rowData = inputLine.split(" ");
    String[] streetsAsString = rowData[0].substring(1, rowData[0].length() - 1).split(",");
    String[] avenuesAsString = rowData[1].substring(1, rowData[1].length() - 1).split(",");
    int streetsCount = streetsAsString.length;
    int[] streets = new int[streetsCount];
    for (int i = 0; i <= streetsCount - 1; i++) {
      streets[i] = Integer.parseInt(streetsAsString[i]);
    }

    int avenuesCount = avenuesAsString.length;
    int[] avenues = new int[avenuesCount];
    for (int i = 0; i <= avenuesCount - 1; i++) {
      avenues[i] = Integer.parseInt(avenuesAsString[i]);
    }

    Rectangle[] rectangles = new Rectangle[(avenuesCount - 1) * (streetsCount - 1)];

    int rectangleIndex = 0;
    for (int i = 0; i <= avenuesCount - 2; i++) {
      for (int j = 0; j <= streetsCount - 2; j++) {
        rectangles[rectangleIndex] = new Rectangle(
          new Point(streets[j], avenues[i]),
          new Point(streets[j + 1], avenues[i]),
          new Point(streets[j + 1], avenues[i + 1]),
          new Point(streets[j], avenues[i + 1]));
        rectangleIndex++;
      }
    }

    return rectangles;
  }

  private static class Rectangle {
    private final Point[] points = new Point[4];
    private final Segment[] segments = new Segment[4];

    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
      points[0] = p1;
      points[1] = p2;
      points[2] = p3;
      points[3] = p4;
      segments[0] = new Segment(points[0], points[1]);
      segments[1] = new Segment(points[1], points[2]);
      segments[2] = new Segment(points[2], points[3]);
      segments[3] = new Segment(points[3], points[0]);
    }

    public boolean hasIntersection(Line line) {
      boolean hasIntersection = false;
      int sideIntersectionCount = 0;
      int edgeIntersectionCount = 0;
      for (Segment segment : segments) {
        if (segment.hasIntersection(line)) {
          if (segment.isEdgeIntersection(line)) {
            edgeIntersectionCount++;
          } else {
            sideIntersectionCount++;
          }
        }
      }
      if (sideIntersectionCount > 0) {
        hasIntersection = true;
      }
      if (edgeIntersectionCount == 4) {
        hasIntersection = true;
      }
      return hasIntersection;
    }

  }

  private static class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }
  }

  private static class Line {
    private final Point[] points = new Point[2];
    private final long a;
    private final long b;
    private final long c;

    public Line(Point point1, Point point2) {
      this.points[0] = point1;
      this.points[1] = point2;
      int x1 = point1.getX();
      int y1 = point1.getY();
      int x2 = point2.getX();
      int y2 = point2.getY();
      a = y1 - y2;
      b = x2 - x1;
      c = x1 * y2 - x2 * y1;
    }
  }


  private static class Segment {
    private final Point[] points = new Point[2];
    private final long a;
    private final long b;
    private final long c;

    public Segment(Point point1, Point point2) {
      this.points[0] = point1;
      this.points[1] = point2;
      int x1 = point1.getX();
      int y1 = point1.getY();
      int x2 = point2.getX();
      int y2 = point2.getY();
      a = y1 - y2;
      b = x2 - x1;
      c = x1 * y2 - x2 * y1;
    }

    public boolean hasIntersection(Line line) {
      double a1 = this.a;
      double b1 = this.b;
      double c1 = this.c;
      double a2 = line.a;
      double b2 = line.b;
      double c2 = line.c;
      double detX1 = det(c1, b1, c2, b2);
      double detX2 = det(a1, b1, a2, b2);
      double detY1 = det(a1, c1, a2, c2);
      double detY2 = detX2;
      if (eq(detX2, 0)) {
        return false;
      }
      double x = -detX1 / detX2;
      double y = -detY1 / detY2;
      double distance1 = distance(points[0].x, points[0].y, x, y);
      double distance2 = distance(points[1].x, points[1].y, x, y);
      double distance3 = distance(points[0].x, points[0].y, points[1].x, points[1].y);

      return eq(distance1 + distance2 - distance3, 0);
    }

    public boolean isEdgeIntersection(Line line) {
      double a1 = this.a;
      double b1 = this.b;
      double c1 = this.c;
      double a2 = line.a;
      double b2 = line.b;
      double c2 = line.c;
      double detX1 = det(c1, b1, c2, b2);
      double detX2 = det(a1, b1, a2, b2);
      double detY1 = det(a1, c1, a2, c2);
      double detY2 = detX2;
      if (eq(detX2, 0)) {
        return false;
      }
      double x = -detX1 / detX2;
      double y = -detY1 / detY2;
      double distance1 = distance(points[0].x, points[0].y, x, y);
      double distance2 = distance(points[1].x, points[1].y, x, y);
      return eq(distance1, 0) || eq(distance2, 0);
    }

  }

  // Math Utils

  private static double det(double a, double b, double c, double d) {
    return a * d - b * c;
  }

  private static boolean eq(double a, double b) {
    return Math.abs(a - b) < ERROR;
  }

  private static double distance(double x0, double y0, double x1, double y1) {
    return Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
  }
}