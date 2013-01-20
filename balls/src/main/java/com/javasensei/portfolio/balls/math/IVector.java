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
    boolean isNull();
    boolean isSemidirect(IVector vector);
    Vector reflect(IVector a);
    double module();
    ILine toLine(IPoint point);
    ILine toLine();
}
