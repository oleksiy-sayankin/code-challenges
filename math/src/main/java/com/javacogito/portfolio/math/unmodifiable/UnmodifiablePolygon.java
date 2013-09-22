package com.javacogito.portfolio.math.unmodifiable;

import com.javacogito.portfolio.math.IPoint;
import com.javacogito.portfolio.math.IPolygon;
import com.javacogito.portfolio.math.IVector;
import com.javacogito.portfolio.math.Polygon;

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
