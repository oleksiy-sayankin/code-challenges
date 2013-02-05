package com.javasensei.portfolio.particles.math;

/**
 * @author oleksiy sayankin
 */
public interface IFigure {
    boolean has(IPoint point);

    double distanceTo(IPoint point);

    void translateInDirection(IVector vector);

    void scale(double coef);
}
