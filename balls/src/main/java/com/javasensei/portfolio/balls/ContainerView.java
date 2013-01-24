package com.javasensei.portfolio.balls;

import com.javasensei.portfolio.balls.graphics.CanvasBall;
import com.javasensei.portfolio.balls.graphics.CanvasPolygon;
import com.javasensei.portfolio.balls.graphics.CanvasRectangle;
import com.javasensei.portfolio.balls.physics.ContainerState;

import java.awt.*;

/**
 * @author asayankin
 */
public class ContainerView implements IContainerObserver {
    private final Graphics graphics;
    private ContainerState newState;
    private ContainerState currentState;

    public ContainerView(Graphics aGraphics) {
        graphics = aGraphics;
    }

    @Override
    public void update(ContainerState containerState) {
        currentState = newState;
        newState = containerState;
        draw();
    }

    private void draw() {
        drawPolygon();
        drawBalls();
    }

    private void drawPolygon() {
        CanvasPolygon canvasPolygon = GraphicalHelper.transform(newState.polygon);
        graphics.drawPolygon(canvasPolygon.xPoints, canvasPolygon.yPoints, canvasPolygon.n);
    }

    private void drawBalls() {
        if (currentState != null && newState != null) {
            int size = currentState.balls.size();
            for (int i = 0; i <= size - 1; i++) {
                CanvasBall newCanvasBall = GraphicalHelper.transform(newState.balls.get(i));
                CanvasBall currentCanvasBall = GraphicalHelper.transform(currentState.balls.get(i));
                graphics.clearRect(currentCanvasBall.point.x, currentCanvasBall.point.y, currentCanvasBall.size, currentCanvasBall.size);
                graphics.setColor(Color.BLACK);
                graphics.fillOval(newCanvasBall.point.x, newCanvasBall.point.y, newCanvasBall.size, newCanvasBall.size);
            }
        }
    }

    private void clearAll() {
        CanvasRectangle cr = GraphicalHelper.transform(newState.polygon.bounds());
        graphics.clearRect(cr.point.x, cr.point.y, cr.width, cr.height);

    }

}
