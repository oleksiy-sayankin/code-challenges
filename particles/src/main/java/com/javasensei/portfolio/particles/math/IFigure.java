package com.javasensei.portfolio.particles.math;

/**
 * @author asayankin
 */
public interface IFigure {
    boolean has(IPoint point);
    double distanceTo(IPoint point);
    void translateInDirection(IVector vector);
    void scale(double coef);
}
