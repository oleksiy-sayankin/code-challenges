package net.javacogito.math;

/**
 * @author oleksiy sayankin
 */
public interface IPoint extends IFigure {
  double getX();

  double getY();

  IPoint copy();

  IPoint reflectAgainst(ILine line);
}
