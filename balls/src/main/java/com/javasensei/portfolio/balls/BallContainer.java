package com.javasensei.portfolio.balls;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.graphics.CanvasPolygon;
import com.javasensei.portfolio.balls.math.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class BallContainer implements Runnable {
    private double width;
    private double height;
    private List<Ball> balls;
    private IPolygon sides;
    private final Graphics graphics;
    private boolean shouldStopThread = false;

    public BallContainer(Graphics aGraphics) {
        width = Constants.Common.CONTAINER_WIDTH;
        height = Constants.Common.CONTAINER_HEIGHT;
        graphics = aGraphics;
        balls = new ArrayList<Ball>();
        sides = initRectanglePolygon();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public List<ISegment> getSides() {
        return sides.toSegmentsClockwise();
    }

    public void addBall() {
        Ball newBall = new Ball(this);
        IBallObserver ballView = new BallView(graphics);
        newBall.registerObserver(ballView);
        balls.add(newBall);

    }

    private void moveAllBalls() {
        for (Ball ball : balls) {
            ball.move();
        }
    }

    @Override
    public void run() {
        while (!shouldStopThread) {
            moveAllBalls();
        }
    }

    public void stop(){
        shouldStopThread = true;
    }

    public void clear(){
        graphics.clearRect(Constants.Window.WIN_MARGIN, Constants.Window.HEADER + Constants.Window.WIN_MARGIN, (int) width, (int) height);
    }

    public void draw(){
        CanvasPolygon canvasPolygon = GraphicalHelper.transform(sides);
        graphics.drawPolygon(canvasPolygon.xPoints, canvasPolygon.yPoints, canvasPolygon.n);
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
