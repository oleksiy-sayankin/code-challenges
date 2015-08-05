package net.javacogito.math.unmodifiable;

import net.javacogito.math.IPoint;
import net.javacogito.math.IPolygon;
import net.javacogito.math.IVector;
import net.javacogito.math.Polygon;

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
