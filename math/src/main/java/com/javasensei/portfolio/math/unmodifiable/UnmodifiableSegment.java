package com.javasensei.portfolio.math.unmodifiable;

import com.javasensei.portfolio.math.*;

/**
 * @author oleksiy sayankin
 */
public final class UnmodifiableSegment extends Segment {

    public UnmodifiableSegment(IPoint aPoint1, IPoint aPoint2) {
        super(MathHelper.unmodifiablePoint(aPoint1), MathHelper.unmodifiablePoint(aPoint2));
    }

    public UnmodifiableSegment(double x1, double y1, double x2, double y2) {
        super(MathHelper.unmodifiablePoint(new Point(x1, y1)), MathHelper.unmodifiablePoint(new Point(x2, y2)));
    }

    public UnmodifiableSegment(ISegment segment) {
        super(MathHelper.unmodifiablePoint(segment.getPoint1()), MathHelper.unmodifiablePoint(segment.getPoint2()));
    }

}
