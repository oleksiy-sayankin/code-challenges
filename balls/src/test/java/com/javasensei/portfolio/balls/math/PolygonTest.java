package com.javasensei.portfolio.balls.math;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author asayankin
 */
public class PolygonTest {
    @Test
    public void hasTrueForTriangleTest(){
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(1.5, 3));
        polygon.addPoint(new Point(3, 0));
        assertTrue(polygon.has(new Point(1, 1)));
    }

    @Test
    public void hasFalseForTriangleTest(){
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(1.5, 3));
        polygon.addPoint(new Point(3, 0));
        assertFalse(polygon.has(new Point(1, -1)));
    }

    @Test
    public void hasTrueForRectangleTest(){
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(0, 3));
        polygon.addPoint(new Point(5, 3));
        polygon.addPoint(new Point(0, 3));
        assertTrue(polygon.has(new Point(1, 1)));
    }

    @Test
    public void hasFalseForRectangleTest(){
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(0, 3));
        polygon.addPoint(new Point(5, 3));
        polygon.addPoint(new Point(0, 3));
        assertFalse(polygon.has(new Point(-1, 1)));
    }

    @Test
    public void hasTrueForComplexFigureTest(){
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(0, 6));
        polygon.addPoint(new Point(8, 6));
        polygon.addPoint(new Point(8, 2));
        polygon.addPoint(new Point(4, 2));
        polygon.addPoint(new Point(4, 4));
        polygon.addPoint(new Point(5, 4));
        polygon.addPoint(new Point(5, 3));
        polygon.addPoint(new Point(7, 3));
        polygon.addPoint(new Point(7, 5));
        polygon.addPoint(new Point(3, 5));
        polygon.addPoint(new Point(3, 1));
        polygon.addPoint(new Point(8, 1));
        polygon.addPoint(new Point(8, 0));
        assertTrue(polygon.has(new Point(4.5, 3.5)));
        assertTrue(polygon.has(new Point(2, 2)));
        assertTrue(polygon.has(new Point(2, 5)));
        assertTrue(polygon.has(new Point(5.5, 5.5)));
        assertTrue(polygon.has(new Point(7.5, 2.5)));
        assertTrue(polygon.has(new Point(7.5, 0.5)));
    }

    @Test
    public void hasFalseForComplexFigureTest(){
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(0, 6));
        polygon.addPoint(new Point(8, 6));
        polygon.addPoint(new Point(8, 2));
        polygon.addPoint(new Point(4, 2));
        polygon.addPoint(new Point(4, 4));
        polygon.addPoint(new Point(5, 4));
        polygon.addPoint(new Point(5, 3));
        polygon.addPoint(new Point(7, 3));
        polygon.addPoint(new Point(7, 5));
        polygon.addPoint(new Point(3, 5));
        polygon.addPoint(new Point(3, 1));
        polygon.addPoint(new Point(8, 1));
        polygon.addPoint(new Point(8, 0));
        assertFalse(polygon.has(new Point(5.5, 3.5)));
        assertFalse(polygon.has(new Point(-0.5, 3.5)));
        assertFalse(polygon.has(new Point(-1, 3.5)));
        assertFalse(polygon.has(new Point(3.5, 1.5)));
        assertFalse(polygon.has(new Point(3.5, 2.5)));
        assertFalse(polygon.has(new Point(3.5, 4.5)));
        assertFalse(polygon.has(new Point(4.5, 4.5)));
        assertFalse(polygon.has(new Point(5.5, 3.5)));
        assertFalse(polygon.has(new Point(5.5, 3.5)));
        assertFalse(polygon.has(new Point(6.5, 3.5)));
        assertFalse(polygon.has(new Point(7.5, 1.5)));
    }

    @Test
    public void stretchInDirectionTest(){
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(2, 6));
        polygon.addPoint(new Point(4, 0));
        polygon.stretch(new Point(4, 0), new Vector(1, 0));
        assertTrue(polygon.has(new Point(4.51, 0.01)));
    }

}
