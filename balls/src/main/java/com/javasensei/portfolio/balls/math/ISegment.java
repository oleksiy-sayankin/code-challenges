package com.javasensei.portfolio.balls.math;

/**
 * @author asayankin
 */
public interface ISegment extends IEuclid{
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
