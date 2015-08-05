package net.javacogito.particles.graphics;

/**
 * @author oleksiy sayankin
 */
public final class CanvasRectangle {
    public final CanvasPoint point;
    public final int width;
    public final int height;

    public CanvasRectangle(CanvasPoint canvasPoint, int aWidth, int aHeight) {
        point = canvasPoint;
        width = aWidth;
        height = aHeight;
    }
}
