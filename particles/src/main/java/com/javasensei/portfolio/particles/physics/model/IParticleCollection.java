package com.javasensei.portfolio.particles.physics.model;

import com.javasensei.portfolio.particles.math.IPoint;
import com.javasensei.portfolio.particles.math.IVector;

/**
 * @author asayankin
 */
public interface IParticleCollection<E extends IParticleModel> extends Iterable<E>{
    void stretch(IPoint stretchPoint, IVector direction);
    public void addParticles(int count);
    void moveAll();

}
