package com.javasensei.portfolio.particles.physics.state;

import com.javasensei.portfolio.particles.math.IPolygon;
import com.javasensei.portfolio.particles.math.MathHelper;

/**
 * @author oleksiy sayankin
 */
public final class BoundsState {
    public final IPolygon sides;

    public BoundsState(IPolygon polygon) {
        sides = MathHelper.unmodifiablePolygon(polygon);
    }

    @Override
    public String toString() {
        return sides.toString();
    }
}
