package com.javasensei.portfolio.balls.math;

import com.javasensei.portfolio.balls.Constants;

public class Point implements  IPoint {
	private double x;
	private double y;

	public Point(double aX, double aY){
		x = aX;
		y = aY;		
	}

    public Point(IPoint point){
        x = point.getX();
        y = point.getY();
    }


    @Override
	public double getX(){
		return x;
	}

    @Override
	public double getY(){
		return y;
	}

    @Override
	public double distanceTo(IPoint p){
		return MathHelper.distanceBetween(this, p);
	} 
	
	public void setXY(double aX, double aY){
		x = aX;
		y = aY;
	}

    @Override
	public String toString(){
		return "[" + x + ", " + y + "]";
	}
	
	@Override
	public boolean equals(Object o){
        if(o instanceof Point){
            Point point = (Point)o;
            return has(point);
        }
        return false;
	}
	
	@Override
	public int hashCode(){
		int ix = (int)Math.round(x);
		int iy = (int)Math.round(y);
		return ix * ix + iy;
	}

    @Override
    public boolean has(IPoint point) {
        return distanceTo(point) < Constants.Common.ERROR;
    }

    @Override
    public IPoint copy(){
        return new Point(x, y);
    }

    @Override
    public void translateInDirection(IVector vector){
        x += vector.getX();
        y += vector.getY();
    }

    @Override
    public void scale(double coef) {
        x *= coef;
        y *= coef;
    }
}
