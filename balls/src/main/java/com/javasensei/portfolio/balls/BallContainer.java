package com.javasensei.portfolio.balls;

import com.javasensei.portfolio.balls.math.ISegment;
import com.javasensei.portfolio.balls.math.Point;
import com.javasensei.portfolio.balls.math.Segment;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class BallContainer implements Runnable{
	private double width;
	private double height;
	private List<Ball> balls;
    private List<ISegment> sides;
	private final Graphics graphics;

	public BallContainer(double aWidth, double aHeight, Graphics aGraphics) {
		width = aWidth;
		height = aHeight;
		graphics = aGraphics;
		balls = new ArrayList<Ball>();
        sides = new ArrayList<ISegment>();
        sides.add(new Segment(new Point(0, 0), new Point(width, 0)));
        sides.add(new Segment(new Point(width,0), new Point(width, height)));
        sides.add(new Segment(new Point(width, height), new Point(0, height)));
        sides.add(new Segment(new Point(0, height), new Point(0,0)));
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public List<ISegment> getSides(){
		return sides;
	}
	public void addBall(){
		Ball newBall = new Ball(this);
		IBallObserver ballView = new BallView(graphics);
		newBall.registerObserver(ballView);
		balls.add(newBall);
		
	}	
	
	private void moveAllBalls(){
		for(Ball ball : balls){
			ball.move();
		}
	}

    @Override
    public void run() {
        for (int i = 0; i <= Constants.Common.MAX_STEPS - 1; i++){
            moveAllBalls();
        }
    }
}
