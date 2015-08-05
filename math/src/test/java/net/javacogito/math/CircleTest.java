package net.javacogito.math;

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

    @Test
    public void hasTest(){
        ICircle circle1 = new Circle(-1, 3, 3);
        IPoint point11 = new Point(-3.9641798961434582, 2.5377905850169697);
        IPoint point12 = new Point(1.5959900629229289, 4.503607526319753);

        ICircle circle2 = new Circle(-1, 5, 2);
        IPoint point21 = new Point(0.849901182297058, 4.239825272890537);
        IPoint point22 = new Point(-1.9610122934081708, 3.24601728289063);

        ICircle circle3 = new Circle(0, 5, 1);
        IPoint point31 = new Point(0.3333333333333327, 4.057190958417937);


        Assert.assertFalse(circle2.has(point11));
        Assert.assertFalse(circle2.has(point12));
        Assert.assertFalse(circle2.has(point31));

        Assert.assertTrue(circle2.has(point21));
        Assert.assertTrue(circle2.has(point22));

        Assert.assertTrue(circle3.has(point31));

        Assert.assertTrue(circle1.has(point11));
        Assert.assertTrue(circle1.has(point12));

    }

}
