package net.javacogito.particles.physics.model;

import net.javacogito.math.IPoint;
import net.javacogito.math.IPolygon;
import net.javacogito.math.IVector;
import net.javacogito.math.Vector;
import net.javacogito.particles.ParticleFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class ParticleCollection<E extends IParticleModel> implements IParticleCollection<E> {
  private final List<IParticleModel> particles;
  private final IContainerModel containerModel;

  public ParticleCollection(IContainerModel aContainerModel) {
    particles = new ArrayList<IParticleModel>();
    containerModel = aContainerModel;
  }

  @Override
  public void moveAll() {
    for (IParticleModel particle : particles) {
      particle.move();
    }
  }

  @Override
  public void addParticles(int count) {
    for (int i = 0; i <= count - 1; i++) {
      particles.add(ParticleFactory.createParticleModel(containerModel));
    }
  }

  @Override
  public void stretch(IPoint stretchPoint, IVector direction) {
    IPolygon sides = containerModel.getSides();
    double rightBound = sides.rightBound();
    double leftBound = sides.leftBound();
    double upBound = sides.upBound();
    double downBound = sides.downBound();
    double dX = direction.getX();
    double dY = direction.getY();
    double stretchPointX = stretchPoint.getX();
    double stretchPointY = stretchPoint.getY();
    for (IParticleModel particle : particles) {
      double x = particle.getX();
      double y = particle.getY();
      double coefX = 1;
      double coefY = 1;
      if (stretchPointX >= rightBound) {
        coefX = (x - leftBound) / (stretchPointX - leftBound);
      }
      if (stretchPointX <= leftBound) {
        coefX = (rightBound - x) / (rightBound - stretchPointX);
      }
      if (stretchPointY >= upBound) {
        coefY = (y - downBound) / (stretchPointY - downBound);
      }
      if (stretchPointY <= downBound) {
        coefY = (upBound - y) / (upBound - stretchPointY);
      }
      IVector vector = new Vector(dX * coefX, dY * coefY);
      particle.translateInDirection(vector);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return (Iterator<E>) particles.iterator();
  }
}
