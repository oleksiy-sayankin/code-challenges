package com.javasensei.portfolio.particles.math.unmodifiable;

import com.javasensei.portfolio.particles.math.IPoint;
import com.javasensei.portfolio.particles.math.IPolygon;
import com.javasensei.portfolio.particles.math.IVector;
import com.javasensei.portfolio.particles.math.Polygon;

/**
 * @author oleksiy sayankin
 */
public final class UnmodifiablePolygon extends Polygon {
    public UnmodifiablePolygon(IPolygon polygon) {
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
    public void addPoint(IPoint point) {
        //nothing to do
    }

    @Override
    public void stretch(IPoint stretchPoint, IVector direction) {
        //nothing to do
    }
}
