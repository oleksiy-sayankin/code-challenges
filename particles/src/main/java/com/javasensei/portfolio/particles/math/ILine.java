package com.javasensei.portfolio.particles.math;

/**
 * @author asayankin
 */
public interface ILine  extends IFigure {
    LineCoef coef();
    IVector toVector();
    IPoint point1();
    IPoint point2();
}
