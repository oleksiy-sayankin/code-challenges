package net.javacogito.math;

import net.javacogito.math.unmodifiable.*;

/**
 * @author oleksiy sayankin
 */

public final class Primitives {
    private Primitives(){}

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
}
