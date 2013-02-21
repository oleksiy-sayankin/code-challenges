package com.javasensei.portfolio.particles.math;

/**
 * @author ashvayka
 */
public interface I2DFigure extends IFigure {
    double width();
    double height();
    MathDimension dimension();
    double downBound();
    double upBound();
    double rightBound();
    double leftBound();
    double area();
    boolean contains(IPoint point);
}
