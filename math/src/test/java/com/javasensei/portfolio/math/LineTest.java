package com.javasensei.portfolio.particles.math;

import org.junit.Test;

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
}
