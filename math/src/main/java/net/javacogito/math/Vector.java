package net.javacogito.math;

/**
 * @author oleksiy sayankin
 */

public class Vector implements IVector {
  private double x;
  private double y;

  public Vector(IPoint point1, IPoint point2) {
    x = point2.getX() - point1.getX();
    y = point2.getY() - point1.getY();
  }

  public Vector(double aX, double aY) {
    x = aX;
    y = aY;
  }

  public Vector(IPoint point) {
    x = point.getX();
    y = point.getY();
  }

  public Vector(IVector vector) {
    x = vector.getX();
    y = vector.getY();
  }

  public void setXY(double aX, double aY) {
    x = aX;
    y = aY;
  }

  public IVector copy() {
    return new Vector(x, y);
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public double module() {
    assert (MathHelper.scalarProduct(this, this) >= 0);
    return Math.sqrt(MathHelper.scalarProduct(this, this));
  }

  @Override
  public double angle() {
    if (this.isNull()) {
      return 0;
    }
    double sinAlpha = Math.abs(y) / this.module();
    double alpha = 0;
    if (x > 0 && y > 0) {
      alpha = Math.asin(sinAlpha);
    }
    if (x < 0 && y > 0) {
      alpha = Math.PI - Math.asin(sinAlpha);
    }
    if (x < 0 && y < 0) {
      alpha = Math.PI + Math.asin(sinAlpha);
    }
    if (x > 0 && y < 0) {
      alpha = 2 * Math.PI - Math.asin(sinAlpha);
    }
    return alpha;
  }

  @Override
  public IVector reflectAgainst(ILine line) {
    IPoint point = new Point(x, y);
    ILine translatedLine = new Line(new Point(0, 0), line.toVector());
    IPoint reflectedPoint = point.reflectAgainst(translatedLine);
    return Primitives.unmodifiableVector(new Vector(reflectedPoint.getX(), reflectedPoint.getY()));
  }

  @Override
  public IVector orthogonal() {
    double ortX;
    double ortY;
    if (!MathHelper.equalsZero(x)) {
      ortY = 1;
      ortX = -(y * ortY) / x;
      return Primitives.unmodifiableVector(new Vector(ortX, ortY));
    }
    if (!MathHelper.equalsZero(y)) {
      ortX = 1;
      ortY = -(x * ortX) / y;
      return Primitives.unmodifiableVector(new Vector(ortX, ortY));
    }
    return Primitives.unmodifiableVector(new Vector(0, 0));
  }

  @Override
  public boolean isSemidirect(IVector vector) {
    return isCollinear(vector) && MathHelper.scalarProduct(this, vector) > 0;
  }

  @Override
  public boolean isCollinear(IVector otherVector) {
    if (this.isNull() || otherVector.isNull()) {
      return true;
    }
    return !this.toLine().isIntersection(otherVector.toLine());
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Vector) {
      Vector vector = (Vector) o;
      return MathHelper.equalsZero(x - vector.getX()) && MathHelper.equalsZero(y - vector.getY());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return (int) x + (int) y;
  }

  @Override
  public String toString() {
    return "[" + x + ", " + y + "]";
  }

  public ILine toLine() {
    return new Line(new Point(0, 0), this);
  }

  @Override
  public ILine toLine(IPoint point) {
    return new Line(point, this);
  }

  @Override
  public boolean isNull() {
    return MathHelper.equalsZero(x) && MathHelper.equalsZero(y);
  }

  @Override
  public void normalize() {
    double module = module();
    x /= module;
    y /= module;
  }

  @Override
  public void mult(double a) {
    x *= a;
    y *= a;
  }

  @Override
  public void add(IVector a) {
    x += a.getX();
    y += a.getY();
  }

  @Override
  public void reverse() {
    x = -x;
    y = -y;
  }

  @Override
  public void rotate(double angleDelta) {
    double startAngel = this.angle();
    double destinationAngle = startAngel + angleDelta;
    double module = module();
    x = Math.cos(destinationAngle) * module;
    y = Math.sin(destinationAngle) * module;
  }
}
