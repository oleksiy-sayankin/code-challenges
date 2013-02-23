package com.javasensei.portfolio.particles.physics.model;

import com.javasensei.portfolio.math.IVector;
import com.javasensei.portfolio.particles.physics.state.ParticleState;

/**
 * @author oleksiy sayankin
 */
public interface IParticleModel {
    void move();

    ParticleState state();

    void translateInDirection(IVector vector);

    double getX();

    double getY();
}
