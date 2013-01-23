package com.javasensei.portfolio.balls.math;

/**
 * @author asayankin
 */
public interface IPoint extends IFigure {
    double getX();
    double getY();
    IPoint copy();
}
