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
        double a = Math.abs(x);
        double sinAlpha = a / h;
        double sinDelta = circle.getR() / h;
        double quoter = 0;
        if(x < 0 && y > 0 ){
            quoter = Math.PI / 2;
        }
        if(x < 0 && y < 0 ){
            quoter = Math.PI;
        }
        if(x > 0 && y < 0 ){
            quoter = 3 * Math.PI / 2;
        }
        double alpha = Math.asin(sinAlpha) + quoter;
        double delta = Math.asin(sinDelta);
        assert (delta >= 0);
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
