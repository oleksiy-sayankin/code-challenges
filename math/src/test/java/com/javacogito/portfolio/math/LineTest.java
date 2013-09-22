package com.javacogito.portfolio.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author oleksiy sayankin
 */
public class LineTest {
    @Test
    public void hasTrueTest() {
        Point point = new Point(401.6420323796443, 78.51515990744261);
        Vector vector = new Vector(0.22980607359851407, 0.5698514120067321);
        assertTrue(new Line(point, vector).has(point));
    }

    @Test
    public void distanceBetweenPointAndLineTest() {
        IPoint point = new Point(1, 1);
        ILine line = new Line(new Point(0, 1), new Point(1, 0));
        final Double EXPECTED_DISTANCE = 0.5d;
        Double actualDistance = line.distanceTo(point);
        assertEquals(EXPECTED_DISTANCE, actualDistance);
    }
}
