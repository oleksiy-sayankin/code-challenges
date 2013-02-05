package com.javasensei.portfolio.particles.math.unmodifiable;

import com.javasensei.portfolio.particles.math.*;

/**
 * @author asayankin
 */
public final class UnmodifiableLine extends Line {
    public UnmodifiableLine(IPoint aPoint1, IPoint aPoint2) {
        super(MathHelper.unmodifiablePoint(aPoint1), MathHelper.unmodifiablePoint(aPoint2));
    }

    public UnmodifiableLine(IPoint point, IVector vector) {
        super(MathHelper.unmodifiablePoint(point), MathHelper.unmodifiablePoint(new Point(point.getX() + vector.getX(), point.getY() + vector.getY())));
    }

    public UnmodifiableLine(ILine line){
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
