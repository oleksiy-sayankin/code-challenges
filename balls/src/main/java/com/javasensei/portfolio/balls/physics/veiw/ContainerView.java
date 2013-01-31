package com.javasensei.portfolio.balls.physics.veiw;

import com.javasensei.portfolio.balls.physics.model.IContainerObserver;
import com.javasensei.portfolio.balls.graphics.CanvasBall;
import com.javasensei.portfolio.balls.graphics.CanvasPolygon;
import com.javasensei.portfolio.balls.graphics.CanvasRectangle;
import com.javasensei.portfolio.balls.physics.state.ContainerState;

import java.awt.*;

/**
 * @author asayankin
 */
public class ContainerView implements IContainerObserver {
    private Graphics graphics;
    private ContainerState newState;
    private ContainerState currentState;

    public ContainerView(Graphics aGraphics) {
        graphics = aGraphics;
    }

    public void setGraphics(Graphics newGraphics){
       graphics = newGraphics;
    }

    @Override
    synchronized public void update(ContainerState containerState) {
        currentState = newState;
        newState = containerState;
        draw();
    }

    private void draw() {
        drawPolygon();
        drawBalls();
    }

    private void drawPolygon() {
//        if (currentState != null) {
//            CanvasPolygon currentCanvasPolygon = GraphicalHelper.transform(currentState.polygon);
//            graphics.setColor(SystemColor.activeCaptionBorder);
//            graphics.drawPolygon(currentCanvasPolygon.xPoints, currentCanvasPolygon.yPoints, currentCanvasPolygon.n);
//        }
        if(newState != null){
            CanvasPolygon newCanvasPolygon = GraphicalHelper.transform(newState.polygon);
            graphics.setColor(Color.BLACK);
            graphics.drawPolygon(newCanvasPolygon.xPoints, newCanvasPolygon.yPoints, newCanvasPolygon.n);
//            System.out.println(newCanvasPolygon);
//            graphics.fill3DRect(getWidth() - 50, getHeight() - 50, 50, 50, true);

        }
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
//        graphics.fillOval(40, 40, 600, 600);
    }

    public void clearAll() {
        CanvasRectangle cr = GraphicalHelper.transform(newState.polygon.bounds());
        graphics.clearRect(cr.point.x, cr.point.y, cr.width, cr.height);
//        graphics.fillOval(40, 40, 60, 60);
    }

}
