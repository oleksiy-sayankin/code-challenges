package com.javasensei.portfolio.balls.physics.model;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.*;
import com.javasensei.portfolio.balls.math.*;
import com.javasensei.portfolio.balls.physics.state.ContainerState;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContainerModel implements Runnable, IContainerModel {
    private double width;
    private double height;
    private final IBallCollection<IBallModel> balls;
    private final IBoundModel bounds;
    private boolean shouldStopThread = false;
    private IContainerObserver containerObserver;
    private Lock ballsAndBoundsLock = new ReentrantLock();

    public ContainerModel(IContainerObserver aContainerObserver) {
        containerObserver = aContainerObserver;
        bounds = new BoundModel();
        width = bounds.width();
        height = bounds.height();
        balls = initBalls();
        containerObserver.update(new ContainerState(balls, bounds));
    }

    @Override
    public void setWidthAndHeight(double newWidth, double newHeight) {
        assert (newWidth >= 0 && newHeight >= 0);
        if (newWidth == width && newHeight == height) {
            return;
        }
        double dX = newWidth - width;
        double dY = newHeight - height;
        IVector direction = new Vector(dX, dY);
        ballsAndBoundsLock.lock();
        try {
            IPoint point = bounds.bounds().bottomRightPoint();
            balls.stretch(point, direction);
            bounds.stretch(point, direction);

        } finally {
            ballsAndBoundsLock.unlock();
        }
        width = newWidth;
        height = newHeight;
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
    public IPolygon getSides() {
        return bounds.getSides();
    }

    @Override
    public void run() {
        while (!shouldStopThread) {
            ballsAndBoundsLock.lock();
            try {
                balls.moveAll();
            } finally {
                ballsAndBoundsLock.unlock();
            }
            containerObserver.update(new ContainerState(balls, bounds));
        }
    }

    public void stop() {
        shouldStopThread = true;
    }


    private IBallCollection<IBallModel> initBalls() {
        IBallCollection<IBallModel> result = new BallCollection<IBallModel>(this);
        result.addBalls(Constants.Common.BALLS_AMOUNT);
        return result;
    }
}
