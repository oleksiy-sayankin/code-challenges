package com.javasensei.portfolio.particles.math.unmodifiable;

import com.javasensei.portfolio.particles.math.*;

/**
 * @author asayankin
 */
public final class UnmodifiableRectangle extends Rectangle {
    public UnmodifiableRectangle(IPoint point1, IPoint point2) {
        super(MathHelper.unmodifiablePoint(point1), MathHelper.unmodifiablePoint(point2));
    }

    public UnmodifiableRectangle(IRectangle rectangle) {
        super(rectangle.points().get(0), rectangle.points().get(2));

    }

    public UnmodifiableRectangle(double leftBound, double rightBound, double upBound, double downBound){
        super(MathHelper.unmodifiablePoint(new Point(leftBound, downBound)), MathHelper.unmodifiablePoint(new Point(rightBound, upBound)));
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
    public void stretch(IPoint stretchPoint, IVector direction){
        //nothing to do
    }
}
