package com.javasensei.portfolio.particles.physics.model;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.particles.*;
import com.javasensei.portfolio.math.*;
import com.javasensei.portfolio.particles.physics.state.ParticleState;

public class ParticleModel implements IParticleModel {
    private IPoint coord;
    private IVector velocity;
    private final IContainerModel particleContainer;
    private int size = 4;


    public ParticleModel(IContainerModel aParticleContainer) {
        particleContainer = aParticleContainer;
        coord = new Point(particleContainer.getDimension().width / 2, particleContainer.getDimension().height / 2);
        velocity = randomVelocity();

    }

    @Override
    public void move() {
        ISegment segment = MathHelper.nearestSegmentInDirection(particleContainer.getSides().toSegmentsClockwise(), coord, velocity);
        double distance = segment.distanceTo(coord);
        if (distance > velocity.module()) {
            coord.translateInDirection(velocity);
        } else {
            velocity = velocity.reflectAgainst(segment.toLine());
            move();
        }
    }

    @Override
    public ParticleState state() {
        return new ParticleState(coord, size);
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
        IVector velocity = new Vector(x, y);
        velocity.normalize();
        velocity.mult(module);
        return velocity;
    }

}
