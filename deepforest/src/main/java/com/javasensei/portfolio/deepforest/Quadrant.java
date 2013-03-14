package com.javasensei.portfolio.deepforest;

/**
 * @author oleksiy sayankin
 */
public enum Quadrant {
    FIRST, SECOND, THIRD, FORTH, FIFTH, SIXTH, SEVENTH, EIGHT;
    public static boolean isNormal(Quadrant quadrant){
        return quadrant == Quadrant.FIRST || quadrant == Quadrant.SECOND || quadrant == Quadrant.THIRD || quadrant == Quadrant.FORTH;
    }
    public static Quadrant getByOrdinal(int ordinal){
        return Quadrant.values()[ordinal];
    }

    public static Quadrant getInner(Quadrant startQuadrant, Quadrant endQuadrant){
       return Quadrant.getByOrdinal((int) Math.round((startQuadrant.ordinal() + endQuadrant.ordinal()) / 2d));
    }
}
