package com.javasensei.portfolio.balls.math;

/**
 * @author ashvayka
 */
public interface IEuclid {
    boolean has(IPoint point);
    double distanceTo(IPoint point);
    void translateInDirection(IVector vector);
}
