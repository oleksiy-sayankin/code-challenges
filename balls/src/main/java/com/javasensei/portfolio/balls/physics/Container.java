package com.javasensei.portfolio.balls.physics;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.*;
import com.javasensei.portfolio.balls.graphics.CanvasPolygon;
import com.javasensei.portfolio.balls.graphics.CanvasRectangle;
import com.javasensei.portfolio.balls.math.*;
import com.javasensei.portfolio.balls.physics.Ball;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Container implements Runnable, IContainer {
    private double width;
    private double height;
    private List<IBall> balls;
    private IPolygon sides;
    private boolean shouldStopThread = false;
    private IContainerObserver containerObserver;

    public Container(IContainerObserver aContainerObserver) {
        containerObserver = aContainerObserver;
        sides = initRectanglePolygon();
        width = sides.width();
        height = sides.height();
        balls = new ArrayList<IBall>();
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public List<ISegment> getSides() {
        return sides.toSegmentsClockwise();
    }

    public void addBall() {
        balls.add(new Ball(this));
    }

    private void moveAllBalls() {
        for (IBall ball : balls) {
            ball.move();
        }
    }

    @Override
    public void run() {
        while (!shouldStopThread) {
            moveAllBalls();
            containerObserver.update(new ContainerState(balls, sides));
        }
    }

    public void stop(){
        shouldStopThread = true;
    }

    private static IPolygon initRectanglePolygon(){
        IPolygon rect = new Polygon();
        rect.addPoint(new Point(0, 0));
        rect.addPoint(new Point(0, Constants.Common.CONTAINER_HEIGHT));
        rect.addPoint(new Point(Constants.Common.CONTAINER_WIDTH, Constants.Common.CONTAINER_HEIGHT));
        rect.addPoint(new Point(Constants.Common.CONTAINER_WIDTH, 0));
        return rect;
    }


}
