package com.javasensei.portfolio.particles.math;

public final class LineCoef {
	public final double a;
	public final double b;
	public final double c;
	public LineCoef(double aA, double aB, double aC){
		a = aA;
		b = aB;
		c = aC;
	}
	@Override
	public String toString(){
		return "[" + a + ", " + b + ", " + c + "]";
	}
}
