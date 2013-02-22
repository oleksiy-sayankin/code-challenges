package com.javasensei.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public interface ISegment extends IFigure {
    double module();

    IPoint getPoint1();

    IPoint getPoint2();

    double getPoint1X();

    double getPoint1Y();

    double getPoint2X();

    double getPoint2Y();

    ILine toLine();

    IVector toVector();
}
