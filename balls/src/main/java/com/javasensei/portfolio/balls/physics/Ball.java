package com.javasensei.portfolio.balls.physics;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.*;
import com.javasensei.portfolio.balls.math.*;

import java.util.ArrayList;
import java.util.List;

public class Ball implements IBall{
    private IPoint coord;
    private IVector velocity;
    private final IContainer ballContainer;
    private int size = 4;


    public Ball(IContainer aBallContainer) {
        ballContainer = aBallContainer;
        coord = new Point(ballContainer.getWidth() / 2, ballContainer.getHeight() / 2);
        velocity = randomVelocity();

    }

    @Override
    public void move() {
        ISegment segment = MathHelper.nearestSegmentInDirection(ballContainer.getSides(), coord, velocity);
        double distance  = MathHelper.distanceBetween(coord, segment);

        if (distance > velocity.module()) {
            coord.translateInDirection(velocity);
        } else {
            velocity = velocity.reflectAgainst(segment.toLine());
            move();
        }
    }

    @Override
    public BallState state(){
        return new BallState(coord, size);
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

    private static IPoint randomPointIn(Container aContainer) {
        final double MAX_X = aContainer.getWidth();
        final double MAX_Y = aContainer.getHeight();
        double x = Math.random() * MAX_X;
        double y = Math.random() * MAX_Y;
        return new Point(x, y);
    }
}
