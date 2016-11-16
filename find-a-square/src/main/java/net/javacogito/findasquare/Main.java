package net.javacogito.findasquare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

  private static final double ERROR = 0.0000001;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if(parseRectangle(inputLine).isSquare()){
        System.out.println("true");
      }else{
        System.out.println("false");
      }
    }
  }

  private static Rectangle parseRectangle(String inputLine){
    String[] rawData = inputLine.split(", ");
    String[] point1RawData = rawData[0].substring(1, rawData[0].length() - 1).split(",");
    String[] point2RawData = rawData[1].substring(1, rawData[1].length() - 1).split(",");
    String[] point3RawData = rawData[2].substring(1, rawData[2].length() - 1).split(",");
    String[] point4RawData = rawData[3].substring(1, rawData[3].length() - 1).split(",");
    Point point1 = new Point(Integer.parseInt(point1RawData[0]), Integer.parseInt(point1RawData[1]));
    Point point2 = new Point(Integer.parseInt(point2RawData[0]), Integer.parseInt(point2RawData[1]));
    Point point3 = new Point(Integer.parseInt(point3RawData[0]), Integer.parseInt(point3RawData[1]));
    Point point4 = new Point(Integer.parseInt(point4RawData[0]), Integer.parseInt(point4RawData[1]));
    return new Rectangle(point1, point2, point3, point4);
  }

  private static class Rectangle{
    private final Point[] points = new Point[4];

    public Rectangle(Point point0, Point point1, Point point2, Point point3){
      points[0] = point0;
      points[1] = point1;
      points[2] = point2;
      points[3] = point3;
    }

    public boolean isSquare(){
      double[] sides = new double[6];
      sides[0] = new Segment(points[0], points[1]).length();
      sides[1] = new Segment(points[1], points[2]).length();
      sides[2] = new Segment(points[2], points[3]).length();
      sides[3] = new Segment(points[3], points[0]).length();
      sides[4] = new Segment(points[0], points[2]).length();
      sides[5] = new Segment(points[1], points[3]).length();

      if(sides[0] == 0 || sides[1] == 0 || sides[2] == 0 || sides[3] == 0 || sides[4] == 0 || sides[5] == 0){
        return false;
      }

      Arrays.sort(sides);
      return eq(sides[0], sides[1]) && eq(sides[1], sides[2]) && eq(sides[2], sides[3]) && eq(sides[3], sides[0])
        && eq(sides[4], sides[5]);
    }
  }

  private static boolean eq(double a , double b){
    return Math.abs(a - b) < ERROR;
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

  private static class Segment{
    private final Point[] points = new Point[2];

    public Segment(Point point1, Point point2){
      this.points[0] = point1;
      this.points[1] = point2;
    }

    public double length(){
      return Math.sqrt((points[1].getX() - points[0].getX()) * (points[1].getX() - points[0].getX())
        + (points[1].getY() - points[0].getY()) * (points[1].getY() - points[0].getY()));
    }
  }
}