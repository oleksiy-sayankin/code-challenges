package com.javasensei.portfolio.math;

import static com.javasensei.portfolio.math.MathHelper.scalarProduct;
import static com.javasensei.portfolio.math.MathHelper.isIntersection;

/**
 * @author oleksiy sayankin
 */

public class Vector implements IVector {
    private double x;
    private double y;

    public Vector(IPoint point1, IPoint point2) {
        x = point2.getX() - point1.getX();
        y = point2.getY() - point1.getY();
    }

    public Vector(double aX, double aY) {
        x = aX;
        y = aY;
    }

    public Vector(IPoint point) {
        x = point.getX();
        y = point.getY();
    }

    public Vector(IVector vector) {
        x = vector.getX();
        y = vector.getY();
    }

    public void setXY(double aX, double aY) {
        x = aX;
        y = aY;
    }

    public IVector copy(){
        return new Vector(x, y);
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
    public double module() {
        assert (scalarProduct(this, this) >= 0);
        return Math.sqrt(scalarProduct(this, this));
    }

    @Override
    public IVector reflectAgainst(ILine line) {
        IPoint point = new Point(x, y);
        ILine translatedLine = new Line(new Point(0, 0), line.toVector());
        IPoint reflectedPoint = point.reflectAgainst(translatedLine);
        return Primitives.unmodifiableVector(new Vector(reflectedPoint.getX(), reflectedPoint.getY()));
    }

    @Override
    public boolean isSemidirect(IVector vector) {
        return isCollinear(vector) && scalarProduct(this, vector) > 0;
    }

    @Override
    public  boolean isCollinear(IVector otherVector) {
        if (this.isNull() || otherVector.isNull()) {
            return true;
        }
        return !isIntersection(this.toLine(), otherVector.toLine());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector) {
            Vector vector = (Vector) o;
            return MathHelper.equalsZero(x - vector.getX()) && MathHelper.equalsZero(y - vector.getY());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) x + (int) y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public ILine toLine() {
        return new Line(new Point(0, 0), this);
    }

    @Override
    public ILine toLine(IPoint point) {
        return new Line(point, this);
    }

    @Override
    public boolean isNull() {
        return MathHelper.equalsZero(x) && MathHelper.equalsZero(y);
    }

    @Override
    public void normalize() {
        double module = module();
        x /= module;
        y /= module;
    }

    @Override
    public void mult(double a) {
        x *= a;
        y *= a;
    }

    @Override
    public void add(IVector a) {
        x += a.getX();
        y += a.getY();
    }

    @Override
    public void reverse() {
        x = -x;
        y = -y;
    }
}
