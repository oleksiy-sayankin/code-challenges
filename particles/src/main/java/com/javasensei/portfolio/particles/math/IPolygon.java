package com.javasensei.portfolio.particles.math;

import java.util.List;

/**
 * @author oleksiy sayankin
 */
public interface IPolygon extends IFigure {
    List<ISegment> toSegmentsClockwise();

    void addPoint(IPoint point);

    void stretch(IPoint stretchPoint, IVector direction);

    List<IPoint> points();

    double width();

    double height();

    MathDimension dimension();

    double downBound();

    double upBound();

    double rightBound();

    double leftBound();

    IRectangle bounds();

    int pointsCount();

    double area();
}
