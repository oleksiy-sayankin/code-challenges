package com.javasensei.portfolio.particles.math;

import com.javasensei.portfolio.particles.Constants;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author oleksiy sayankin
 */
public class RectangleTest {

    @Test
    public void pointsTest() {
        IRectangle rectangle = new Rectangle(new Point(2, 2), new Point(6, 4));
        IPoint EXPECTED_BOTTOM_LEFT_POINT = new Point(2, 2);
        IPoint EXPECTED_BOTTOM_RIGHT_POINT = new Point(6, 2);
        IPoint EXPECTED_TOP_LEFT_POINT = new Point(2, 4);
        IPoint EXPECTED_TOP_RIGHT_POINT = new Point(6, 4);
        IPoint actualBottomLeftPoint = rectangle.bottomLeftPoint();
        IPoint actualBottomRightPoint = rectangle.bottomRightPoint();
        IPoint actualTopLeftPoint = rectangle.topLeftPoint();
        IPoint actualTopRightPoint = rectangle.topRightPoint();
        assertEquals(EXPECTED_BOTTOM_LEFT_POINT, actualBottomLeftPoint);
        assertEquals(EXPECTED_BOTTOM_RIGHT_POINT, actualBottomRightPoint);
        assertEquals(EXPECTED_TOP_LEFT_POINT, actualTopLeftPoint);
        assertEquals(EXPECTED_TOP_RIGHT_POINT, actualTopRightPoint);
    }

    @Test
    public void areaTest() {
        IRectangle rectangle = new Rectangle(new Point(2, 2), new Point(6, 4));
        final Double EXPECTED_AREA = 8d;
        Double actualArea = rectangle.area();
        assertEquals(EXPECTED_AREA, actualArea);
    }

    @Test
    public void areaAfterStretchingIncTest() {
        IRectangle rectangle = new Rectangle(new Point(2, 2), new Point(6, 4));
        IVector direction = new Vector(1, 1);
        rectangle.stretch(rectangle.topRightPoint(), direction);
        final Double EXPECTED_AREA = 15d;
        Double actualArea = rectangle.area();
        assertEquals(EXPECTED_AREA, actualArea);
    }

    @Test
    public void areaAfterStretchingDecTest() {
        IRectangle rectangle = new Rectangle(new Point(2, 2), new Point(6, 4));
        IVector direction = new Vector(-1, -1);
        rectangle.stretch(rectangle.topRightPoint(), direction);
        final Double EXPECTED_AREA = 3d;
        Double actualArea = rectangle.area();
        assertEquals(EXPECTED_AREA, actualArea);
    }

    @Test
    public void rectangleConstructorTest() {
        IRectangle rectangleA = new Rectangle(new Point(1, 1), new Point(10, 7));
        IRectangle rectangleB = new Rectangle(1, 10, 1, 7);
        assertEquals(rectangleA.area(), rectangleB.area(), Constants.Common.ERROR);
        assertEquals(rectangleA, rectangleB);
    }

    @Test
    public void rectangleEqualsTrueTest() {
        IRectangle rectangleA = new Rectangle(new Point(3, 4), new Point(11, 8));
        IRectangle rectangleB = new Rectangle(3, 11, 4, 8);
        assertEquals(rectangleA.area(), rectangleB.area(), Constants.Common.ERROR);
        assertEquals(rectangleA, rectangleB);
    }

    @Test
    public void rectangleEqualsFalseTest() {
        IRectangle rectangleA = new Rectangle(new Point(3, 4), new Point(11, 8));
        IRectangle rectangleB = new Rectangle(4, 12, 4, 8);
        IRectangle rectangleC = new Rectangle(3, 11, 0, 4);
        assertEquals(rectangleA.area(), rectangleB.area(), Constants.Common.ERROR);
        assertEquals(rectangleA.area(), rectangleC.area(), Constants.Common.ERROR);
        assertEquals(rectangleB.area(), rectangleC.area(), Constants.Common.ERROR);
        assertFalse(rectangleA.equals(rectangleB));
        assertFalse(rectangleA.equals(rectangleC));
        assertFalse(rectangleB.equals(rectangleC));
    }

