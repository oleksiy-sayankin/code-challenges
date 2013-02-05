package com.javasensei.portfolio.particles.math;

/**
 * @author asayankin
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rectangle extends Polygon implements IRectangle{
    public Rectangle(IPoint point1, IPoint point2){
        List<IPoint> rectanglePoints = new ArrayList<IPoint>();
        rectanglePoints.add(point1.copy());
        rectanglePoints.add(new Point(point1.getX(), point2.getY()));
        rectanglePoints.add(point2.copy());
        rectanglePoints.add(new Point(point2.getX(), point1.getY()));
        setPoints(Collections.unmodifiableList(rectanglePoints));
    }

    public Rectangle(double leftBound, double rightBound, double downBound, double upBound){
        List<IPoint> rectanglePoints = new ArrayList<IPoint>();
        rectanglePoints.add(new Point(leftBound, downBound));
        rectanglePoints.add(new Point(leftBound, upBound));
        rectanglePoints.add(new Point(rightBound, upBound));
        rectanglePoints.add(new Point(rightBound, downBound));
        setPoints(Collections.unmodifiableList(rectanglePoints));
    }

    @Override
    public IRectangle bounds(){
        return this;
    }

    @Override
    public IPoint bottomLeftPoint() {
        return points().get(0);
    }

    @Override
    public IPoint bottomRightPoint() {
        return points().get(3);
    }

    @Override
    public IPoint topLeftPoint() {
        return points().get(1);
    }

    @Override
    public IPoint topRightPoint() {
        return points().get(2);
    }
}
