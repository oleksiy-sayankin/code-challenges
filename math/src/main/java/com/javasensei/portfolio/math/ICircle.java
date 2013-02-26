package com.javasensei.portfolio.math;

import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public interface ICircle extends I2DFigure{
    IPoint getCenter();
    double getRadius();
    Set<IPoint> intersection(ILine line);
}
