package com.javasensei.portfolio.math;

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
    public void moduleTest() {
        IVector a = new Vector(3, 4);
        final Double EXPECTED_MODULE = 5d;
        Double actualModule = MathHelper.module(a);
        assertEquals(EXPECTED_MODULE, actualModule);
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
    public void distanceBetweenPointsTest() {
        IPoint a = new Point(3, 2);
        IPoint b = new Point(7, 5);
        final Double EXPECTED_DISTANCE = 5d;
        Double actualDistance = MathHelper.distanceBetween(a, b);
        assertEquals(EXPECTED_DISTANCE, actualDistance);
    }

    @Test
    public void distanceBetweenPointAndLineTest() {
        IPoint point = new Point(1, 1);
        ILine line = new Line(new Point(0, 1), new Point(1, 0));
        final Double EXPECTED_DISTANCE = 0.5d;
        Double actualDistance = MathHelper.distanceBetween(point, line);
        assertEquals(EXPECTED_DISTANCE, actualDistance);
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
        final IPoint EXPECTED_INTERSECTION_POINT = MathHelper.unmodifiablePoint(0.5d, 0.5d);
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
        final IPoint EXPECTED_INTERSECTION_POINT = MathHelper.unmodifiablePoint(2.5d, 2.5d);
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
        final IPoint EXPECTED_INTERSECTION_POINT = MathHelper.unmodifiablePoint(0.5d, 0.5d);
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

        final IPoint EXPECTED_INTERSECTION_POINT = MathHelper.unmodifiablePoint(509.51162707334277d, 346);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint2);
    }

    @Test
    public void isCollinearTrueTest() {
        IVector a = new Vector(1, 3);
        IVector b = new Vector(3, 9);
        IVector c = new Vector(new Point(5, 15), new Point(6, 18));
        IVector d = new Vector(0, 0);
        IVector e = new Vector(0, 0);
        assertTrue(MathHelper.isCollinear(a, b));
        assertTrue(MathHelper.isCollinear(b, c));
        assertTrue(MathHelper.isCollinear(a, c));
        assertTrue(MathHelper.isCollinear(d, c));
        assertTrue(MathHelper.isCollinear(d, e));
    }

    @Test
    public void isCollinearFalseTest() {
        IVector a = new Vector(2, 3);
        IVector b = new Vector(5, 9);
        IVector c = new Vector(new Point(1, 15), new Point(8, 18));
        assertFalse(MathHelper.isCollinear(a, b));
        assertFalse(MathHelper.isCollinear(b, c));
        assertFalse(MathHelper.isCollinear(a, c));
    }


    @Test
    public void isSemidirectTrueTest() {
        IVector a = new Vector(2, 3);
        IVector b = new Vector(200, 300);
        IVector c = new Vector(new Point(-6, -9), new Point(10, 15));
        assertTrue(MathHelper.isSemidirect(a, b));
        assertTrue(MathHelper.isSemidirect(b, c));
        assertTrue(MathHelper.isSemidirect(a, c));
    }

    @Test
    public void isSemidirectFalseTest() {
        IVector a = new Vector(21, 3);
        IVector b = new Vector(201, 300);
        IVector c = new Vector(new Point(-7, -9), new Point(10, 15));
        assertFalse(MathHelper.isSemidirect(a, b));
        assertFalse(MathHelper.isSemidirect(b, c));
        assertFalse(MathHelper.isSemidirect(a, c));
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

        final ISegment EXPECTED_SEGMENT = MathHelper.unmodifiableSegment(new Segment(width, height, 0, height));
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

        final ISegment EXPECTED_SEGMENT = MathHelper.unmodifiableSegment(new Segment(width, 0, width, width));
        ISegment actualSegment = MathHelper.nearestSegmentInDirection(sides, point, vector);
        assertEquals(EXPECTED_SEGMENT, actualSegment);

    }

    @Test
    public void orthogonalTrueTest() {
        IVector a = new Vector(3, 2);
        IVector EXPECTED_VECTOR = new Vector(-4, 6);
        IVector orthogonalVector = MathHelper.orthogonal(a);
        EXPECTED_VECTOR.normalize();
        IVector actualVector = new Vector(orthogonalVector);
        actualVector.normalize();
        assertEquals(EXPECTED_VECTOR, actualVector);
    }

    @Test
    public void orthogonalFalseTest() {
        IVector a = new Vector(1, 2);
        IVector EXPECTED_VECTOR = new Vector(-2, 3);
        IVector actualVector = MathHelper.orthogonal(a);
        EXPECTED_VECTOR.normalize();
        actualVector.normalize();
        assertFalse(EXPECTED_VECTOR.equals(actualVector));
    }

    @Test
    public void reflectPointAgainstLineTest() {
        IPoint point = new Point(21, 3);
        ILine line = new Line(new Point(0, 0), new Point(5, 5));
        final IPoint EXPECTED_POINT = MathHelper.unmodifiablePoint(3, 21);
        IPoint actualPoint = MathHelper.reflectPointAgainstLine(point, line);
        assertEquals(EXPECTED_POINT, actualPoint);
    }


    @Test
    public void reflectPointAgainstLineAdditionalTest() {
        IPoint point = new Point(-4, 1);
        ILine line = new Line(new Point(0, 0), new Point(-5, 5));
        final IPoint EXPECTED_POINT = MathHelper.unmodifiablePoint(-1, 4);
        IPoint actualPoint = MathHelper.reflectPointAgainstLine(point, line);
        assertEquals(EXPECTED_POINT, actualPoint);
    }

    @Test
    public void reflectVectorAgainstLineTest() {
        IVector vector = new Vector(1, 1);
        ILine line = new Line(new Point(0, 0), new Point(1, 0));
        IVector actualReflectedVector = MathHelper.reflectVectorAgainstLine(vector, line);
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
}