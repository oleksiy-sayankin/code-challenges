package com.javasensei.portfolio.balls.math;

/**
 * @author asayankin
 */
public interface IVector {
    double getX();
    double getY();
    void mult(double a);
    void add(IVector a);
    void normalize();
    void reverse();
    boolean isNull();
    boolean isSemidirect(IVector vector);
    IVector reflectAgainst(ILine a);
    double module();
    ILine toLine(IPoint point);
    ILine toLine();
}
