package com.javasensei.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public interface IArc extends IFigure{
    double startAngle();
    double endAngle();
    boolean containsAngle(double angle);
    double midAngle();
    IPoint getStartPoint();
    IPoint getEndPoint();
}
