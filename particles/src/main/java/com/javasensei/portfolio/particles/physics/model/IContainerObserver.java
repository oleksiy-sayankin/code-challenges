package com.javasensei.portfolio.particles.physics.model;

import com.javasensei.portfolio.particles.physics.state.ContainerState;

/**
 * @author asayankin
 */
public interface IContainerObserver {
    void update(ContainerState containerState);
}
