package com.javasensei.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public class Circle implements ICircle{
    private IPoint center;
    private double radius;

    public Circle(IPoint center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public double getX() {
        return center.getX();
    }

    public double getY() {
        return center.getY();
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public IPoint getCenter(){
        return center;
    }

    @Override
    public String toString(){
        return "{" + center + ", radius = " + radius + "}";
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!(other instanceof  Circle)){
            return false;
        }
        if(other == this){
            return true;
        }
        Circle otherCircle = (Circle)other;
        return this.center.equals(otherCircle.center) && MathHelper.equalsZero(this.radius - otherCircle.radius);
    }

    @Override
    public double width() {
        return 2 * radius;
    }

    @Override
    public double height() {
        return 2 * radius;
    }

    @Override
    public MathDimension dimension() {
        return new MathDimension(width(), height());
    }

    @Override
    public double downBound() {
        return center.getY() - radius;
    }

    @Override
    public double upBound() {
        return center.getY() + radius;
    }

    @Override
    public double rightBound() {
        return center.getX() + radius;
    }

    @Override
    public double leftBound() {
        return center.getX() - radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.PI * radius;
    }

    @Override
    public boolean contains(IPoint point) {
        return center.distanceTo(point) < radius;
    }

    @Override
    public boolean has(IPoint point) {
        return MathHelper.equalsZero(center.distanceTo(point) - radius);
    }

    @Override
    public double distanceTo(IPoint point) {
        return point.distanceTo(center) - radius;
    }

    @Override
    public void translateInDirection(IVector vector) {
        center.translateInDirection(vector);
    }

    @Override
    public void scale(double coef) {
        radius *= coef;
    }
}
