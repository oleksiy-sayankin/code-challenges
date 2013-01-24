package com.javasensei.portfolio.balls;

import com.javasensei.portfolio.balls.math.ISegment;
import com.javasensei.portfolio.balls.physics.ContainerState;

import java.util.List;

/**
 * @author asayankin
 */
public interface IContainerObserver {
    void update(ContainerState containerState);
}
