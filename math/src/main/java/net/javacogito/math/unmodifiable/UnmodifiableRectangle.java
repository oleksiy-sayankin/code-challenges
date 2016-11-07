package net.javacogito.math.unmodifiable;

import net.javacogito.math.*;

/**
 * @author oleksiy sayankin
 */
public final class UnmodifiableRectangle extends Rectangle {
  public UnmodifiableRectangle(IPoint point1, IPoint point2) {
    super(Primitives.unmodifiablePoint(point1), Primitives.unmodifiablePoint(point2));
  }

  public UnmodifiableRectangle(IRectangle rectangle) {
    super(rectangle.points().get(0), rectangle.points().get(2));

  }

  public UnmodifiableRectangle(double leftBound, double rightBound, double upBound, double downBound) {
    super(Primitives.unmodifiablePoint(new Point(leftBound, downBound)), Primitives.unmodifiablePoint(new Point(rightBound, upBound)));
  }

  @Override
  public void translateInDirection(IVector vector) {
    //nothing to do
  }

  @Override
  public void scale(double coef) {
    //nothing to do
  }

  @Override
  public void addPoint(IPoint point) {
    //nothing to do
  }

  @Override
  public void stretch(IPoint stretchPoint, IVector direction) {
    //nothing to do
  }
}
