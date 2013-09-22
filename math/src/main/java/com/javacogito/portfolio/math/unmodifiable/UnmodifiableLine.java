package com.javacogito.portfolio.math.unmodifiable;

import com.javacogito.portfolio.math.*;

/**
 * @author oleksiy sayankin
 */
public final class UnmodifiableLine extends Line {
    public UnmodifiableLine(IPoint aPoint1, IPoint aPoint2) {
        super(Primitives.unmodifiablePoint(aPoint1), Primitives.unmodifiablePoint(aPoint2));
    }

    public UnmodifiableLine(IPoint point, IVector vector) {
        super(Primitives.unmodifiablePoint(point), Primitives.unmodifiablePoint(new Point(point.getX() + vector.getX(), point.getY() + vector.getY())));
    }

    public UnmodifiableLine(ILine line) {
        super(line.point1(), line.point2());
    }

    @Override
    public void translateInDirection(IVector vector) {
        //nothing to do
    }

    @Override
    public void scale(double coef) {
        //nothing to do
    }
}
