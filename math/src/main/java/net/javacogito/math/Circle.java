package net.javacogito.math;

import net.javacogito.math.equation.Solution;
import net.javacogito.math.equation.Solver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public class Circle implements ICircle {
  private IPoint center;
  private double radius;

  public Circle(IPoint center, double radius) {
    this.center = center;
    this.radius = radius;
  }

  public Circle(double x, double y, double radius) {
    this.center = new Point(x, y);
    this.radius = radius;
  }

  public double getX() {
    return center.getX();
  }

  public double getY() {
    return center.getY();
  }

  @Override
  public double getRadius() {
    return radius;
  }

  @Override
  public Set<IPoint> intersection(ILine line) {
    Set<IPoint> intersectionPoints = new HashSet<IPoint>();
    LineCoef coef = line.coef();
    double a = coef.a;
    double b = coef.b;
    double c = coef.c;
    double x0 = center.getX();
    double y0 = center.getY();
    double r = radius;
    if (!MathHelper.equalsZero(b)) {
      double kForLine = -a / b;
      double bForLine = -c / b;
      double qa = 1 + kForLine * kForLine;
      double qb = -2 * x0 + 2 * kForLine * (bForLine - y0);
      double qc = x0 * x0 + (bForLine - y0) * (bForLine - y0) - r * r;
      Solution solution = Solver.quadratic(qa, qb, qc);
      switch (solution.getResolution()) {
        case NO_REAL_ROOTS:
          return Collections.<IPoint>emptySet();
        case ONE_REAL_ROOT:
          double intersectionX = solution.getRoots()[0];
          double intersectionY = kForLine * intersectionX + bForLine;
          IPoint point = new Point(intersectionX, intersectionY);
          intersectionPoints.add(point);
          return intersectionPoints;
        case TWO_REAL_ROOTS:
          double intersectionXFirst = solution.getRoots()[0];
          double intersectionYFirst = kForLine * intersectionXFirst + bForLine;
          IPoint pointFirst = new Point(intersectionXFirst, intersectionYFirst);
          intersectionPoints.add(pointFirst);
          double intersectionXSecond = solution.getRoots()[1];
          double intersectionYSecond = kForLine * intersectionXSecond + bForLine;
          IPoint pointSecond = new Point(intersectionXSecond, intersectionYSecond);
          intersectionPoints.add(pointSecond);
          return intersectionPoints;
      }
    }
    if (!MathHelper.equalsZero(a)) {
      double intersectionX = -c / a;
      double qa = 1;
      double qb = -2 * y0;
      double qc = (intersectionX - x0) * (intersectionX - x0) + y0 * y0 - r * r;
      Solution solution = Solver.quadratic(qa, qb, qc);
      switch (solution.getResolution()) {
        case NO_REAL_ROOTS:
          return Collections.<IPoint>emptySet();
        case ONE_REAL_ROOT:
          double intersectionY = solution.getRoots()[0];
          IPoint point = new Point(intersectionX, intersectionY);
          intersectionPoints.add(point);
          return intersectionPoints;
        case TWO_REAL_ROOTS:
          double intersectionYFirst = solution.getRoots()[0];
          IPoint pointFirst = new Point(intersectionX, intersectionYFirst);
          intersectionPoints.add(pointFirst);
          double intersectionYSecond = solution.getRoots()[1];
          IPoint pointSecond = new Point(intersectionX, intersectionYSecond);
          intersectionPoints.add(pointSecond);
          return intersectionPoints;
      }
    }
    return Collections.<IPoint>emptySet();
  }

  @Override
  public Set<IPoint> intersection(ISegment segment) {
    ILine line = segment.toLine();
    Set<IPoint> intersectionPoints = new HashSet<IPoint>();
    Set<IPoint> lineIntersectionPoints = intersection(line);
    for (IPoint point : lineIntersectionPoints) {
      if (segment.has(point)) {
        intersectionPoints.add(point);
      }
    }
    return intersectionPoints;
  }

  @Override
  public Set<IPoint> intersection(IRay ray) {
    ILine line = ray.toLine();
    Set<IPoint> intersectionPoints = new HashSet<IPoint>();
    Set<IPoint> lineIntersectionPoints = intersection(line);
    for (IPoint point : lineIntersectionPoints) {
      if (ray.has(point)) {
        intersectionPoints.add(point);
      }
    }
    return intersectionPoints;
  }

  @Override
  public IPoint getCenter() {
    return center;
  }

  @Override
  public String toString() {
    return "{" + center + ", radius = " + radius + "}";
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (!(other instanceof Circle)) {
      return false;
    }
    if (other == this) {
      return true;
    }
    Circle otherCircle = (Circle) other;
    return this.center.equals(otherCircle.center) && MathHelper.equalsZero(this.radius - otherCircle.radius);
  }

  @Override
  public double width() {
    return 2 * radius;
  }

  @Override
  public double height() {
    return 2 * radius;
  }

  @Override
  public MathDimension dimension() {
    return new MathDimension(width(), height());
  }

  @Override
  public double downBound() {
    return center.getY() - radius;
  }

  @Override
  public double upBound() {
    return center.getY() + radius;
  }

  @Override
  public double rightBound() {
    return center.getX() + radius;
  }

  @Override
  public double leftBound() {
    return center.getX() - radius;
  }

  @Override
  public double area() {
    return Math.PI * Math.PI * radius;
  }

  @Override
  public boolean contains(IPoint point) {
    return center.distanceTo(point) < radius;
  }

  @Override
  public boolean has(IPoint point) {
    return MathHelper.equalsZero(center.distanceTo(point) - radius);
  }

  @Override
  public double distanceTo(IPoint point) {
    return point.distanceTo(center) - radius;
  }

  @Override
  public void translateInDirection(IVector vector) {
    center.translateInDirection(vector);
  }

  @Override
  public void scale(double coef) {
    radius *= coef;
  }
}
