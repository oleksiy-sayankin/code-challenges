package net.javacogito.overlappingrectangles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      Rectangle[] rectangles = parseRectangles(inputLine);
      if(rectangles[0].isOverlap(rectangles[1])){
        System.out.println("True");
      } else {
        System.out.println("False");
      }
    }
  }

  private static Rectangle[] parseRectangles(String inputLine){
    Rectangle[] result = new Rectangle[2];
    String[] rawData = inputLine.split(",");
    Point upperLeftPoint1 = new Point(Integer.parseInt(rawData[0]), Integer.parseInt(rawData[1]));
    Point lowerRightPoint1 = new Point(Integer.parseInt(rawData[2]), Integer.parseInt(rawData[3]));
    Point upperLeftPoint2 = new Point(Integer.parseInt(rawData[4]), Integer.parseInt(rawData[5]));
    Point lowerRightPoint2 = new Point(Integer.parseInt(rawData[6]), Integer.parseInt(rawData[7]));
    result[0] = new Rectangle(upperLeftPoint1, lowerRightPoint1);
    result[1] = new Rectangle(upperLeftPoint2, lowerRightPoint2);
    return result;
  }

  private static class Rectangle{
    private final Point[] points = new Point[4];
    private final Line[] lines = new Line[4];

    public Rectangle(Point upperLeftPoint, Point lowerRightPoint){
      points[0] = new Point(upperLeftPoint.getX(), lowerRightPoint.getY());
      points[1] = lowerRightPoint;
      points[2] = new Point(lowerRightPoint.getX(), upperLeftPoint.getY());
      points[3] = upperLeftPoint;
      lines[0] = new Line(points[0], points[1]);
      lines[1] = new Line(points[1], points[2]);
      lines[2] = new Line(points[2], points[3]);
      lines[3] = new Line(points[3], points[0]);
    }

    public boolean isInside(Point point){
      boolean isInside = true;
      for (Line line : lines){
        if(line.func(point) < 0) {
          isInside = false;
          break;
        }
      }
      return isInside;
    }

    public boolean isOverlap(Rectangle rectangle){
      boolean otherInsideThis = false;
      for(Point point : rectangle.getPoints()){
        if(isInside(point)){
          otherInsideThis = true;
          break;
        }
      }
      boolean thisInsideOther = false;
      for (Point point : this.getPoints()){
        if(rectangle.isInside(point)){
          thisInsideOther = true;
          break;
        }
      }
      return otherInsideThis || thisInsideOther;
    }

    public Point[] getPoints(){
      return points;
    }
  }

  private static class Point{
    private final int x;
    private final int y;
    public Point(int x, int y){
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

  private static class Line{
    private final Point[] points = new Point[2];
    private final long a;
    private final long b;
    private final long c;

    public Line(Point point1, Point point2){
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

    public long func(Point t){
      return a * t.getX() + b * t.getY() + c;
    }
  }
}