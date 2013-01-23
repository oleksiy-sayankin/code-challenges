package com.javasensei.portfolio.balls.math;

/**
 * @author asayankin
 */
public interface ILine  extends IFigure {
    LineCoef coef();
    IVector toVector();
}
