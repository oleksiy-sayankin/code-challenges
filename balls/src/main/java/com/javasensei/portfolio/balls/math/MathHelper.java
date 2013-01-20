package com.javasensei.portfolio.balls.math;

import com.javasensei.portfolio.balls.Constants;

import java.util.List;

/**
 * @author asayankin
 */
public final class MathHelper {
    private MathHelper(){}

    public static double scalarProduct(IVector a, IVector b){
        return a.getX()*b.getX() + a.getY()*b.getY();
    }


    public static double module(IVector a){
        return Math.sqrt(scalarProduct(a, a));
    }

    public static double module(ISegment a){
        double deltaX = a.getPoint2X() - a.getPoint1X();
        double deltaY = a.getPoint2Y() - a.getPoint1Y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static double cos(IVector a, IVector b){
        double moduleA = a.module();
        double moduleB = b.module();
        double cos = 0;
        if (!equalsZero(moduleA) && !equalsZero(moduleB)){
            cos = scalarProduct(a, b) / (moduleA * moduleB);
        }
        return cos;
    }

    public static double distanceBetween(IPoint a, IPoint b){
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    public static double distanceBetween(IPoint point, ILine line){
        LineCoef coef = line.coef();
        double x = point.getX();
        double y = point.getY();
        return (coef.a * x + coef.b * y + coef.c) / (coef.a * coef.a + coef.b * coef.b);
    }

    public static Vector orthogonal(IVector vector){
        double x = vector.getX();
        double y = vector.getY();
        double ortX;
        double ortY;
        if (!equalsZero(x)){
            ortY = 1;
            ortX = - (y * ortY) / x;
            return new Vector(ortX, ortY);
        }
        if (!equalsZero(y)){
            ortX = 1;
            ortY = - (x * ortX) / y;
            return new Vector(ortX, ortY);
        }
        return new Vector(0,0);
    }

    public static Vector reflect(IVector a, IVector b){
        double cosAlpha = MathHelper.cos(b, a);
        double alpha = Math.acos(cosAlpha);
        double newAlpha = -2d * alpha;
        double sinNewAlpha = Math.sin(newAlpha);
        double cosNewAlpha = Math.cos(newAlpha);
        double x1 = b.getX() * cosNewAlpha - b.getY() * sinNewAlpha;
        double y1 = b.getX() * sinNewAlpha + b.getY() * cosNewAlpha;
        return new Vector(x1, y1);
    }

    public static double distanceBetween(IPoint point, ISegment segment){
        IVector orthogonalVector = orthogonal(segment.toVector());
        Line  orthogonalLine = new Line(point, orthogonalVector);
        Point intersectionPoint = intersection(orthogonalLine, segment.toLine());
        IPoint point1 = segment.getPoint1();
        IPoint point2 = segment.getPoint2();

        if (equalsZero(distanceBetween(point1, intersectionPoint) + distanceBetween(intersectionPoint, point2)
                - segment.module())){
             return distanceBetween(point, intersectionPoint);
        }

        if(distanceBetween(point, point1) < distanceBetween(point, point2)){
             return distanceBetween(point, point1);
        }

        return distanceBetween(point, point2);
    }

    public static double det(double a1, double a2, double b1, double b2){
        return a1 * b2 - a2 * b1;
    }

    public static boolean isIntersection(ILine firstLine, ILine secondLine){
        LineCoef coef1 = firstLine.coef();
        LineCoef coef2 = secondLine.coef();
        double a1 = coef1.a;
        double b1 = coef1.b;
        double a2 = coef2.a;
        double b2 = coef2.b;
        double det = det(a1, a2, b1, b2);
        return !equalsZero(det);
    }

    public static Point intersection(ILine firstLine, ILine secondLine) {
        Point result = null;
        LineCoef coef1 = firstLine.coef();
        LineCoef coef2 = secondLine.coef();
        double a2 = coef1.a;
        double b2 = coef1.b;
        double c2 = coef1.c;
        double a1 = coef2.a;
        double b1 = coef2.b;
        double c1 = coef2.c;
        double det = det(a1, a2, b1, b2);

        if (!equalsZero(det)) {
            double detX = - det(c1, c2, b1, b2);
            double detY = - det(a1, a2, c1, c2);
            double x = detX / det;
            double y = detY / det;
            result = new Point(x, y);
        }
        return result;
    }

    public static IPoint intersection(ISegment a, ISegment b) {
        ILine lineA = a.toLine();
        ILine lineB = b.toLine();
        if (isIntersection(lineA, lineB)) {
        IPoint point = intersection(lineA, lineB);
            if (a.has(point) && b.has(point)) {
                return point;
            }
        }
        return null;
    }

    public static Point intersection(ILine lineA, ISegment segment) {
        ILine lineB = segment.toLine();
        if (isIntersection(lineA, lineB)) {
            Point point = intersection(lineA, lineB);
            if (segment.has(point)) {
                return point;
            }
        }
        return null;
    }

    public static boolean equalsZero(double value){
        return Math.abs(value) < Constants.Common.ERROR;
    }

    public static ISegment nearestSegmentInDirection(List<ISegment> segments, IPoint point, IVector vector){
        ISegment result  = null;
        double minDistance = Double.MAX_VALUE;
        for (ISegment segment : segments) {
            Point intersectionPoint = intersection(vector.toLine(point), segment);
            if (intersectionPoint != null && vector.isSemidirect(new Vector(point, intersectionPoint))) {
                double newDistance = point.distanceTo(intersectionPoint);
                if (newDistance < minDistance) {
                    minDistance = newDistance;
                    result = segment;
                }
            }
        }
        return result;
    }

    public static IPoint unmodifiablePoint(IPoint point){
        return new UnmodifiablePoint(point);
    }

    public static IPoint unmodifiablePoint(double x, double y){
        return new UnmodifiablePoint(x, y);
    }

    public static ISegment unmodifiableSegment(ISegment segment){
        return new UnmodifiableSegment(segment);
    }

    public static IVector unmodifiableVector(IVector vector){
        return new UnmodifiableVector(vector);
    }

    public static boolean isCollinear(IVector a, IVector b){
        if(a.isNull() || b.isNull()){
            return true;
        }
        return !isIntersection(a.toLine(), b.toLine());
    }

    public static boolean isSemidirect(IVector a, IVector b){
        return isCollinear(a, b) && scalarProduct(a, b) > 0;
    }

    public static IVector sum(IVector a, IVector b){
        return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
    }
}
