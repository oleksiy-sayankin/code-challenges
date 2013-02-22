package com.javasensei.portfolio.math.unmodifiable;

import com.javasensei.portfolio.math.IPoint;
import com.javasensei.portfolio.math.IVector;
import com.javasensei.portfolio.math.Point;

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
