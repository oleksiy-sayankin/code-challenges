package com.javasensei.portfolio.particles.graphics;

/**
 * @author asayankin
 */
public final class CanvasRectangle {
    public final CanvasPoint point;
    public final int width;
    public final int height;
    public CanvasRectangle(CanvasPoint canvasPoint, int aWidth, int aHeight){
        point = canvasPoint;
        width = aWidth;
        height = aHeight;
    }
}
