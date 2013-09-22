package com.javacogito.portfolio.particles.graphics;

/**
 * @author oleksiy sayankin
 */

public final class CanvasParticle {
    public final CanvasPoint point;
    public final int size;

    public CanvasParticle(CanvasPoint canvasPoint, int aSize) {
        point = canvasPoint;
        size = aSize;
    }
}
