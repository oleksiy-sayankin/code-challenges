package net.javacogito.math;


/**
 * @author oleksiy sayankin
 */

public class Line implements ILine {
  private IPoint point1;
  private IPoint point2;

  public Line(IPoint aPoint1, IPoint aPoint2) {
    point1 = aPoint1;
    point2 = aPoint2;
  }

  public Line(IPoint point, IVector vector) {
    point1 = point;
    point2 = new Point(point.getX() + vector.getX(), point.getY() + vector.getY());
  }

  @Override
  public double distanceTo(IPoint point) {
    LineCoef coef = coef();
    double x = point.getX();
    double y = point.getY();
    return (coef.a * x + coef.b * y + coef.c) / (coef.a * coef.a + coef.b * coef.b);
  }

  @Override
  public void translateInDirection(IVector vector) {
    point1.translateInDirection(vector);
    point2.translateInDirection(vector);
  }

  @Override
  public boolean has(IPoint point) {
    return distanceTo(point) < MathConstants.ERROR;
  }

  @Override
  public void scale(double coef) {
    point1.scale(coef);
    point2.scale(coef);
  }

  @Override
  public LineCoef coef() {
    double a = point1.getY() - point2.getY();
    double b = point2.getX() - point1.getX();
    double c = point1.getX() * point2.getY() - point2.getX() * point1.getY();
    return new LineCoef(a, b, c);
  }


  public IVector toVector() {
    return Primitives.unmodifiableVector(point2.getX() - point1.getX(), point2.getY() - point1.getY());
  }

  @Override
  public IPoint point1() {
    return Primitives.unmodifiablePoint(point1);
  }

  @Override
  public IPoint point2() {
    return Primitives.unmodifiablePoint(point2);
  }

  @Override
  public ILine orthogonal(IPoint point) {
    IVector orthogonalVector = this.toVector().orthogonal();
    return Primitives.unmodifiableLine(new Line(point, orthogonalVector));
  }

  @Override
  public boolean isIntersection(ILine secondLine) {
    LineCoef coef1 = this.coef();
    LineCoef coef2 = secondLine.coef();
    double a1 = coef1.a;
    double b1 = coef1.b;
    double a2 = coef2.a;
    double b2 = coef2.b;
    double det = MathHelper.det(a1, a2, b1, b2);
    return !MathHelper.equalsZero(det);
  }

  @Override
  public IPoint intersection(ILine secondLine) {
    Point result = null;
    LineCoef coef1 = this.coef();
    LineCoef coef2 = secondLine.coef();
    double a2 = coef1.a;
    double b2 = coef1.b;
    double c2 = coef1.c;
    double a1 = coef2.a;
    double b1 = coef2.b;
    double c1 = coef2.c;
    double det = MathHelper.det(a1, a2, b1, b2);

    if (!MathHelper.equalsZero(det)) {
      double detX = -MathHelper.det(c1, c2, b1, b2);
      double detY = -MathHelper.det(a1, a2, c1, c2);
      double x = detX / det;
      double y = detY / det;
      result = new Point(x, y);
    }
    return Primitives.unmodifiablePoint(result);
  }

  @Override
  public IPoint intersection(ISegment segment) {
    ILine lineB = segment.toLine();
    if (this.isIntersection(lineB)) {
      IPoint point = this.intersection(lineB);
      if (segment.has(point)) {
        return point;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "{" + point1 + ", " + point2 + "}";
  }
}
