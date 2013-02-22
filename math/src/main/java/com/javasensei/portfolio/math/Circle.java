package com.javasensei.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public class Circle {
    private IPoint center;
    private double r;

    public Circle(IPoint center, double r){
        this.center = center;
        this.r = r;
    }

    public double getX() {
        return center.getX();
    }

    public double getY() {
        return center.getY();
    }

    public double getR() {
        return r;
    }


    public IPoint center(){
        return center;
    }

    @Override
    public String toString(){
        return "{" + center + ", r = " + r + "}";
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
        return this.center.equals(otherCircle.center) && MathHelper.equalsZero(this.r - otherCircle.r);
    }
}
