package net.javacogito.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class Polygon implements IPolygon {
  private List<IPoint> points;

  public Polygon() {
    points = new ArrayList<IPoint>();
  }

  public Polygon(IPolygon polygon) {
    points = new ArrayList<IPoint>();
    for (IPoint point : polygon.points()) {
      points.add(new Point(point));
    }
  }

  @Override
  public boolean has(IPoint point) {
    return distanceTo(point) < MathConstants.ERROR;
  }

  @Override
  public boolean contains(IPoint point) {
    ISegment segment = MathHelper.nearestSegment(toSegmentsClockwise(), point);
    return isOnTheRightSide(segment.toVector(), new Point(point.getX() - segment.getPoint1X(), point.getY() - segment.getPoint1Y()));
  }

  @Override
  public double distanceTo(IPoint point) {
    if (points.isEmpty()) {
      return Double.NaN;
    }
    return MathHelper.nearestSegment(toSegmentsClockwise(), point).distanceTo(point);
  }

  @Override
  public void translateInDirection(IVector vector) {
    for (IPoint point : points) {
      point.translateInDirection(vector);
    }
  }

  @Override
  public void scale(double coef) {
    for (IPoint point : points) {
      point.scale(coef);
    }
  }

  @Override
  public List<ISegment> toSegmentsClockwise() {
    List<ISegment> segments = new ArrayList<ISegment>();
    for (int i = 0; i <= points.size() - 2; i++) {
      segments.add(Primitives.unmodifiableSegment(new Segment(points.get(i), points.get(i + 1))));
    }
    segments.add(Primitives.unmodifiableSegment(new Segment(points.get(points.size() - 1), points.get(0))));
    return segments;
  }

  @Override
  public void addPoint(IPoint point) {
    points.add(point.copy());
  }

  @Override
  public List<IPoint> points() {
    List<IPoint> unmodifiablePoints = new ArrayList<IPoint>();
    for (IPoint point : points) {
      unmodifiablePoints.add(Primitives.unmodifiablePoint(point));
    }
    return Collections.unmodifiableList(unmodifiablePoints);
  }

  @Override
  public void stretch(IPoint stretchPoint, IVector direction) {
    double rightBound = rightBound();
    double leftBound = leftBound();
    double upBound = upBound();
    double downBound = downBound();
    assert (leftBound <= rightBound);
    assert (downBound <= upBound);
    double dX = direction.getX();
    double dY = direction.getY();
    double stretchPointX = stretchPoint.getX();
    double stretchPointY = stretchPoint.getY();
    for (IPoint point : points) {
      double x = point.getX();
      double y = point.getY();
      double coefX = 1;
      double coefY = 1;
      if (stretchPointX >= rightBound) {
        coefX = (x - leftBound) / (stretchPointX - leftBound);
      }
      if (stretchPointX <= leftBound) {
        coefX = (rightBound - x) / (rightBound - stretchPointX);
      }
      if (stretchPointY >= upBound) {
        coefY = (y - downBound) / (stretchPointY - downBound);
      }
      if (stretchPointY <= downBound) {
        coefY = (upBound - y) / (upBound - stretchPointY);
      }
      IVector vector = new Vector(dX * coefX, dY * coefY);
      point.translateInDirection(vector);
    }
  }

  private static boolean isOnTheRightSide(IVector a, IPoint point) {
    return MathHelper.signedArea(a, new Vector(point)) < 0;
  }

  @Override
  public double width() {
    assert (rightBound() - leftBound() >= 0);
    return rightBound() - leftBound();
  }

  @Override
  public double height() {
    assert (upBound() - downBound() >= 0);
    return upBound() - downBound();
  }

  @Override
  public MathDimension dimension() {
    return new MathDimension(width(), height());
  }

  @Override
  public IRectangle bounds() {
    return Primitives.unmodifiableRectangle(new Rectangle(leftBound(), rightBound(), upBound(), downBound()));
  }

  @Override
  public int pointsCount() {
    return points.size();
  }

  @Override
  public double area() {
    if (pointsCount() <= 2) {
      return 0;
    }
    double area = 0;
    List<ISegment> segments = this.toSegmentsClockwise();
    for (ISegment segment : segments) {
      double h = segment.getEndPoint().getX() - segment.getStartPoint().getX();
      if (MathHelper.equalsZero(h)) {
        continue;
      }
      double a = segment.getStartPoint().getY();
      double b = segment.getEndPoint().getY();
      double trapezoidArea = (a + b) * h / 2;
      area += trapezoidArea;
    }
    assert (area >= 0);
    return area;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    boolean first = true;
    for (IPoint point : points) {
      if (first) {
        first = false;
      } else {
        sb.append(", ");
      }
      sb.append(point);
    }
    sb.append("}");
    return sb.toString();
  }

  protected void setPoints(List<IPoint> newPoints) {
    points = newPoints;
  }


  @Override
  public double leftBound() {
    double leftBound = Double.MAX_VALUE;
    for (IPoint point : points) {
      if (point.getX() < leftBound) {
        leftBound = point.getX();
      }
    }
    return leftBound;
  }

  @Override
  public double rightBound() {
    double rightBound = -Double.MAX_VALUE;
    for (IPoint point : points) {
      if (point.getX() > rightBound) {
        rightBound = point.getX();
      }
    }
    return rightBound;
  }

  @Override
  public double upBound() {
    double upBound = -Double.MAX_VALUE;
    for (IPoint point : points) {
      if (point.getY() > upBound) {
        upBound = point.getY();
      }
    }
    return upBound;
  }

  @Override
  public double downBound() {
    double downBound = Double.MAX_VALUE;
    for (IPoint point : points) {
      if (point.getY() < downBound) {
        downBound = point.getY();
      }
    }
    return downBound;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (!(other instanceof IPolygon)) {
      return false;
    }
    IPolygon otherPolygon = (IPolygon) other;
    if (this.pointsCount() != otherPolygon.pointsCount()) {
      return false;
    }
    return this.points().equals(otherPolygon.points());
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (IPoint point : points) {
      result += point.hashCode();
    }
    return result;
  }
}
