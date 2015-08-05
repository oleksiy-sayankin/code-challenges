package net.javacogito.particles.physics.model;

import net.javacogito.math.MathDimension;
import net.javacogito.math.IPolygon;

/**
 * @author oleksiy sayankin
 */
public interface IContainerModel {
    void setDimension(MathDimension newDimension);
    MathDimension getDimension();
    IPolygon getSides();
    IParticleCollection<IParticleModel> getParticles();
}
