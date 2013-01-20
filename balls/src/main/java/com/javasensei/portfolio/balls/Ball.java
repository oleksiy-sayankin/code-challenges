package com.javasensei.portfolio.balls;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.math.*;

import java.util.ArrayList;
import java.util.List;

public class Ball implements IBallSubject {
    private IPoint coord;
    private IVector velocity;
    private final BallContainer ballContainer;
    private int size = 4;
    private List<IBallObserver> ballObservers;

    public Ball(BallContainer aBallContainer) {
        ballContainer = aBallContainer;
        coord = randomPointIn(ballContainer);
        velocity = randomVelocity();
        ballObservers = new ArrayList<IBallObserver>();
    }


    @Override
    public void registerObserver(IBallObserver ballObserver) {
        ballObservers.add(ballObserver);
    }

    @Override
    public void removeObserver(IBallObserver ballObserver) {
        ballObservers.remove(ballObserver);
    }


    @Override
    public void notifyObservers() {
        for (IBallObserver ballObserver : ballObservers) {
            ballObserver.update(new BallState(coord, size));
        }
    }

    public void move() {
        ISegment segment = MathHelper.nearestSegmentInDirection(ballContainer.getSides(), coord, velocity);
        double distance  = MathHelper.distanceBetween(coord, segment);

        if (distance > velocity.module()) {
            coord.translateInDirection(velocity);
        } else {
            velocity = velocity.reflect(segment.toVector());
            move();
        }
        notifyObservers();
    }

    private static IVector randomVelocity() {
        double x = Math.random() * 2d - 1;
        double y = Math.random() * 2d - 1;
        double module = Math.random() * Constants.Common.MAX_VELOCITY;
        IVector velocity = new  Vector(x, y);
        velocity.normalize();
        velocity.mult(module);
        return  velocity;
    }

    private static IPoint randomPointIn(BallContainer aBallContainer) {
        final double MAX_X = aBallContainer.getWidth();
        final double MAX_Y = aBallContainer.getHeight();
        double x = Math.random() * MAX_X;
        double y = Math.random() * MAX_Y;
        return new Point(x, y);
    }
}
