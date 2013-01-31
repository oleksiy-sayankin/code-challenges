package com.javasensei.portfolio.balls.physics.model;

import com.javasensei.portfolio.balls.math.IPoint;
import com.javasensei.portfolio.balls.math.IVector;

import java.util.Iterator;

/**
 * @author asayankin
 */
public interface IBallCollection<E extends IBallModel> extends Iterable<E>{
    void stretch(IPoint stretchPoint, IVector direction);
    public void addBalls(int count);
    void moveAll();

}
