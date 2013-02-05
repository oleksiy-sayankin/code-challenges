package com.javasensei.portfolio.particles.physics.veiw;

import com.javasensei.portfolio.particles.physics.model.IContainerObserver;
import com.javasensei.portfolio.particles.graphics.CanvasParticle;
import com.javasensei.portfolio.particles.graphics.CanvasPolygon;
import com.javasensei.portfolio.particles.graphics.CanvasRectangle;
import com.javasensei.portfolio.particles.physics.state.ContainerState;

import java.awt.*;

/**
 * @author oleksiy sayankin
 */
public class ContainerView implements IContainerObserver {
    private Graphics graphics;
    private ContainerState newState;
    private ContainerState currentState;

    public ContainerView(Graphics aGraphics) {
        graphics = aGraphics;
    }

    public void setGraphics(Graphics newGraphics) {
        graphics = newGraphics;
    }

    @Override
    synchronized public void update(ContainerState containerState) {
        currentState = newState;
        newState = containerState;
        draw();
    }

    private void draw() {
        drawBounds();
        drawParticles();
    }

    private void drawBounds() {
        if (newState != null) {
            CanvasPolygon newCanvasPolygon = GraphicalHelper.transform(newState.bounds.sides);
            graphics.setColor(Color.BLACK);
            graphics.drawPolygon(newCanvasPolygon.xPoints, newCanvasPolygon.yPoints, newCanvasPolygon.n);
        }
    }

    private void drawParticles() {
        if (currentState != null && newState != null) {
            int size = currentState.particles.size();
            for (int i = 0; i <= size - 1; i++) {
                CanvasParticle newCanvasParticle = GraphicalHelper.transform(newState.particles.get(i));
                CanvasParticle currentCanvasParticle = GraphicalHelper.transform(currentState.particles.get(i));
                graphics.clearRect(currentCanvasParticle.point.x, currentCanvasParticle.point.y, currentCanvasParticle.size, currentCanvasParticle.size);
                graphics.setColor(Color.BLACK);
                graphics.fillOval(newCanvasParticle.point.x, newCanvasParticle.point.y, newCanvasParticle.size, newCanvasParticle.size);
            }
        }
    }

    public void clearAll() {
        CanvasRectangle cr = GraphicalHelper.transform(newState.bounds.sides.bounds());
        graphics.clearRect(cr.point.x, cr.point.y, cr.width, cr.height);
    }

}
