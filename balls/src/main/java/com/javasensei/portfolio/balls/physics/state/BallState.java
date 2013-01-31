package com.javasensei.portfolio.balls.physics.state;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.math.IPoint;
import com.javasensei.portfolio.balls.math.MathHelper;

public final class BallState {
    public final IPoint point;
	public final int size;

    public BallState(IPoint coord, int aSize) {
        point = MathHelper.unmodifiablePoint(coord);
        size = aSize;
    }
}
