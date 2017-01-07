package net.javacogito.pointincircle;

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
      Circle circle = parseCircle(inputLine);
      Point point = parsePoint(inputLine);
      if(circle.has(point)){
        System.out.println("true");
      } else {
        System.out.println("false");
      }
    }
  }

  private static Circle parseCircle (String inputLine){
    String[] rawData = inputLine.split("; ");
    String centerRawData = rawData[0].substring(9, rawData[0].length() - 1);
    String[] centerCoordinate = centerRawData.split(", ");
    double x = Double.parseDouble(centerCoordinate[0]);
    double y = Double.parseDouble(centerCoordinate[1]);
    String radiusRawData = rawData[1].substring(8);
    double radius = Double.parseDouble(radiusRawData);
    return new Circle(new Point(x, y), radius);
  }

  private static Point parsePoint (String inputLine){
    String[] rawData = inputLine.split("; ");
    String pointRawData = rawData[2].substring(8, rawData[2].length() - 1);
    String[] centerCoordinate = pointRawData.split(", ");
    double x = Double.parseDouble(centerCoordinate[0]);
    double y = Double.parseDouble(centerCoordinate[1]);
    return new Point(x, y);
  }


  private static class Circle{
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius){
      this.center = center;
      this.radius = radius;
    }

    public boolean has(Point point){
      double distance = Math.sqrt((center.x - point.x) * (center.x - point.x) + (center.y - point.y) * (center.y - point.y));
      return distance < radius;
    }
  }

  private static class Point{
    private final double x;
    private final double y;

    public Point(double x, double y){
      this.x = x;
      this.y = y;
    }
  }
}