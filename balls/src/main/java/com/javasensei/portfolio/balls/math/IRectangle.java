package com.javasensei.portfolio.balls.math;

/**
 * @author asayankin
 */
public interface IRectangle extends IPolygon{
    double area();
    IPoint bottomLeftPoint();
    IPoint bottomRightPoint();
    IPoint topLeftPoint();
    IPoint topRightPoint();
}
