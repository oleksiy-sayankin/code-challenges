package com.javasensei.portfolio.particles.physics.model;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.particles.*;
import com.javasensei.portfolio.particles.math.*;
import com.javasensei.portfolio.particles.physics.state.ContainerState;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContainerModel implements Runnable, IContainerModel {

    private MathDimension currentDimension;
    private final IParticleCollection<IParticleModel> particles;
    private final IBoundModel bounds;
    private boolean shouldStopThread = false;
    private IContainerObserver containerObserver;
    private Lock ParticlesAndBounds = new ReentrantLock();

    public ContainerModel(IContainerObserver aContainerObserver, MathDimension dimension) {
        containerObserver = aContainerObserver;
        bounds = new BoundModel(dimension);
        currentDimension = dimension;
        particles = initParticles();
        containerObserver.update(new ContainerState(particles, bounds));
    }

    @Override
    public void setDimension(MathDimension newDimension) {
        assert (newDimension.width >= 0 && newDimension.height >= 0);
        if (newDimension.width == currentDimension.width && newDimension.width == currentDimension.height) {
            return;
        }
        double dX = newDimension.width - currentDimension.width;
        double dY = newDimension.height - currentDimension.height;
        IVector direction = MathHelper.unmodifiableVector(dX, dY);
        ParticlesAndBounds.lock();
        try {
            IPoint point = bounds.bounds().bottomRightPoint();
            particles.stretch(point, direction);
            bounds.stretch(point, direction);
        } finally {
            ParticlesAndBounds.unlock();
        }
        currentDimension = newDimension;
    }

    @Override
    public MathDimension getDimension() {
        return currentDimension;
    }


    @Override
    public IPolygon getSides() {
        return bounds.getSides();
    }

    @Override
    public void run() {
        while (!shouldStopThread) {
            ParticlesAndBounds.lock();
            try {
                particles.moveAll();
            } finally {
                ParticlesAndBounds.unlock();
            }
            containerObserver.update(new ContainerState(particles, bounds));
        }
    }

    public void stop() {
        shouldStopThread = true;
    }


    private IParticleCollection<IParticleModel> initParticles() {
        IParticleCollection<IParticleModel> result = new ParticleCollection<IParticleModel>(this);
        result.addParticles(Constants.Common.PARTICLES_AMOUNT);
        return result;
    }
}
