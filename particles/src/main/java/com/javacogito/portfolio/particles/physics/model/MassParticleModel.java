package com.javacogito.portfolio.particles.physics.model;

import com.javacogito.portfolio.particles.Constants;

/**
 * @author oleksiy sayankin
 */
public class MassParticleModel extends SimpleParticleModel {
    private double mass;

    public MassParticleModel(IContainerModel aParticleContainer) {
        super(aParticleContainer);
        mass = randomMass();
    }

    @Override
    public void move() {
       //TODO: implement gravity here
    }


    private static double randomMass(){
        return Math.random() * Constants.Common.MAX_MASS;
    }
}
