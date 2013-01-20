package com.javasensei.portfolio.balls;

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
            GraphicalState graphicalState = transformToGraphicalState(currentState);
            graphics.clearRect(graphicalState.x, graphicalState.y, graphicalState.size, graphicalState.size);
        }
        GraphicalState newViewState = transformToGraphicalState(newState);
        graphics.setColor(Color.BLACK);
        graphics.fillOval(newViewState.x, newViewState.y, newViewState.size, newViewState.size);
    }


	private static GraphicalState transformToGraphicalState(BallState ballState){
		int x = (int) ballState.x + Constants.Window.WIN_MARGIN;
		int y = (int) ballState.y + Constants.Window.WIN_HEADER + Constants.Window.WIN_MARGIN;
		int size = ballState.size;
		return new GraphicalState(x, y, size);
	}
	
}
