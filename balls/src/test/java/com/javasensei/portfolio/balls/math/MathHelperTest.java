package com.javasensei.portfolio.balls.math;

import com.javasensei.portfolio.balls.math.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author asayankin
 */
public class MathHelperTest {
    @Test
    public void scalarProductTest(){
        Vector a = new Vector(3, 2);
        Vector b = new Vector(-2 ,4);
        final Double EXPECTED_SCALAR_PRODUCT = 2d;
        Double actualScalarProduct = MathHelper.scalarProduct(a, b);
        assertEquals(EXPECTED_SCALAR_PRODUCT, actualScalarProduct);
    }

    @Test
    public void moduleTest(){
        Vector a = new Vector(3, 4);
        final Double EXPECTED_MODULE = 5d;
        Double actualModule = MathHelper.module(a);
        assertEquals(EXPECTED_MODULE, actualModule);
    }

    @Test
    public void cosTest(){
        Vector a = new Vector(4, 0);
        Vector b = new Vector(4 ,3);
        final Double EXPECTED_COS = 4d / 5d;
        Double actualCos = MathHelper.cos(a, b);
        assertEquals(EXPECTED_COS, actualCos);
    }

    @Test
    public void distanceBetweenPointsTest(){
        Point a = new Point(3, 2);
        Point b = new Point(7, 5);
        final Double EXPECTED_DISTANCE = 5d;
        Double actualDistance = MathHelper.distanceBetween(a, b);
        assertEquals(EXPECTED_DISTANCE, actualDistance);
    }

    @Test
    public void distanceBetweenPointAndLineTest(){
        Point point = new Point(1, 1);
        Line line = new Line(new Point(0, 1), new Point(1,0));
        final Double EXPECTED_DISTANCE = 0.5d;
        Double actualDistance = MathHelper.distanceBetween(point, line);
        assertEquals(EXPECTED_DISTANCE, actualDistance);
    }

    @Test
    public void detTest(){
        final Double EXPECTED_DET = -17d;
        Double actualDet = MathHelper.det(3, 5, 4, 1);
        assertEquals(EXPECTED_DET, actualDet);
    }

    @Test
    public void isIntersectionTrueTest(){
        Line lineA = new Line(new Point(0, 1), new Point(1,0));
        Line lineB = new Line(new Point(0, 0), new Point(1,1));
        Boolean actualIsIntersection = MathHelper.isIntersection(lineA, lineB);
        assertTrue(actualIsIntersection);
    }

    @Test
    public void isSelfIntersectionFalseTest(){
        Line line = new Line(new Point(0, 1), new Point(1,0));
        Boolean actualIsIntersection = MathHelper.isIntersection(line, line);
        assertFalse(actualIsIntersection);
    }

    @Test
    public void isIntersectionLineAndLineFalseTest(){
        Line lineA = new Line(new Point(0, 1), new Point(1,0));
        Line lineB = new Line(new Point(0, 44), new Point(44,0));
        Boolean actualIsIntersection = MathHelper.isIntersection(lineA, lineB);
        assertFalse(actualIsIntersection);
    }

