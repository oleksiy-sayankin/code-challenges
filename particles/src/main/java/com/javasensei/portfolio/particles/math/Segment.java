package com.javasensei.portfolio.particles.math;

import static com.javasensei.portfolio.particles.Constants.*;

public class Segment implements ISegment {
    private IPoint point1;
    private IPoint point2;

    public Segment(IPoint aPoint1, IPoint aPoint2) {
        point1 = aPoint1.copy();
        point2 = aPoint2.copy();
    }

    public Segment(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    @Override
    public String toString() {
        return "{" + point1 + ", " + point2 + "}";
    }


    @Override
    public boolean has(IPoint point) {
        return distanceTo(point) < Common.ERROR;
    }

    @Override
    public double distanceTo(IPoint point) {
        return MathHelper.distanceBetween(point, this);
    }

    @Override
    public void translateInDirection(IVector vector) {
        point1.translateInDirection(vector);
        point2.translateInDirection(vector);
    }

    @Override
    public void scale(double coef) {
        point1.scale(coef);
        point2.scale(coef);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Segment) {
            Segment segment = (Segment) o;
            boolean isStartEquals = Math.abs(point1.getX() - segment.getPoint1X()) < Common.ERROR && Math.abs(point1.getY() - segment.getPoint1Y()) < Common.ERROR;
            boolean isEndEquals = Math.abs(point2.getX() - segment.getPoint2X()) < Common.ERROR && Math.abs(point2.getY() - segment.getPoint2Y()) < Common.ERROR;
            return isStartEquals && isEndEquals;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) point1.getX() + (int) point1.getY() + (int) point2.getX() + (int) point2.getY();
    }

    @Override
    public IPoint getPoint1() {
        return MathHelper.unmodifiablePoint(point1);
    }

    @Override
    public IPoint getPoint2() {
        return MathHelper.unmodifiablePoint(point2);
    }

    @Override
    public double getPoint1X() {
        return point1.getX();
    }

    @Override
    public double getPoint1Y() {
        return point1.getY();
    }

    @Override
    public double getPoint2X() {
        return point2.getX();
    }

    @Override
    public double getPoint2Y() {
        return point2.getY();
    }

    @Override
    public ILine toLine() {
        return MathHelper.unmodifiableLine(point1, point2);
    }

    public IVector toVector() {
        return MathHelper.unmodifiableVector(point2.getX() - point1.getX(), point2.getY() - point1.getY());
    }

    @Override
    public double module() {
        return MathHelper.module(this);
    }
}
