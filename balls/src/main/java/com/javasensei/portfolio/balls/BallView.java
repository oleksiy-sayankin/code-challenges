package com.javasensei.portfolio.balls;

/**
 * @author oleksiy sayankin
 */

import java.awt.Color;
import java.awt.Graphics;

public class BallView implements IBallObserver {
	private final Graphics graphics;
	private BallState currentState;
	private BallState newState;

	public BallView(Graphics aGraphics) {
		graphics = aGraphics;
	}


	@Override
	public void update(BallState ballState) {
		currentState = newState;
		newState = ballState;
		draw();
	}

    private void draw() {
        if (currentState != null) {
            GraphicalState graphicalState = GraphicalHelper.transform(currentState);
            graphics.clearRect(graphicalState.point.x, graphicalState.point.y, graphicalState.size, graphicalState.size);
        }
        GraphicalState newViewState = GraphicalHelper.transform(newState);
        graphics.setColor(Color.BLACK);
        graphics.fillOval(newViewState.point.x, newViewState.point.y, newViewState.size, newViewState.size);
    }


}
