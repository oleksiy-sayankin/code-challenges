package com.javasensei.portfolio.particles.math;

/**
 * @author asayankin
 */
public interface IPoint extends IFigure {
    double getX();
    double getY();
    IPoint copy();
}
