package net.javacogito.particles.physics.model;

import net.javacogito.particles.physics.state.ContainerState;

/**
 * @author oleksiy sayankin
 */
public interface IContainerObserver {
    void update(ContainerState containerState);
}
