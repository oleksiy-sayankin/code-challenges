package com.javasensei.portfolio.deepforest;

import java.util.List;
/**
 * @author oleksiy sayankin
 */
public final class MathHelper {
    private MathHelper(){}
    public static boolean equalsZero(double value) {
        return Math.abs(value) < Constants.ERROR;
    }

    public static double distanceBetween(final Coord a, final Coord b) {
        return Math.sqrt((a.x() - b.x()) * (a.x() - b.x()) + (a.y() - b.y()) * (a.y() - b.y()));
    }

    public static Circle minCircleContainingAll(final List<Circle> circles, final Coord center){
        double minRadius  = 0;
        for(Circle circle : circles){
            double newRadius = distanceBetween(circle.center(), center) + circle.getR();
            if(newRadius > minRadius){
                minRadius = newRadius;
            }
        }
        assert(minRadius >= 0);
        return new Circle(center, minRadius);
    }

    public static CircleSegment shadow(final Circle circle, final Coord coord){
        double h = distanceBetween(circle.center(), coord);
        if (MathHelper.equalsZero(h)){
            return null;
        }
        double x = circle.center().x() - coord.x();
        double y = circle.center().y() - coord.y();
        double a = Math.abs(y);
        double sinAlpha = a / h;
        double sinDelta = circle.getR() / h;
        double alpha = 0;
        if(x > 0 && y > 0 ){
            alpha = Math.asin(sinAlpha);
        }
        if(x < 0 && y > 0 ){
            alpha = Math.PI - Math.asin(sinAlpha);
        }
        if(x < 0 && y < 0 ){
            alpha = Math.PI + Math.asin(sinAlpha);
        }
        if(x > 0 && y < 0 ){
            alpha = 2 * Math.PI - Math.asin(sinAlpha);
        }
        double delta = Math.asin(sinDelta);
        assert (delta >= 0);
        assert (alpha >= 0);
        CircleSegment result  = new CircleSegment(alpha - delta, alpha + delta);
        result.normalize();
        return result;
    }

    public static double normalize(double angle){
        double result = angle;
        while (result < 0){
            result += 2 * Math.PI;
        }
        while (result > 2 * Math.PI){
            result -= 2 * Math.PI;
        }
        return result;
    }
}
