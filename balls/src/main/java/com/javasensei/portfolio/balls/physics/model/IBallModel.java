package com.javasensei.portfolio.balls.physics.model;

import com.javasensei.portfolio.balls.math.IVector;
import com.javasensei.portfolio.balls.physics.state.BallState;

/**
 * @author asayankin
 */
public interface IBallModel {
    void move();
    BallState state();
    void translateInDirection(IVector vector);
    double getX();
    double getY();
}
