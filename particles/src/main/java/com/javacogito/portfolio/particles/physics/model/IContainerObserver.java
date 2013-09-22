package com.javacogito.portfolio.particles.physics.model;

import com.javacogito.portfolio.particles.physics.state.ContainerState;

/**
 * @author oleksiy sayankin
 */
public interface IContainerObserver {
    void update(ContainerState containerState);
}
