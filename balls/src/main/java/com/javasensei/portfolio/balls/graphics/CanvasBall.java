package com.javasensei.portfolio.balls.graphics;

import com.javasensei.portfolio.balls.graphics.CanvasPoint;

/**
 * @author oleksiy sayankin
 */

public final class CanvasBall {
    public final CanvasPoint point;
	public final int size;

	public CanvasBall(CanvasPoint canvasPoint, int aSize) {
        point = canvasPoint;
		size = aSize;
	}
}
