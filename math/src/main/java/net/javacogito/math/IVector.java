package net.javacogito.math;

/**
 * @author oleksiy sayankin
 */
public interface IVector {
  double getX();

  double getY();

  void mult(double a);

  void add(IVector a);

  void normalize();

  void reverse();

  void rotate(double angleDelta);

  boolean isNull();

  boolean isSemidirect(IVector vector);

  boolean isCollinear(IVector vector);

  IVector reflectAgainst(ILine a);

  IVector orthogonal();

  double module();

  double angle();

  ILine toLine(IPoint point);

  ILine toLine();

  IVector copy();
}
