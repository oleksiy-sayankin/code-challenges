package com.javasensei.portfolio.math;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author oleksiy sayankin
 */

public class VectorTest {
    @Test
    public void moduleTest() {
        IVector a = new Vector(3, 4);
        final Double EXPECTED_MODULE = 5d;
        Double actualModule = a.module();
        assertEquals(EXPECTED_MODULE, actualModule);
    }

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

    @Test
    public void isCollinearTrueTest() {
        IVector a = new Vector(1, 3);
        IVector b = new Vector(3, 9);
        IVector c = new Vector(new Point(5, 15), new Point(6, 18));
        IVector d = new Vector(0, 0);
        IVector e = new Vector(0, 0);
        assertTrue(a.isCollinear(b));
        assertTrue(b.isCollinear(c));
        assertTrue(a.isCollinear(c));
        assertTrue(d.isCollinear(c));
        assertTrue(d.isCollinear(e));
    }

    @Test
    public void isCollinearFalseTest() {
        IVector a = new Vector(2, 3);
        IVector b = new Vector(5, 9);
        IVector c = new Vector(new Point(1, 15), new Point(8, 18));
        assertFalse(a.isCollinear(b));
        assertFalse(b.isCollinear(c));
        assertFalse(a.isCollinear(c));
    }

    @Test
    public void isSemidirectTrueTest() {
        IVector a = new Vector(2, 3);
        IVector b = new Vector(200, 300);
        IVector c = new Vector(new Point(-6, -9), new Point(10, 15));
        assertTrue(a.isSemidirect(b));
        assertTrue(b.isSemidirect(c));
        assertTrue(a.isSemidirect(c));
    }

    @Test
    public void isSemidirectFalseTest() {
        IVector a = new Vector(21, 3);
        IVector b = new Vector(201, 300);
        IVector c = new Vector(new Point(-7, -9), new Point(10, 15));
        assertFalse(a.isSemidirect(b));
        assertFalse(b.isSemidirect(c));
        assertFalse(a.isSemidirect(c));
    }

    @Test
    public void reflectVectorAgainstLineTest() {
        IVector vector = new Vector(1, 1);
        ILine line = new Line(new Point(0, 0), new Point(1, 0));
        IVector actualReflectedVector = vector.reflectAgainst(line);
        IVector expectedReflectedVector =  new Vector(1, -1);
        assertEquals(expectedReflectedVector, actualReflectedVector);
    }
}
