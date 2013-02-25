package com.javasensei.portfolio.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author oleksiy sayankin
 */
public class MathHelperTest {
    @Test
    public void scalarProductTest() {
        IVector a = new Vector(3, 2);
        IVector b = new Vector(-2, 4);
        final Double EXPECTED_SCALAR_PRODUCT = 2d;
        Double actualScalarProduct = MathHelper.scalarProduct(a, b);
        assertEquals(EXPECTED_SCALAR_PRODUCT, actualScalarProduct);
    }



    @Test
    public void cosTest() {
        IVector a = new Vector(4, 0);
        IVector b = new Vector(4, 3);
        final Double EXPECTED_COS = 4d / 5d;
        Double actualCos = MathHelper.cos(a, b);
        assertEquals(EXPECTED_COS, actualCos);
    }




    @Test
    public void detTest() {
        final Double EXPECTED_DET = -17d;
        Double actualDet = MathHelper.det(3, 5, 4, 1);
        assertEquals(EXPECTED_DET, actualDet);
    }

    @Test
    public void isIntersectionTrueTest() {
        ILine lineA = new Line(new Point(0, 1), new Point(1, 0));
        ILine lineB = new Line(new Point(0, 0), new Point(1, 1));
        Boolean actualIsIntersection = MathHelper.isIntersection(lineA, lineB);
        assertTrue(actualIsIntersection);
    }

    @Test
    public void isSelfIntersectionFalseTest() {
        ILine line = new Line(new Point(0, 1), new Point(1, 0));
        Boolean actualIsIntersection = MathHelper.isIntersection(line, line);
        assertFalse(actualIsIntersection);
    }

    @Test
    public void isIntersectionLineAndLineFalseTest() {
        ILine lineA = new Line(new Point(0, 1), new Point(1, 0));
        ILine lineB = new Line(new Point(0, 44), new Point(44, 0));
        Boolean actualIsIntersection = MathHelper.isIntersection(lineA, lineB);
        assertFalse(actualIsIntersection);
    }

