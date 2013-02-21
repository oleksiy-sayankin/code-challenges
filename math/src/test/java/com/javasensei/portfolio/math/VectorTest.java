package com.javasensei.portfolio.particles.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class VectorTest {
    @Test
    public void testFindReflection001() {
        Vector a = new Vector(1, 1);
        Vector b = new Vector(1, 0);
        IVector test = a.reflectAgainst(b.toLine());
        Vector correct = new Vector(1, -1);
        assertEquals(correct, test);
    }

    @Test
    public void testFindReflection002() {
        Vector a = new Vector(-1.34, -1.267);
        Vector b = new Vector(0, 1);
        IVector test = a.reflectAgainst(b.toLine());
        Vector correct = new Vector(1.34, -1.267);
        assertEquals(correct, test);
    }

    @Test
    public void testFindCosAngle() {
        Vector a = new Vector(1, 1);
        Vector b = new Vector(1, 0);
        double test = MathHelper.cos(a, b);
        double correct = Math.cos(Math.PI / 4d);
        assertEquals(correct, test, MathConstants.ERROR);
    }
}
