package com.javasensei.portfolio.math;

import static com.javasensei.portfolio.math.MathHelper.intersection;

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
        return Primitives.unmodifiableVector(vector);
    }

    @Override
    public IPoint startPoint() {
        return Primitives.unmodifiablePoint(point);
    }

    @Override
    public ILine toLine() {
        return Primitives.unmodifiableLine(new Line(point, vector));
    }

    @Override
    public boolean has(IPoint otherPoint) {
        return distanceTo(otherPoint) < MathConstants.ERROR;
    }

    @Override
    public double distanceTo(IPoint otherPoint) {
        IVector rayVector = vector;
        IPoint rayStartPoint = point;
        if(rayVector.isNull()){
            return rayStartPoint.distanceTo(otherPoint);
        }
        if(rayStartPoint.equals(otherPoint)){
            return 0;
        }
        ILine rayLine = this.toLine();
        if(rayLine.has(otherPoint)){
            if(new Vector(rayStartPoint, otherPoint).isSemidirect(rayVector)){
                return 0;
            }
            return rayStartPoint.distanceTo(otherPoint);
        }
        ILine orthogonalLine = rayLine.orthogonal(otherPoint);
        IPoint intersectionPoint = intersection(rayLine, orthogonalLine);
        if(new Vector(rayStartPoint, intersectionPoint).isSemidirect(rayVector)){
            return otherPoint.distanceTo(intersectionPoint);
        }
        return rayStartPoint.distanceTo(otherPoint);
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
