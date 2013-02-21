package com.javasensei.portfolio.particles.math;

import com.javasensei.portfolio.particles.math.unmodifiable.*;

import java.util.List;

/**
 * @author oleksiy sayankin
 */
public final class MathHelper {
    private MathHelper() {
    }

    public static final IVector ORT_X = unmodifiableVector(1, 0);
    public static final IVector ORT_Y = unmodifiableVector(0, 1);

    public static double scalarProduct(final IVector a, final IVector b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }


    public static double module(final IVector a) {
        assert (scalarProduct(a, a) >= 0);
        return Math.sqrt(scalarProduct(a, a));
    }

    public static double module(final ISegment a) {
        double deltaX = a.getPoint2X() - a.getPoint1X();
        double deltaY = a.getPoint2Y() - a.getPoint1Y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
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

    public static double distanceBetween(final IPoint a, final IPoint b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    public static double distanceBetween(final IPoint point, final ILine line) {
        LineCoef coef = line.coef();
        double x = point.getX();
        double y = point.getY();
        return (coef.a * x + coef.b * y + coef.c) / (coef.a * coef.a + coef.b * coef.b);
    }

    public static IVector orthogonal(final IVector vector) {
        double x = vector.getX();
        double y = vector.getY();
        double ortX;
        double ortY;
        if (!equalsZero(x)) {
            ortY = 1;
            ortX = -(y * ortY) / x;
            return unmodifiableVector(new Vector(ortX, ortY));
        }
        if (!equalsZero(y)) {
            ortX = 1;
            ortY = -(x * ortX) / y;
            return unmodifiableVector(new Vector(ortX, ortY));
        }
        return unmodifiableVector(new Vector(0, 0));
    }

    public static ILine orthogonal(final ILine line, final IPoint point) {
        IVector vector = line.toVector();
        IVector orthogonalVector = orthogonal(vector);
        return unmodifiableLine(new Line(point, orthogonalVector));
    }

    public static IPoint reflectPointAgainstLine(final IPoint point, final ILine line) {
        ILine orthogonalLine = orthogonal(line, point);
        IPoint intersectionPoint = intersection(line, orthogonalLine);
        IVector direction = new Vector(point, intersectionPoint);
        IPoint resultPoint = new Point(intersectionPoint);
        resultPoint.translateInDirection(direction);
        return unmodifiablePoint(resultPoint);
    }


    public static IVector reflectVectorAgainstLine(final IVector a, final ILine line) {
        IPoint point = new Point(a.getX(), a.getY());
        ILine translatedLine = new Line(new Point(0, 0), line.toVector());
        IPoint reflectedPoint = reflectPointAgainstLine(point, translatedLine);
        return unmodifiableVector(new Vector(reflectedPoint.getX(), reflectedPoint.getY()));
    }

    public static double distanceBetween(final IPoint point, final ISegment segment) {
        IVector orthogonalVector = orthogonal(segment.toVector());
        Line orthogonalLine = new Line(point, orthogonalVector);
        IPoint intersectionPoint = intersection(orthogonalLine, segment.toLine());
        IPoint point1 = segment.getPoint1();
        IPoint point2 = segment.getPoint2();

        if (equalsZero(distanceBetween(point1, intersectionPoint) + distanceBetween(intersectionPoint, point2)
                - segment.module())) {
            return distanceBetween(point, intersectionPoint);
        }

        if (distanceBetween(point, point1) < distanceBetween(point, point2)) {
            return distanceBetween(point, point1);
        }

        return distanceBetween(point, point2);
    }

    public static double distanceBetween(final IRay ray, final IPoint point) {
        IVector rayVector = ray.toVector();
        IPoint rayStartPoint = ray.startPoint();
        if(rayVector.isNull()){
            return rayStartPoint.distanceTo(point);
        }
        if(rayStartPoint.equals(point)){
            return 0;
        }
        ILine rayLine = ray.toLine();
        if(rayLine.has(point)){
            if(new Vector(rayStartPoint, point).isSemidirect(rayVector)){
                return 0;
            }
            return rayStartPoint.distanceTo(point);
        }
        ILine orthogonalLine = MathHelper.orthogonal(rayLine, point);
        IPoint intersectionPoint = MathHelper.intersection(rayLine, orthogonalLine);
        if(new Vector(rayStartPoint, intersectionPoint).isSemidirect(rayVector)){
            return point.distanceTo(intersectionPoint);
        }
        return rayStartPoint.distanceTo(point);
    }

    public static double det(double a1, double a2, double b1, double b2) {
        return a1 * b2 - a2 * b1;
    }

    public static boolean isIntersection(final ILine firstLine, final ILine secondLine) {
        LineCoef coef1 = firstLine.coef();
        LineCoef coef2 = secondLine.coef();
        double a1 = coef1.a;
        double b1 = coef1.b;
        double a2 = coef2.a;
        double b2 = coef2.b;
        double det = det(a1, a2, b1, b2);
        return !equalsZero(det);
    }

    public static IPoint intersection(final ILine firstLine, final ILine secondLine) {
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
            double detX = -det(c1, c2, b1, b2);
            double detY = -det(a1, a2, c1, c2);
            double x = detX / det;
            double y = detY / det;
            result = new Point(x, y);
        }
        return unmodifiablePoint(result);
    }

    public static IPoint intersection(final ISegment a, final ISegment b) {
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

    public static IPoint intersection(final ILine lineA, final ISegment segment) {
        ILine lineB = segment.toLine();
        if (isIntersection(lineA, lineB)) {
            IPoint point = intersection(lineA, lineB);
            if (segment.has(point)) {
                return point;
            }
        }
        return null;
    }

    public static boolean equalsZero(double value) {
        return Math.abs(value) < MathConstants.ERROR;
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
        return unmodifiableSegment(result);
    }


    public static ISegment nearestSegmentInDirection(final List<ISegment> segments, final IPoint point, final IVector vector) {
        ISegment result = null;
        double minDistance = Double.MAX_VALUE;
        for (ISegment segment : segments) {
            IPoint intersectionPoint = intersection(vector.toLine(point), segment);
            if (intersectionPoint != null && vector.isSemidirect(new Vector(point, intersectionPoint))) {
                double newDistance = point.distanceTo(intersectionPoint);
                if (newDistance < minDistance) {
                    minDistance = newDistance;
                    result = segment;
                }
            }
        }

        return unmodifiableSegment(result);
    }

    public static IPoint unmodifiablePoint(IPoint point) {
        return new UnmodifiablePoint(point);
    }

    public static IPoint unmodifiablePoint(double x, double y) {
        return new UnmodifiablePoint(x, y);
    }

    public static ISegment unmodifiableSegment(final ISegment segment) {
        return new UnmodifiableSegment(segment);
    }

    public static IVector unmodifiableVector(final IVector vector) {
        return new UnmodifiableVector(vector);
    }

    public static IVector unmodifiableVector(final double x, final double y) {
        return new UnmodifiableVector(x, y);
    }

    public static IRectangle unmodifiableRectangle(final IRectangle rectangle) {
        return new UnmodifiableRectangle(rectangle);
    }

    public static IPolygon unmodifiablePolygon(final IPolygon polygon) {
        return new UnmodifiablePolygon(polygon);
    }

    public static ILine unmodifiableLine(final ILine line) {
        return new UnmodifiableLine(line);
    }

    public static ILine unmodifiableLine(final IPoint point1, final IPoint point2) {
        return new UnmodifiableLine(point1, point2);
    }

    public static boolean isCollinear(final IVector a, final IVector b) {
        if (a.isNull() || b.isNull()) {
            return true;
        }
        return !isIntersection(a.toLine(), b.toLine());
    }

    public static boolean isSemidirect(final IVector a, final IVector b) {
        return isCollinear(a, b) && scalarProduct(a, b) > 0;
    }

    public static IVector sum(final IVector a, final IVector b) {
        return unmodifiableVector(new Vector(a.getX() + b.getX(), a.getY() + b.getY()));
    }
}
