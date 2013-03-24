package com.javasensei.portfolio.math;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author oleksiy sayankin
 */
public class MathHelperTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MathHelperTest.class);
    @Before
    public void init(){
        PropertyConfigurator.configure("log4j.properties");
    }

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
        Boolean actualIsIntersection = lineA.isIntersection(lineB);
        assertTrue(actualIsIntersection);
    }

    @Test
    public void isSelfIntersectionFalseTest() {
        ILine line = new Line(new Point(0, 1), new Point(1, 0));
        Boolean actualIsIntersection = line.isIntersection(line);
        assertFalse(actualIsIntersection);
    }

    @Test
    public void isIntersectionLineAndLineFalseTest() {
        ILine lineA = new Line(new Point(0, 1), new Point(1, 0));
        ILine lineB = new Line(new Point(0, 44), new Point(44, 0));
        Boolean actualIsIntersection = lineA.isIntersection(lineB);
        assertFalse(actualIsIntersection);
    }

    @Test
    public void intersectionBetweenLineAndLineTrueTest() {
        ILine lineA = new Line(new Point(0, 1), new Point(1, 0));
        ILine lineB = new Line(new Point(0, 0), new Point(1, 1));
        final IPoint EXPECTED_INTERSECTION_POINT = Primitives.unmodifiablePoint(0.5d, 0.5d);
        IPoint actualIntersectionPoint = lineA.intersection(lineB);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenSegmentAndSegmentFalseTest() {
        ISegment segmentA = new Segment(new Point(2, 3), new Point(3, 2));
        ISegment segmentB = new Segment(new Point(34, 23), new Point(8, 42));
        IPoint actualIntersectionPoint = segmentA.intersection(segmentB);
        assertTrue(segmentA.toLine().isIntersection(segmentB.toLine()));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void selfIntersectionBetweenSegmentAndSegmentFalseTest() {
        ISegment segment = new Segment(new Point(2, 3), new Point(3, 2));
        IPoint actualIntersectionPoint = segment.intersection(segment);
        assertFalse(segment.toLine().isIntersection(segment.toLine()));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenSegmentAndSegmentTrueTest() {
        ISegment segmentA = new Segment(new Point(2, 3), new Point(3, 2));
        ISegment segmentB = new Segment(new Point(0, 0), new Point(4, 4));
        final IPoint EXPECTED_INTERSECTION_POINT = Primitives.unmodifiablePoint(2.5d, 2.5d);
        IPoint actualIntersectionPoint = segmentA.intersection(segmentB);
        assertEquals(EXPECTED_INTERSECTION_POINT, actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenLineAndSegmentFalseTest() {
        ISegment segment = new Segment(new Point(2, 3), new Point(3, 2));
        ILine line = new Line(new Point(4, 5), new Point(8, 3));
        IPoint actualIntersectionPoint = line.intersection(segment);
        assertTrue(segment.toLine().isIntersection(line));
        assertNull(actualIntersectionPoint);
    }

    @Test
    public void intersectionBetweenLineAndSegmentTrueTest() {
        ISegment segment = new Segment(new Point(0.1d, 0.1d), new Point(1.1d, 1.1d));
        ILine line = new Line(new Point(0, 1), new Point(1, 0));
        IPoint actualIntersectionPoint = line.intersection(segment);
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

        IPoint actualIntersectionPoint2 = new Line(point, vector).intersection(sides.get(2).toLine());

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

    @Test
    public void shadowComplex001PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 13511.91;
        double y = 19985.14;
        double radius = 3162.36;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = Math.abs(circle.getCenter().getY() - point.getY());
        double dx = Math.abs(circle.getCenter().getX() - point.getX());
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha = Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex002PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 2886.10;
        double y = 17267.74;
        double radius = 1463.67;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha = Math.PI - Math.asin(sinAlpha) ;
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex003PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = -7519.49;
        double y = 11191.86;
        double radius = 2666.43;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha = Math.PI - Math.asin(sinAlpha) ;
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex004PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 14917.34;
        double y = -18730.64;
        double radius = 7646.41;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.asin(sinAlpha) ;
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex005PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = -19355.62;
        double y = 23243.36;
        double radius = 6205.99;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.PI - Math.asin(sinAlpha) ;
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex006PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 3031.66;
        double y = 6301.12;
        double radius = 1178.17;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.PI - Math.asin(sinAlpha) ;
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex007PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 9984.78;
        double y = 5537.91;
        double radius = 2033.35;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.asin(sinAlpha) ;
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex008PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 1022.61;
        double y = 9823.68;
        double radius = 704.56;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  - Math.PI - Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex009PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 31610.72;
        double y = 11149.89;
        double radius = 42.25;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex010PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 7445.01;
        double y = 21301.20;
        double radius = 2583.35;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex011PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = -1327.28;
        double y = 4020.72;
        double radius = 2084.76;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha = -Math.PI - Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex012PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 33674.61;
        double y = 18684.91;
        double radius = 7403.20;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex013PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 19190.25;
        double y = 7311.46;
        double radius = 3833.87;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex014PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = -5281.84;
        double y = 24080.87;
        double radius = 3747.03;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha = Math.PI - Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }

    @Test
    public void shadowComplex015PointTest(){
        IPoint point = new Point(3632.38, 11191.86);
        double x = 19951.19;
        double y = 11216.34;
        double radius = 24.65;
        ICircle circle = new Circle(new Point(x, y), radius);
        double h = point.distanceTo(circle.getCenter());
        LOGGER.info("h = " + h);
        double dy = circle.getCenter().getY() - point.getY();
        double dx = circle.getCenter().getX() - point.getX();
        LOGGER.info("dx = " + dx);
        LOGGER.info("dy = " + dy);
        double sinAlpha =  dy / h;
        double sinDelta =  radius / h;
        LOGGER.info("sinAlpha = " + sinAlpha);
        LOGGER.info("sinDelta = " + sinDelta);
        double delta =  Math.asin(sinDelta);
        double alpha =  Math.asin(sinAlpha);
        IArc expectedArc = new Arc(alpha - delta ,   alpha + delta);
        IArc actualArc = MathHelper.shadow(circle, point);
        Assert.assertEquals(expectedArc, actualArc);
    }
}