package com.javasensei.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public interface IPoint extends IFigure {
    double getX();

    double getY();

    IPoint copy();
}
