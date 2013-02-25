package com.javasensei.portfolio.math;

import static com.javasensei.portfolio.math.MathHelper.intersection;

/**
 * @author oleksiy sayankin
 */

public class Point implements IPoint {
    private double x;
    private double y;

    public Point(double aX, double aY) {
        x = aX;
        y = aY;
    }

    public Point(IPoint point) {
        x = point.getX();
        y = point.getY();
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double distanceTo(IPoint p) {
        return Math.sqrt((this.getX() - p.getX()) * (this.getX() - p.getX()) + (this.getY() - p.getY()) * (this.getY() - p.getY()));
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point) {
            Point point = (Point) o;
            return has(point);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int ix = (int) Math.round(x);
        int iy = (int) Math.round(y);
        return ix * ix + iy;
    }

    @Override
    public boolean has(IPoint point) {
        return distanceTo(point) < MathConstants.ERROR;
    }

    @Override
    public IPoint copy() {
        return new Point(x, y);
    }

    @Override
    public IPoint reflectAgainst(ILine line) {
        ILine orthogonalLine = line.orthogonal(this);
        IPoint intersectionPoint = intersection(line, orthogonalLine);
        IVector direction = new Vector(this, intersectionPoint);
        IPoint resultPoint = new Point(intersectionPoint);
        resultPoint.translateInDirection(direction);
        return Primitives.unmodifiablePoint(resultPoint);    }

    @Override
    public void translateInDirection(IVector vector) {
        x += vector.getX();
        y += vector.getY();
    }

    @Override
    public void scale(double coef) {
        x *= coef;
        y *= coef;
    }
}
