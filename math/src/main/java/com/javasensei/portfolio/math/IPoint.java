package com.javasensei.portfolio.particles.math;

/**
 * @author oleksiy sayankin
 */
public interface IPoint extends IFigure {
    double getX();

    double getY();

    IPoint copy();
}
