package com.javacogito.portfolio.math.unmodifiable;

import com.javacogito.portfolio.math.IPoint;
import com.javacogito.portfolio.math.IVector;
import com.javacogito.portfolio.math.Point;

/**
 * @author oleksiy sayankin
 */
public final class UnmodifiablePoint extends Point {
    public UnmodifiablePoint(double aX, double aY) {
        super(aX, aY);
    }

    public UnmodifiablePoint(IPoint point) {
        super(point.getX(), point.getY());
    }

    @Override
    public void translateInDirection(IVector vector) {
        // nothing to do
    }

    @Override
    public void scale(double coef) {
        // nothing to do
    }
}
