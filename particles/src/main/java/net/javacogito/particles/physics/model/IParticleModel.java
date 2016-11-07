package net.javacogito.particles.physics.model;

import net.javacogito.math.IVector;
import net.javacogito.particles.physics.state.ParticleState;

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
