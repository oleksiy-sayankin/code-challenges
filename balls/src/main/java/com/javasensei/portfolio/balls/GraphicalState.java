package com.javasensei.portfolio.balls;

import com.javasensei.portfolio.balls.graphics.CanvasPoint;

/**
 * @author oleksiy sayankin
 */

public final class GraphicalState {
    public final CanvasPoint point;
	public final int size;

	public GraphicalState(CanvasPoint canvasPoint, int aSize) {
        point = canvasPoint;
		size = aSize;
	}
}
