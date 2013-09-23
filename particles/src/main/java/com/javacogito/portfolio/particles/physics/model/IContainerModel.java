package com.javacogito.portfolio.particles.physics.model;

import com.javacogito.portfolio.math.MathDimension;
import com.javacogito.portfolio.math.IPolygon;

/**
 * @author oleksiy sayankin
 */
public interface IContainerModel {
    void setDimension(MathDimension newDimension);
    MathDimension getDimension();
    IPolygon getSides();
    IParticleCollection<IParticleModel> getParticles();
}
