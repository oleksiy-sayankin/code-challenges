package com.javasensei.portfolio.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public class CircleTest {

    @Test
    public void intersectionSimpleTest(){
        double x = 1;
        double y = 1;
        double r = 5;
        ICircle circle = new Circle(x, y, r);
        ILine line = new Line(new Point(x, y), new Point(x + 1, y + 1));
        Set<IPoint> actualPoints = circle.intersection(line);
        Set<IPoint> expectedPoints = new HashSet<IPoint>();

        double x0 = x + r * Math.cos(Math.PI / 4);
        double y0 = y + r * Math.sin(Math.PI / 4);

        double x1 = x - r * Math.cos(Math.PI / 4);
        double y1 = y - r * Math.sin(Math.PI / 4);

        expectedPoints.add(new Point(x0, y0));
        expectedPoints.add(new Point(x1, y1));

        Assert.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void intersectionOnePointTest(){
        double x = 1;
        double y = 1;
        double r = 5;
        ICircle circle = new Circle(x, y, r);

        double x0 = x + r * Math.cos(Math.PI / 4);
        double y0 = y - r * Math.sin(Math.PI / 4);

        ILine line = new Line(new Point(x0, y0), new Point(x0 + 1, y0 + 1));
        Set<IPoint> actualPoints = circle.intersection(line);
        Set<IPoint> expectedPoints = new HashSet<IPoint>();

        expectedPoints.add(new Point(x0, y0));

        Assert.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void intersectionNoPointTest(){
        double x = 1;
        double y = 1;
        double r = 5;
        ICircle circle = new Circle(x, y, r);

        double x0 = x + (r + 1) * Math.cos(Math.PI / 4);
        double y0 = y - (r + 1) * Math.sin(Math.PI / 4);

        ILine line = new Line(new Point(x0, y0), new Point(x0 + 1, y0 + 1));
        Set<IPoint> actualPoints = circle.intersection(line);
        Set<IPoint> expectedPoints = Collections.<IPoint>emptySet();

        Assert.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void intersectionComplexTest(){
        double x = 1;
        double y = 1;
        double r = 5;
        ICircle circle = new Circle(x, y, r);
        ILine line = new Line(new Point(x, y - r), new Point(x + r, y));
        Set<IPoint> actualPoints = circle.intersection(line);
        Set<IPoint> expectedPoints = new HashSet<IPoint>();

        double x0 = x;
        double y0 = y - r;

        double x1 = x + r;
        double y1 = y;

        expectedPoints.add(new Point(x0, y0));
        expectedPoints.add(new Point(x1, y1));

        Assert.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void intersectionVerticalLineTest(){
        double x = 1;
        double y = 1;
        double r = 5;
        ICircle circle = new Circle(x, y, r);
        ILine line = new Line(new Point(x, y - r), new Point(x, y + r));
        Set<IPoint> actualPoints = circle.intersection(line);
        Set<IPoint> expectedPoints = new HashSet<IPoint>();

        double x0 = x;
        double y0 = y - r;

        double x1 = x;
        double y1 = y + r;

        expectedPoints.add(new Point(x0, y0));
        expectedPoints.add(new Point(x1, y1));

        Assert.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void intersectionVerticalLineComplexTest(){
        double x = 1;
        double y = 1;
        double r = 5;
        ICircle circle = new Circle(x, y, r);

        double x0 = x + r * Math.cos(Math.PI / 4);
        double y0 = y + r * Math.sin(Math.PI / 4);

        double x1 = x + r * Math.cos(Math.PI / 4);
        double y1 = y - r * Math.sin(Math.PI / 4);

        ILine line = new Line(new Point(x0, y0), new Point(x1, y1));
        Set<IPoint> actualPoints = circle.intersection(line);
        Set<IPoint> expectedPoints = new HashSet<IPoint>();
        expectedPoints.add(new Point(x0, y0));
        expectedPoints.add(new Point(x1, y1));

        Assert.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void intersectionLineWithAngleTest(){
        double x = 1;
        double y = 1;
        double r = 5;
        ICircle circle = new Circle(x, y, r);

        double alpha0 = -1;
        double alpha1 = -2;

        double x0 = x + r * Math.cos(alpha0);
        double y0 = y + r * Math.sin(alpha0);

        double x1 = x + r * Math.cos(alpha1);
        double y1 = y + r * Math.sin(alpha1);

        ILine line = new Line(new Point(x0, y0), new Point(x1, y1));
        Set<IPoint> actualPoints = circle.intersection(line);
        Set<IPoint> expectedPoints = new HashSet<IPoint>();

        expectedPoints.add(new Point(x0, y0));
        expectedPoints.add(new Point(x1, y1));

        Assert.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void intersectionWithRay(){
        double x = 1;
        double y = 1;
        double r = 5;
        ICircle circle = new Circle(x, y, r);

        double alpha0 = 1;


        double x0 = x + r * Math.cos(alpha0);
        double y0 = y + r * Math.sin(alpha0);

        IRay ray = new Ray(new Point(x, y), new Point(x0, y0));
        Set<IPoint> actualPoints = circle.intersection(ray);
        Set<IPoint> expectedPoints = new HashSet<IPoint>();

        expectedPoints.add(new Point(x0, y0));

        Assert.assertEquals(expectedPoints, actualPoints);
    }

}
