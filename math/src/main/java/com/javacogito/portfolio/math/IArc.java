package com.javacogito.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public interface IArc extends IFigure{
    double getStartAngle();
    double getEndAngle();
    double getRadius();
    IPoint getCenter();
    boolean containsAngle(double angle);
    double midAngle();
    IPoint getStartPoint();
    IPoint getEndPoint();
    void normalize();
}
