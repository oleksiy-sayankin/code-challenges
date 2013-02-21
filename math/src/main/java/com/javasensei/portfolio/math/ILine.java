package com.javasensei.portfolio.particles.math;

/**
 * @author oleksiy sayankin
 */
public interface ILine extends IFigure {
    LineCoef coef();

    IVector toVector();

    IPoint point1();

    IPoint point2();
}
