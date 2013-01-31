package com.javasensei.portfolio.balls.physics.model;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.*;
import com.javasensei.portfolio.balls.math.*;
import com.javasensei.portfolio.balls.physics.state.BallState;

public class BallModel implements IBallModel {
    private IPoint coord;
    private IVector velocity;
    private final IContainerModel ballContainer;
    private int size = 4;


    public BallModel(IContainerModel aBallContainer) {
        ballContainer = aBallContainer;
        coord = new Point(ballContainer.getWidth() / 2, ballContainer.getHeight() / 2);
        velocity = randomVelocity();

    }

    @Override
    public void move() {
        ISegment segment = MathHelper.nearestSegmentInDirection(ballContainer.getSides().toSegmentsClockwise(), coord, velocity);
        double distance  =  MathHelper.distanceBetween(coord, segment);
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

    @Override
    public void translateInDirection(IVector vector) {
        coord.translateInDirection(vector);
    }

    @Override
    public double getX() {
        return coord.getX();
    }

    @Override
    public double getY() {
        return coord.getY();
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

    private static IPoint randomPointIn(ContainerModel aContainerModel) {
        final double MAX_X = aContainerModel.getWidth();
        final double MAX_Y = aContainerModel.getHeight();
        double x = Math.random() * MAX_X;
        double y = Math.random() * MAX_Y;
        return new Point(x, y);
    }
}
