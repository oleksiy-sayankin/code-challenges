package com.javasensei.portfolio.balls.math;

/**
 * @author asayankin
 */
public interface IPoint extends IEuclid{
    double getX();
    double getY();
    IPoint copy();
}
