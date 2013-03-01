package com.javasensei.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public class Arc implements IArc{
    private double startAngle;
    private double endAngle;
    private double radius;
    private IPoint center;

    public Arc(double startAngle, double endAngle){
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.radius = 1;
        this.center = new Point(0, 0);
    }

    public Arc(double startAngle, double endAngle, double radius, IPoint center){
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.radius = radius;
        this.center = center.copy();
    }

    public Arc(IArc otherArc){
        startAngle = otherArc.getStartAngle();
        endAngle = otherArc.getEndAngle();
        radius = otherArc.getRadius();
        center = otherArc.getCenter().copy();
    }

    @Override
    public void normalize(){
        startAngle = MathHelper.normalize(startAngle);
        endAngle = MathHelper.normalize(endAngle);
    }

    public void shiftOver2PI(){
        startAngle += 2 * Math.PI;
        endAngle += 2 * Math.PI;
    }

    @Override
    public String toString(){
        return "[" + startAngle + ", " + endAngle + "]";
    }

    public double midAngle(){
        if(startAngle < endAngle){
            return (startAngle + endAngle) /2d;
        }
        return (startAngle + endAngle + 2 * Math.PI) /2d;
    }

    @Override
    public IPoint getStartPoint() {
        IVector startVector = new Vector(radius, 0);
        startVector.rotate(startAngle);
        IPoint startPoint = new Point(center);
        startPoint.translateInDirection(startVector);
        return Primitives.unmodifiablePoint(startPoint);
    }

    @Override
    public IPoint getEndPoint() {
        IVector endVector = new Vector(radius, 0);
        endVector.rotate(endAngle);
        IPoint endPoint = new Point(center);
        endPoint.translateInDirection(endVector);
        return Primitives.unmodifiablePoint(endPoint);
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!(other instanceof Arc)){
            return false;
        }
        if(this == other){
            return true;
        }
        Arc otherArc = (Arc)other;
        double normalizedThisStart = MathHelper.normalize(this.startAngle);
        double normalizedThisEnd = MathHelper.normalize(this.endAngle);
        double normalizedOtherStart = MathHelper.normalize(otherArc.startAngle);
        double normalizedOtherEnd = MathHelper.normalize(otherArc.endAngle);
        return MathHelper.equalsZero(normalizedThisStart - normalizedOtherStart) && MathHelper.equalsZero(normalizedThisEnd - normalizedOtherEnd);
    }

    @Override
    public int hashCode(){
        return (int) startAngle + (int) endAngle;
    }

    @Override
    public double getStartAngle() {
        return startAngle;
    }

    @Override
    public double getEndAngle() {
        return endAngle;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public IPoint getCenter() {
    return center;
    }


    @Override
    public boolean containsAngle(double angle) {
        if(endAngle < startAngle){
            return angle >= startAngle  && angle <= endAngle + 2 * Math.PI;
        }

        return angle >= startAngle  && angle <= endAngle;
    }

    @Override
    public boolean has(IPoint point) {
        return distanceTo(point) < MathConstants.ERROR;
    }

    @Override
    public double distanceTo(IPoint point) {
        IVector vector = new Vector(center, point);
        double angle = vector.angle();
        if (this.containsAngle(angle)){
            double distanceFromCenter = vector.module();
            if(distanceFromCenter < radius){
                return radius - distanceFromCenter;
            } else{
                return distanceFromCenter - radius;
            }
        }
        double distanceToStartPoint = getStartPoint().distanceTo(point);
        double distanceToEndPoint = getEndPoint().distanceTo(point);
        return distanceToStartPoint < distanceToEndPoint ? distanceToStartPoint : distanceToEndPoint;
    }

    @Override
    public void translateInDirection(IVector vector) {
        center.translateInDirection(vector);
    }

    @Override
    public void scale(double coef) {
        center.scale(coef);
        radius *= coef;
    }
}
