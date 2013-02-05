package com.javasensei.portfolio.particles.math;

/**
 * @author oleksiy sayankin
 */
public interface IRectangle extends IPolygon {
    IPoint bottomLeftPoint();

    IPoint bottomRightPoint();

    IPoint topLeftPoint();

    IPoint topRightPoint();
}
