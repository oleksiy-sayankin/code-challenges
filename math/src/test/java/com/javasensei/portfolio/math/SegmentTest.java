package com.javasensei.portfolio.math;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author oleksiy sayankin
 */

public class SegmentTest {
    @Test
    public void TestfindIntersection001() {
        Segment a = new Segment(new Point(1d, 0d), new Point(1d, 2d));
        Segment b = new Segment(new Point(0d, 1d), new Point(2d, 1d));
        IPoint test = MathHelper.intersection(a, b);
        Point correct = new Point(1, 1);
        assertEquals(correct, test);
    }

    @Test
    public void TestfindIntersection002() {
        Segment a = new Segment(new Point(0d, 0d), new Point(0d, 368d));
        Segment b = new Segment(new Point(0.3d, 0.9), new Point(-3.462439786393077d, -3.5547287070894034d));
        IPoint test = MathHelper.intersection(a, b);
        Point correct = new Point(0d, 0.5448d);
        assertEquals(correct, test);
    }
}


