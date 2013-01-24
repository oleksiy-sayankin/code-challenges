package com.javasensei.portfolio.balls.math;

import java.util.List;

/**
 * @author asayankin
 */
public interface IPolygon extends IFigure {
    List<ISegment> toSegmentsClockwise();
    void addPoint(IPoint point);
    void stretchInDirection(IVector direction);
    List<IPoint> points();
    double width();
    double height();
    IRectangle bounds();
}
