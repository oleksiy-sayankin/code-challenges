package com.javacogito.portfolio.particles.physics.model;

import com.javacogito.portfolio.math.IVector;
import com.javacogito.portfolio.particles.physics.state.ParticleState;

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
