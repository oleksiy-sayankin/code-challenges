package com.javasensei.portfolio.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author oleksiy sayankin
 */
public class RayTest {

    @Test
    public void hasTrueTest(){
        double x = 2123.12;
        double y = 122.963;
        double dx = 324.2;
        double dy = 9732.2;
        double coef = 732.43;
        IPoint point = new Point(x, y);
        IVector vector = new Vector(dx, dy);
        IPoint otherPoint = new Point(x + dx * coef, y + dy * coef);
        IRay ray = new Ray(point, vector);
        Assert.assertTrue(ray.has(otherPoint));
    }

    @Test
    public void hasFalseTest(){
        double x = 2123.12;
        double y = 122.963;
        double dx = 324.2;
        double dy = 9732.2;
        double disturbance = 234.234;
        IPoint point = new Point(x, y);
        IVector vector = new Vector(dx, dy);
        IPoint otherPoint = new Point(x + disturbance, y - disturbance);
        IRay ray = new Ray(point, vector);
        Assert.assertFalse(ray.has(otherPoint));
    }

    @Test
    public void distanceTo1Test(){
        IPoint point = new Point(5, 4);
        IVector vector = new Vector(3, 2);
        IPoint otherPoint = new Point(9, 11);
        IRay ray = new Ray(point, vector);
        double actualDistance = ray.distanceTo(otherPoint);
        double expectedDistance = Math.sqrt(2 * 2 + 3 * 3);
        Assert.assertEquals(actualDistance, expectedDistance, MathConstants.ERROR);
    }
    @Test
    public void distanceTo2Test(){
        IPoint point = new Point(5, 4);
        IVector vector = new Vector(3, 2);
        IPoint otherPoint = new Point(-4, -7);
        IRay ray = new Ray(point, vector);
        double actualDistance = ray.distanceTo(otherPoint);
        double expectedDistance = otherPoint.distanceTo(point);
        Assert.assertEquals(actualDistance, expectedDistance, MathConstants.ERROR);
    }

}
