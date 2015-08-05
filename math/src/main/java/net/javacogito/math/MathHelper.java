package net.javacogito.math;

import java.util.List;

/**
 * @author oleksiy sayankin
 */
public final class MathHelper {
    private MathHelper() {
    }

    public static final IVector ORT_X = Primitives.unmodifiableVector(1, 0);
    public static final IVector ORT_Y = Primitives.unmodifiableVector(0, 1);

    public static double scalarProduct(final IVector a, final IVector b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    public static double cos(final IVector a, final IVector b) {
        double moduleA = a.module();
        double moduleB = b.module();
        double cos = 0;
        if (!equalsZero(moduleA) && !equalsZero(moduleB)) {
            cos = scalarProduct(a, b) / (moduleA * moduleB);
        }
        assert (cos >= -1 && cos <= 1);
        return cos;
    }

    public static double signedArea(final IVector a, final IVector b) {
        return det(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public static double det(double a1, double a2, double b1, double b2) {
        return a1 * b2 - a2 * b1;
    }

    public static boolean equalsZero(double value) {
        return Math.abs(value) < Settings.error();
    }

    public static boolean equals(double a, double b){
        return Math.abs(a - b) < Settings.error();
    }

    public static ISegment nearestSegment(final List<ISegment> segments, final IPoint point) {
        ISegment result = null;
        double minDistance = Double.MAX_VALUE;
        for (ISegment segment : segments) {
            double newDistance = segment.distanceTo(point);
            if (newDistance < minDistance) {
                minDistance = newDistance;
                result = segment;
            }
        }
        return Primitives.unmodifiableSegment(result);
    }


    public static ISegment nearestSegmentInDirection(final List<ISegment> segments, final IPoint point, final IVector vector) {
        ISegment result = null;
        double minDistance = Double.MAX_VALUE;
        for (ISegment segment : segments) {
            IPoint intersectionPoint = vector.toLine(point).intersection(segment);
            if (intersectionPoint != null && vector.isSemidirect(new Vector(point, intersectionPoint))) {
                double newDistance = point.distanceTo(intersectionPoint);
                if (newDistance < minDistance) {
                    minDistance = newDistance;
                    result = segment;
                }
            }
        }

        return Primitives.unmodifiableSegment(result);
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

    public static IArc shadow(final ICircle circle, final IPoint point){
        double h = circle.getCenter().distanceTo(point);
        if (MathHelper.equalsZero(h) || h <= circle.getRadius()){
            return null;
        }
        double x = circle.getCenter().getX() - point.getX();
        double y = circle.getCenter().getY() - point.getY();
        double a = Math.abs(y);
        double sinAlpha = a / h;
        double sinDelta = circle.getRadius() / h;
        double alpha = 0;
        if(x > 0 && y >= 0 ){
            alpha = Math.asin(sinAlpha);
        }
        if(x <= 0 && y >= 0 ){
            alpha = Math.PI - Math.asin(sinAlpha);
        }
        if(x <= 0 && y < 0 ){
            alpha = Math.PI + Math.asin(sinAlpha);
        }
        if(x > 0 && y < 0 ){
            alpha = 2 * Math.PI - Math.asin(sinAlpha);
        }
        double delta = Math.asin(sinDelta);
        assert (delta >= 0);
        assert (alpha >= 0);
        IArc result  = new Arc(alpha - delta, alpha + delta);
        result.normalize();
        return result;
    }

    public static Circle minCircleContainingAll(final List<ICircle> circles, final IPoint point){
        double minRadius  = 0;
        for(ICircle circle : circles){
            double newRadius = circle.getCenter().distanceTo(point) + circle.getRadius();
            if(newRadius > minRadius){
                minRadius = newRadius;
            }
        }
        assert(minRadius >= 0);
        return new Circle(point, minRadius);
    }

}
