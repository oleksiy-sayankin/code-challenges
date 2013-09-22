package com.javacogito.portfolio.particles.physics.model;

import com.javacogito.portfolio.math.IPoint;
import com.javacogito.portfolio.math.IVector;

/**
 * @author oleksiy sayankin
 */
public interface IParticleCollection<E extends IParticleModel> extends Iterable<E> {
    void stretch(IPoint stretchPoint, IVector direction);
    public void addParticles(int count);
    void moveAll();

}
