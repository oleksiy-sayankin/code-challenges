package com.javacogito.portfolio.particles.physics.state;

import com.javacogito.portfolio.particles.physics.model.IParticleCollection;
import com.javacogito.portfolio.particles.physics.model.IParticleModel;
import com.javacogito.portfolio.particles.physics.model.IBoundModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public final class ContainerState {
    public final List<ParticleState> particles;
    public final BoundsState bounds;

    public ContainerState(IParticleCollection<IParticleModel> aParticles, IBoundModel boundModel) {
        List<ParticleState> bs = new ArrayList<ParticleState>();
        for (IParticleModel particle : aParticles) {
            bs.add(particle.state());
        }
        particles = Collections.unmodifiableList(bs);
        bounds = boundModel.state();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ bounds = ");
        sb.append(bounds);
        sb.append(", particles = ");
        boolean first = true;
        for (ParticleState particleState : particles) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(particleState);
        }
        sb.append(" ]");
        return sb.toString();
    }
}
