package com.javacogito.portfolio.particles.physics.state;

/**
 * @author oleksiy sayankin
 */

import com.javacogito.portfolio.math.IPoint;
import com.javacogito.portfolio.math.Primitives;

public final class ParticleState {
    public final IPoint point;
    public final int size;

    public ParticleState(IPoint coord, int aSize) {
        point = Primitives.unmodifiablePoint(coord);
        size = aSize;
    }

    @Override
    public String toString() {
        return point.toString();
    }
}
