package com.javasensei.portfolio.math;

import static com.javasensei.portfolio.math.MathHelper.orthogonal;
import static com.javasensei.portfolio.math.MathHelper.equalsZero;
import static com.javasensei.portfolio.math.MathHelper.intersection;

/**
 * @author oleksiy sayankin
 */

public class Segment implements ISegment {
    private IPoint startPoint;
    private IPoint endPoint;

    public Segment(IPoint aPoint1, IPoint aPoint2) {
        startPoint = aPoint1.copy();
        endPoint = aPoint2.copy();
    }

    public Segment(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    @Override
    public String toString() {
        return "{" + startPoint + ", " + endPoint + "}";
    }


    @Override
    public boolean has(IPoint point) {
        return distanceTo(point) < MathConstants.ERROR;
    }

    @Override
    public double distanceTo(IPoint point) {

        if(startPoint.has(point) || endPoint.has(point)){
            return 0;
        }

        IVector orthogonalVector = orthogonal(this.toVector());
        Line orthogonalLine = new Line(point, orthogonalVector);
        IPoint intersectionPoint = intersection(orthogonalLine, this.toLine());



        if (equalsZero(startPoint.distanceTo(intersectionPoint) + endPoint.distanceTo(intersectionPoint)
                - this.module())) {
            return point.distanceTo(intersectionPoint);
        }

        if (startPoint.distanceTo(point) < endPoint.distanceTo(point)) {
            return startPoint.distanceTo(point);
        }

        return endPoint.distanceTo(point);
    }

    @Override
    public void translateInDirection(IVector vector) {
        startPoint.translateInDirection(vector);
        endPoint.translateInDirection(vector);
    }

    @Override
    public void scale(double coef) {
        startPoint.scale(coef);
        endPoint.scale(coef);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Segment) {
            Segment segment = (Segment) o;
            boolean isStartEquals = startPoint.equals(segment.startPoint);
            boolean isEndEquals = endPoint.equals(segment.endPoint);
            return isStartEquals && isEndEquals;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) startPoint.getX() + (int) startPoint.getY() + (int) endPoint.getX() + (int) endPoint.getY();
    }

    @Override
    public IPoint getStartPoint() {
        return Primitives.unmodifiablePoint(startPoint);
    }

    @Override
    public IPoint getEndPoint() {
        return Primitives.unmodifiablePoint(endPoint);
    }

    @Override
    public double getPoint1X() {
        return startPoint.getX();
    }

    @Override
    public double getPoint1Y() {
        return startPoint.getY();
    }

    @Override
    public double getPoint2X() {
        return endPoint.getX();
    }

    @Override
    public double getPoint2Y() {
        return endPoint.getY();
    }

    @Override
    public ILine toLine() {
        return Primitives.unmodifiableLine(startPoint, endPoint);
    }

    public IVector toVector() {
        return Primitives.unmodifiableVector(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
    }

    @Override
    public double module() {
        double deltaX = this.getPoint2X() - this.getPoint1X();
        double deltaY = this.getPoint2Y() - this.getPoint1Y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
