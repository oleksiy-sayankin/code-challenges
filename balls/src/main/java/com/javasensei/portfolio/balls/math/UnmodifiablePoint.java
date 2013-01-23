package com.javasensei.portfolio.balls.math;

/**
 * @author asayankin
 */
public final class UnmodifiablePoint extends Point{
    public UnmodifiablePoint(double aX, double aY) {
        super(aX, aY);
    }

    public UnmodifiablePoint(IPoint point) {
        super(point.getX(), point.getY());
    }


    @Override
    public void setXY(double aX, double aY){
    //do nothing
    }
}
