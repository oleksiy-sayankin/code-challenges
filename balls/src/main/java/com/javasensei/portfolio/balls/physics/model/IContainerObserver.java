package com.javasensei.portfolio.balls.physics.model;

import com.javasensei.portfolio.balls.physics.state.ContainerState;

/**
 * @author asayankin
 */
public interface IContainerObserver {
    void update(ContainerState containerState);
}
