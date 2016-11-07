package net.javacogito.particles.physics.model;

import net.javacogito.math.IPoint;
import net.javacogito.math.IVector;

/**
 * @author oleksiy sayankin
 */
public interface IParticleCollection<E extends IParticleModel> extends Iterable<E> {
  void stretch(IPoint stretchPoint, IVector direction);

  public void addParticles(int count);

  void moveAll();

}
