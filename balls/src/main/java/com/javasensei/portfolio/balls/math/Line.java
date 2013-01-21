package com.javasensei.portfolio.balls.math;

import com.javasensei.portfolio.balls.Constants;

public class Line implements ILine {
	private IPoint point1;
	private IPoint point2;

	public Line(IPoint aPoint1, IPoint aPoint2) {
		point1 = aPoint1;
		point2 = aPoint2;
	}

    public Line(IPoint point, IVector vector) {
        point1 = point;
        point2 = new Point(point.getX() + vector.getX(), point.getY() + vector.getY());
    }

    @Override
    public double distanceTo(IPoint point){
        return MathHelper.distanceBetween(point, this);
    }

    @Override
    public void translateInDirection(IVector vector) {
        point1.translateInDirection(vector);
        point2.translateInDirection(vector);
    }

    @Override
    public boolean has(IPoint point){
		return distanceTo(point) < Constants.Common.ERROR;
	}

    @Override
    public LineCoef coef(){
		double a = point1.getY() - point2.getY();
		double b = point2.getX() - point1.getX();
		double c = point1.getX() * point2.getY() - point2.getX() * point1.getY();		
		return new LineCoef(a, b, c);
	}
			


    public IVector toVector(){
		return new Vector(point2.getX() - point1.getX(), point2.getY() - point1.getY());
	}

}
