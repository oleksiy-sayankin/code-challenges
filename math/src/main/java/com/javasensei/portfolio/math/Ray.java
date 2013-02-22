package com.javasensei.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public class Ray implements IRay{
    private IPoint point;
    private IVector vector;

    public Ray(IPoint point, IVector vector){
        this.point = point.copy();
        this.vector = vector.copy();
    }

    @Override
    public IVector toVector() {
        return MathHelper.unmodifiableVector(vector);
    }

    @Override
    public IPoint startPoint() {
        return MathHelper.unmodifiablePoint(point);
    }

    @Override
    public ILine toLine() {
        return MathHelper.unmodifiableLine(new Line(point, vector));
    }

    @Override
    public boolean has(IPoint otherPoint) {
        return distanceTo(otherPoint) < MathConstants.ERROR;
    }

    @Override
    public double distanceTo(IPoint otherPoint) {
        return MathHelper.distanceBetween(this, otherPoint);
    }

    @Override
    public void translateInDirection(IVector vector) {
        point.translateInDirection(vector);
    }

    @Override
    public void scale(double coef) {
        point.scale(coef);
    }
}
