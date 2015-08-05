package net.javacogito.math.unmodifiable;

import net.javacogito.math.*;

/**
 * @author oleksiy sayankin
 */
public final class UnmodifiableSegment extends Segment {

    public UnmodifiableSegment(IPoint aPoint1, IPoint aPoint2) {
        super(Primitives.unmodifiablePoint(aPoint1), Primitives.unmodifiablePoint(aPoint2));
    }

    public UnmodifiableSegment(double x1, double y1, double x2, double y2) {
        super(Primitives.unmodifiablePoint(new Point(x1, y1)), Primitives.unmodifiablePoint(new Point(x2, y2)));
    }

    public UnmodifiableSegment(ISegment segment) {
        super(Primitives.unmodifiablePoint(segment.getStartPoint()), Primitives.unmodifiablePoint(segment.getEndPoint()));
    }

}
