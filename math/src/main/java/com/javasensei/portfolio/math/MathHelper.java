package com.javasensei.portfolio.math;

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

    public static IVector orthogonal(final IVector vector) {
        double x = vector.getX();
        double y = vector.getY();
        double ortX;
        double ortY;
        if (!equalsZero(x)) {
            ortY = 1;
            ortX = -(y * ortY) / x;
            return Primitives.unmodifiableVector(new Vector(ortX, ortY));
        }
        if (!equalsZero(y)) {
            ortX = 1;
            ortY = -(x * ortX) / y;
            return Primitives.unmodifiableVector(new Vector(ortX, ortY));
        }
        return Primitives.unmodifiableVector(new Vector(0, 0));
    }

    public static ILine orthogonal(final ILine line, final IPoint point) {
        IVector vector = line.toVector();
        IVector orthogonalVector = orthogonal(vector);
        return Primitives.unmodifiableLine(new Line(point, orthogonalVector));
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
        return Primitives.unmodifiablePoint(result);
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
        return Primitives.unmodifiableSegment(result);
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

        return Primitives.unmodifiableSegment(result);
    }
}
