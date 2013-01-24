package com.javasensei.portfolio.balls.math;

/**
 * @author asayankin
 */
public final class UnmodifiablePolygon extends Polygon{
    public UnmodifiablePolygon(IPolygon polygon){
        setPoints(polygon.points());
    }
    @Override
    public void translateInDirection(IVector vector) {
        //nothing to do
    }

    @Override
    public void scale(double coef) {
        //nothing to do
    }

    @Override
    public void addPoint(IPoint point){
        //nothing to do
    }

    @Override
    public void stretchInDirection(IVector direction){
        //nothing to do
    }
}