    @Test
    public void intersectionBetweenLineAndLineTrueTest() {
        ILine lineA = new Line(new Point(0, 1), new Point(1, 0));
        ILine lineB = new Line(new Point(0, 0), new Point(1, 1));
        final IPoint EXPECTED_INTERSECTION_POINT = Primitives.unmodifiablePoint(0.5d, 0.5d);
        IPoint actualIntersectionPoint = MathHelper.intersection(lineA, lineB);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenSegmentAndSegmentFalseTest() {
        ISegment segmentA = new Segment(new Point(2, 3), new Point(3, 2));
        ISegment segmentB = new Segment(new Point(34, 23), new Point(8, 42));
        IPoint actualIntersectionPoint = MathHelper.intersection(segmentA, segmentB);
        assertTrue(MathHelper.isIntersection(segmentA.toLine(), segmentB.toLine()));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void selfIntersectionBetweenSegmentAndSegmentFalseTest() {
        ISegment segment = new Segment(new Point(2, 3), new Point(3, 2));
        IPoint actualIntersectionPoint = MathHelper.intersection(segment, segment);
        assertFalse(MathHelper.isIntersection(segment.toLine(), segment.toLine()));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenSegmentAndSegmentTrueTest() {
        ISegment segmentA = new Segment(new Point(2, 3), new Point(3, 2));
        ISegment segmentB = new Segment(new Point(0, 0), new Point(4, 4));
        final IPoint EXPECTED_INTERSECTION_POINT = Primitives.unmodifiablePoint(2.5d, 2.5d);
        IPoint actualIntersectionPoint = MathHelper.intersection(segmentA, segmentB);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenLineAndSegmentFalseTest() {
        ISegment segment = new Segment(new Point(2, 3), new Point(3, 2));
        ILine line = new Line(new Point(4, 5), new Point(8, 3));
        IPoint actualIntersectionPoint = MathHelper.intersection(line, segment);
        assertTrue(MathHelper.isIntersection(segment.toLine(), line));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenLineAndSegmentTrueTest() {
        ISegment segment = new Segment(new Point(0.1d, 0.1d), new Point(1.1d, 1.1d));
        ILine line = new Line(new Point(0, 1), new Point(1, 0));
        IPoint actualIntersectionPoint = MathHelper.intersection(line, segment);
        final IPoint EXPECTED_INTERSECTION_POINT = Primitives.unmodifiablePoint(0.5d, 0.5d);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenLineAndSegmentAdditionalTrueTest() {
        List<Segment> sides = new ArrayList<Segment>();
        double width = 686d;
        double height = 346d;
        sides.add(new Segment(new Point(0, 0), new Point(width, 0)));
        sides.add(new Segment(new Point(width, 0), new Point(width, height)));
        sides.add(new Segment(new Point(width, height), new Point(0, height)));
        sides.add(new Segment(new Point(0, height), new Point(0, 0)));
        IPoint point = new Point(401.6420323796443, 78.51515990744261);
        IVector vector = new Vector(0.22980607359851407, 0.5698514120067321);

        IPoint actualIntersectionPoint2 = MathHelper.intersection(new Line(point, vector), sides.get(2).toLine());

        vector.normalize();
        IVector direction = new Vector(point, actualIntersectionPoint2);
        direction.normalize();

        final IPoint EXPECTED_INTERSECTION_POINT = Primitives.unmodifiablePoint(509.51162707334277d, 346);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint2);
    }




    @Test
    public void nearestSegmentInDirectionTest() {
        List<ISegment> sides = new ArrayList<ISegment>();
        double width = 686d;
        double height = 346d;
        sides.add(new Segment(new Point(0, 0), new Point(width, 0)));
        sides.add(new Segment(new Point(width, 0), new Point(width, height)));
        sides.add(new Segment(new Point(width, height), new Point(0, height)));
        sides.add(new Segment(new Point(0, height), new Point(0, 0)));
        Point point = new Point(401.6420323796443, 78.51515990744261);
        Vector vector = new Vector(0.22980607359851407, 0.5698514120067321);

        final ISegment EXPECTED_SEGMENT = Primitives.unmodifiableSegment(new Segment(width, height, 0, height));
        ISegment actualSegment = MathHelper.nearestSegmentInDirection(sides, point, vector);
        assertEquals(EXPECTED_SEGMENT, actualSegment);

    }

    @Test
    public void nearestSegmentInDirectionAdditionalTest() {
        List<ISegment> sides = new ArrayList<ISegment>();
        double width = 5d;
        double height = 5d;
        sides.add(new Segment(new Point(0, 0), new Point(width, 0)));
        sides.add(new Segment(new Point(width, 0), new Point(width, height)));
        sides.add(new Segment(new Point(width, height), new Point(0, height)));
        sides.add(new Segment(new Point(0, height), new Point(0, 0)));
        IPoint point = new Point(4, 4);
        IVector vector = new Vector(1, 1);

        final ISegment EXPECTED_SEGMENT = Primitives.unmodifiableSegment(new Segment(width, 0, width, width));
        ISegment actualSegment = MathHelper.nearestSegmentInDirection(sides, point, vector);
        assertEquals(EXPECTED_SEGMENT, actualSegment);

    }


    @Test
    public void signedAreaTest() {
        IVector a = new Vector(0, 1);
        IVector b = new Vector(1, 0);
        Double EXPECTED_SIGNED_AREA = -1d;
        Double actualSignedArea = MathHelper.signedArea(a, b);
        assertEquals(EXPECTED_SIGNED_AREA, actualSignedArea);
    }

    @Test
    public void nearestSegmentInDirectionNewTest() {
        IPolygon sides = new Polygon();
        sides.addPoint(new Point(0, 0));
        sides.addPoint(new Point(0, 366));
        sides.addPoint(new Point(1019, 366));
        sides.addPoint(new Point(1019, 0));
        IPoint coord = new Point(177.2951140292843, 223.66314531649516);
        IVector velocity = new Vector(3.9412324599388047, 2.352070331338597);
        ISegment actualSegment = MathHelper.nearestSegmentInDirection(sides.toSegmentsClockwise(), coord, velocity);
        ISegment EXPECTED_SEGMENT = new Segment(new Point(0, 366), new Point(1019, 366));
        assertEquals(EXPECTED_SEGMENT, actualSegment);
    }

    @Test
    public void minCircleContainingAllTest(){
        List<ICircle> circles = new ArrayList<ICircle>();
        double radius = 3d;
        double offset = 4;
        circles.add(new Circle(new Point(offset, offset), radius));
        circles.add(new Circle(new Point(offset, -offset), radius));
        circles.add(new Circle(new Point(-offset, -offset), radius));
        circles.add(new Circle(new Point(-offset, offset), radius));
        Point center = new Point(0, 0);
        double expectedRadius = Math.sqrt(offset * offset +  offset * offset) + radius;
        Circle expectedCircle = new Circle(center, expectedRadius);
        Circle actualCircle = MathHelper.minCircleContainingAll(circles, center);
        Assert.assertEquals(expectedCircle, actualCircle);
    }

    @Test
    public void shadowTest(){
        Circle circle = new Circle(new Point(5, 5), 5);
        Point point = new Point(0, 0);
        IArc expectedSegment = new Arc(0, Math.PI / 2);
        IArc actualSegment = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedSegment, actualSegment);
    }

    @Test
    public void shadowComplexTest(){
        double radius = 2;
        double x = -2;
        double y = 2;
        ICircle circle = new Circle(new Point(x, y), radius);
        IPoint point = new Point(0, 0);
        IArc expectedSegment = new Arc( Math.PI / 2,   Math.PI);
        IArc actualSegment = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedSegment, actualSegment);
    }

    @Test
    public void shadowComplex2Test(){
        double radius = 7;
        double h = radius / Math.sin(Math.PI / 12);
        double x = h * Math.sin(Math.PI / 4);
        double y = - h * Math.sin(Math.PI / 4);
        ICircle circle = new Circle(new Point(x, y), radius);
        IPoint point = new Point(0, 0);
        IArc expectedSegment = new Arc((2 * Math.PI / 12) * 10,  (2 * Math.PI / 12) * 11);
        IArc actualSegment = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedSegment, actualSegment);
    }

    @Test
    public void shadowComplex3Test(){
        double radius = 2;
        double x = 2;
        double y = 2;
        ICircle circle = new Circle(new Point(x, y), radius);
        IPoint point = new Point(0, 0);
        IArc expectedSegment = new Arc( 0,   Math.PI / 2);
        IArc actualSegment = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedSegment, actualSegment);
    }

    @Test
    public void shadowComplex4Test(){
        double radius = 2;
        double x = 8;
        double y = 19;
        ICircle circle = new Circle(new Point(x, y), radius);
        IPoint point = new Point(10, 17);
        IArc expectedSegment = new Arc(Math.PI / 2 ,   Math.PI);
        IArc actualSegment = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedSegment, actualSegment);
    }
}