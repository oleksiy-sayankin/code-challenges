package net.javacogito.math;

import java.util.List;

/**
 * @author oleksiy sayankin
 */
public interface IPolygon extends I2DFigure {
  List<ISegment> toSegmentsClockwise();

  void addPoint(IPoint point);

  void stretch(IPoint stretchPoint, IVector direction);

  List<IPoint> points();

  IRectangle bounds();

  int pointsCount();
}
