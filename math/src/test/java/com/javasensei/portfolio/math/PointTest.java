package com.javasensei.portfolio.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author oleksiy sayankin
 */
public class PointTest {

    @Test
    public void distanceBetweenPointsTest() {
        IPoint a = new Point(3, 2);
        IPoint b = new Point(7, 5);
        final Double EXPECTED_DISTANCE = 5d;
        Double actualDistance = a.distanceTo(b);
        assertEquals(EXPECTED_DISTANCE, actualDistance);
    }

    @Test
    public void reflectPointAgainstLineTest() {
        IPoint point = new Point(21, 3);
        ILine line = new Line(new Point(0, 0), new Point(5, 5));
        final IPoint EXPECTED_POINT = Primitives.unmodifiablePoint(3, 21);
        IPoint actualPoint = point.reflectAgainst(line);
        assertEquals(EXPECTED_POINT, actualPoint);
    }


    @Test
    public void reflectPointAgainstLineAdditionalTest() {
        IPoint point = new Point(-4, 1);
        ILine line = new Line(new Point(0, 0), new Point(-5, 5));
        final IPoint EXPECTED_POINT = Primitives.unmodifiablePoint(-1, 4);
        IPoint actualPoint = point.reflectAgainst(line);
        assertEquals(EXPECTED_POINT, actualPoint);
    }
}
