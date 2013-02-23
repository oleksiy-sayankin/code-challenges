package com.javasensei.portfolio.particles.physics.state;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.math.IPoint;
import com.javasensei.portfolio.math.MathHelper;

public final class ParticleState {
    public final IPoint point;
    public final int size;

    public ParticleState(IPoint coord, int aSize) {
        point = MathHelper.unmodifiablePoint(coord);
        size = aSize;
    }

    @Override
    public String toString() {
        return point.toString();
    }
}
