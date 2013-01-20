package com.javasensei.portfolio.balls;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.math.IPoint;

public final class BallState {
	public final double x;
	public final double y;
	public final int size;

	public BallState(double aX, double aY, int aSize) {
		x = aX;
		y = aY;
		size = aSize;
	}

    public BallState(IPoint coord, int aSize) {
        x = coord.getX();
        y = coord.getY();
        size = aSize;
    }
}
