package com.javasensei.portfolio.particles.math;

/**
 * @author asayankin
 */
public interface IRectangle extends IPolygon{
    IPoint bottomLeftPoint();
    IPoint bottomRightPoint();
    IPoint topLeftPoint();
    IPoint topRightPoint();
}