    @Test
    public void stretchFromBottomLeftPointGreaterTest() {
        IRectangle actualAfterStretch = new Rectangle(new Point(3, 4), new Point(11, 8));
        actualAfterStretch.stretch(actualAfterStretch.bottomLeftPoint(), new Vector(-2, -3));
        IRectangle EXPECTED_AFTER_STRETCH = new Rectangle(1, 11, 1, 8);
        assertEquals(EXPECTED_AFTER_STRETCH, actualAfterStretch);
    }

    @Test
    public void stretchFromBottomLeftPointSmallerTest() {
        IRectangle actualAfterStretch = new Rectangle(new Point(3, 4), new Point(11, 8));
        actualAfterStretch.stretch(actualAfterStretch.bottomLeftPoint(), new Vector(1, 1));
        IRectangle EXPECTED_AFTER_STRETCH = new Rectangle(4, 11, 5, 8);
        assertEquals(EXPECTED_AFTER_STRETCH, actualAfterStretch);
    }

    @Test
    public void stretchFromBottomRightPointGreaterTest() {
        IRectangle actualAfterStretch = new Rectangle(new Point(3, 4), new Point(11, 8));
        actualAfterStretch.stretch(actualAfterStretch.bottomRightPoint(), new Vector(2, -3));
        IRectangle EXPECTED_AFTER_STRETCH = new Rectangle(3, 13, 1, 8);
        assertEquals(EXPECTED_AFTER_STRETCH, actualAfterStretch);
    }

    @Test
    public void stretchFromBottomRightPointSmallerTest() {
        IRectangle actualAfterStretch = new Rectangle(new Point(3, 4), new Point(11, 8));
        actualAfterStretch.stretch(actualAfterStretch.bottomRightPoint(), new Vector(-2, 3));
        IRectangle EXPECTED_AFTER_STRETCH = new Rectangle(3, 9, 7, 8);
        assertEquals(EXPECTED_AFTER_STRETCH, actualAfterStretch);
    }

    @Test
    public void stretchFromTopLeftPointGreaterTest() {
        IRectangle actualAfterStretch = new Rectangle(new Point(3, 4), new Point(11, 8));
        actualAfterStretch.stretch(actualAfterStretch.topLeftPoint(), new Vector(-2, 3));
        IRectangle EXPECTED_AFTER_STRETCH = new Rectangle(1, 11, 4, 11);
        assertEquals(EXPECTED_AFTER_STRETCH, actualAfterStretch);
    }

    @Test
    public void stretchFromTopLeftPointSmallerTest() {
        IRectangle actualAfterStretch = new Rectangle(new Point(3, 4), new Point(11, 8));
        actualAfterStretch.stretch(actualAfterStretch.topLeftPoint(), new Vector(2, -3));
        IRectangle EXPECTED_AFTER_STRETCH = new Rectangle(5, 11, 4, 5);
        assertEquals(EXPECTED_AFTER_STRETCH, actualAfterStretch);
    }

    @Test
    public void stretchFromTopRighPointGreaterTest() {
        IRectangle actualAfterStretch = new Rectangle(new Point(3, 4), new Point(11, 8));
        actualAfterStretch.stretch(actualAfterStretch.topRightPoint(), new Vector(2, 3));
        IRectangle EXPECTED_AFTER_STRETCH = new Rectangle(3, 13, 4, 11);
        assertEquals(EXPECTED_AFTER_STRETCH, actualAfterStretch);
    }

    @Test
    public void stretchFromTopRighPointSmallerTest() {
        IRectangle actualAfterStretch = new Rectangle(new Point(3, 4), new Point(11, 8));
        actualAfterStretch.stretch(actualAfterStretch.topRightPoint(), new Vector(-2, -3));
        IRectangle EXPECTED_AFTER_STRETCH = new Rectangle(3, 9, 4, 5);
        assertEquals(EXPECTED_AFTER_STRETCH, actualAfterStretch);
    }

}