    @Test
    public void intersectionBetweenLineAndLineTrueTest(){
        Line lineA = new Line(new Point(0, 1), new Point(1,0));
        Line lineB = new Line(new Point(0, 0), new Point(1,1));
        final IPoint EXPECTED_INTERSECTION_POINT = MathHelper.unmodifiablePoint(0.5d, 0.5d);
        Point actualIntersectionPoint = MathHelper.intersection(lineA, lineB);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenSegmentAndSegmentFalseTest(){
        Segment segmentA = new Segment(new Point(2, 3), new Point(3, 2));
        Segment segmentB = new Segment(new Point(34, 23), new Point(8, 42));
        IPoint actualIntersectionPoint = MathHelper.intersection(segmentA, segmentB);
        assertTrue(MathHelper.isIntersection(segmentA.toLine(), segmentB.toLine()));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void selfIntersectionBetweenSegmentAndSegmentFalseTest(){
        Segment segment = new Segment(new Point(2, 3), new Point(3, 2));
        IPoint actualIntersectionPoint = MathHelper.intersection(segment, segment);
        assertFalse(MathHelper.isIntersection(segment.toLine(), segment.toLine()));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenSegmentAndSegmentTrueTest(){
        Segment segmentA = new Segment(new Point(2, 3), new Point(3, 2));
        Segment segmentB = new Segment(new Point(0, 0), new Point(4, 4));
        final IPoint EXPECTED_INTERSECTION_POINT = MathHelper.unmodifiablePoint(2.5d, 2.5d);
        IPoint actualIntersectionPoint = MathHelper.intersection(segmentA, segmentB);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenLineAndSegmentFalseTest(){
        Segment segment = new Segment(new Point(2, 3), new Point(3, 2));
        Line line = new Line(new Point(4, 5), new Point(8, 3));
        Point actualIntersectionPoint = MathHelper.intersection(line, segment);
        assertTrue(MathHelper.isIntersection(segment.toLine(), line));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenLineAndSegmentTrueTest(){
        Segment segment = new Segment(new Point(0.1d, 0.1d), new Point(1.1d, 1.1d));
        Line line = new Line(new Point(0, 1), new Point(1, 0));
        Point actualIntersectionPoint = MathHelper.intersection(line, segment);
        final IPoint EXPECTED_INTERSECTION_POINT = MathHelper.unmodifiablePoint(0.5d, 0.5d);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenLineAndSegmentAdditionalTrueTest(){
        List<Segment> sides = new ArrayList<Segment>();
        double width =  686d;
        double height = 346d;
        sides.add(new Segment(new Point(0, 0), new Point(width, 0)));
        sides.add(new Segment(new Point(width,0), new Point(width, height)));
        sides.add(new Segment(new Point(width, height), new Point(0, height)));
        sides.add(new Segment(new Point(0, height), new Point(0,0)));
        Point point = new Point(401.6420323796443, 78.51515990744261);
        Vector vector = new Vector(0.22980607359851407, 0.5698514120067321);

        Point actualIntersectionPoint0 = MathHelper.intersection(new Line(point, vector), sides.get(0).toLine());
        Point actualIntersectionPoint1 = MathHelper.intersection(new Line(point, vector), sides.get(1).toLine());
        Point actualIntersectionPoint2 = MathHelper.intersection(new Line(point, vector), sides.get(2).toLine());
        Point actualIntersectionPoint3 = MathHelper.intersection(new Line(point, vector), sides.get(3).toLine());

        System.out.println(actualIntersectionPoint0);
        System.out.println(actualIntersectionPoint1);
        System.out.println(actualIntersectionPoint2);
        System.out.println(actualIntersectionPoint3);

        vector.normalize();
        Vector direction = new Vector(point, actualIntersectionPoint2);
        direction.normalize();

        System.out.println(direction);
        System.out.println(vector);

        System.out.println(MathHelper.isSemidirect(direction, vector));
        System.out.println(MathHelper.scalarProduct(direction, vector));


        final IPoint EXPECTED_INTERSECTION_POINT = MathHelper.unmodifiablePoint(509.51162707334277d, 346);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint2);
    }

    @Test
    public void isCollinearTrueTest(){
        Vector a = new Vector(1, 3);
        Vector b = new Vector(3, 9);
        Vector c = new Vector(new Point(5, 15), new Point(6, 18));
        Vector d = new Vector(0, 0);
        Vector e = new Vector(0, 0);
        assertTrue(MathHelper.isCollinear(a, b));
        assertTrue(MathHelper.isCollinear(b, c));
        assertTrue(MathHelper.isCollinear(a, c));
        assertTrue(MathHelper.isCollinear(d, c));
        assertTrue(MathHelper.isCollinear(d, e));
    }

    @Test
    public void isCollinearFalseTest(){
        Vector a = new Vector(2, 3);
        Vector b = new Vector(5, 9);
        Vector c = new Vector(new Point(1, 15), new Point(8, 18));
        assertFalse(MathHelper.isCollinear(a, b));
        assertFalse(MathHelper.isCollinear(b, c));
        assertFalse(MathHelper.isCollinear(a, c));
    }


    @Test
    public void isSemidirectTrueTest(){
        Vector a = new Vector(2, 3);
        Vector b = new Vector(200, 300);
        Vector c = new Vector(new Point(-6, -9), new Point(10, 15));
        assertTrue(MathHelper.isSemidirect(a, b));
        assertTrue(MathHelper.isSemidirect(b, c));
        assertTrue(MathHelper.isSemidirect(a, c));
    }

    @Test
    public void isSemidirectFalseTest(){
        Vector a = new Vector(21, 3);
        Vector b = new Vector(201, 300);
        Vector c = new Vector(new Point(-7, -9), new Point(10, 15));
        assertFalse(MathHelper.isSemidirect(a, b));
        assertFalse(MathHelper.isSemidirect(b, c));
        assertFalse(MathHelper.isSemidirect(a, c));
    }

    @Test
    public void nearestSegmentInDirectionTest(){
        List<ISegment> sides = new ArrayList<ISegment>();
        double width =  686d;
        double height = 346d;
        sides.add(new Segment(new Point(0, 0), new Point(width, 0)));
        sides.add(new Segment(new Point(width,0), new Point(width, height)));
        sides.add(new Segment(new Point(width, height), new Point(0, height)));
        sides.add(new Segment(new Point(0, height), new Point(0,0)));
        Point point = new Point(401.6420323796443, 78.51515990744261);
        Vector vector = new Vector(0.22980607359851407, 0.5698514120067321);

        final ISegment EXPECTED_SEGMENT = MathHelper.unmodifiableSegment(new Segment(width,height, 0,height));
        ISegment actualSegment = MathHelper.nearestSegmentInDirection(sides, point, vector);
        assertEquals(EXPECTED_SEGMENT, actualSegment);

    }

    @Test
    public void nearestSegmentInDirectionAdditionalTest(){
        List<ISegment> sides = new ArrayList<ISegment>();
        double width =  5d;
        double height = 5d;
        sides.add(new Segment(new Point(0, 0), new Point(width, 0)));
        sides.add(new Segment(new Point(width,0), new Point(width, height)));
        sides.add(new Segment(new Point(width, height), new Point(0, height)));
        sides.add(new Segment(new Point(0, height), new Point(0,0)));
        Point point = new Point(4, 4);
        Vector vector = new Vector(1, 1);

        final ISegment EXPECTED_SEGMENT = MathHelper.unmodifiableSegment(new Segment(width,0, width,width));
        ISegment actualSegment = MathHelper.nearestSegmentInDirection(sides, point, vector);
        assertEquals(EXPECTED_SEGMENT, actualSegment);

    }

    @Test
    public void orthogonalTrueTest(){
        Vector a = new Vector(3, 2);
        Vector EXPECTED_VECTOR = new Vector(-4, 6);
        Vector actualVector = MathHelper.orthogonal(a);
        EXPECTED_VECTOR.normalize();
        actualVector.normalize();
        assertEquals(EXPECTED_VECTOR, actualVector);
    }

    @Test
    public void orthogonalFalseTest(){
        Vector a = new Vector(1, 2);
        Vector EXPECTED_VECTOR = new Vector(-2, 3);
        Vector actualVector = MathHelper.orthogonal(a);
        EXPECTED_VECTOR.normalize();
        actualVector.normalize();
        assertFalse(EXPECTED_VECTOR.equals(actualVector));
    }


}