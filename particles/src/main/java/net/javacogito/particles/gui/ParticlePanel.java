package net.javacogito.particles.gui;

import net.javacogito.particles.Constants;
import net.javacogito.math.MathDimension;
import net.javacogito.particles.physics.veiw.ContainerView;
import net.javacogito.particles.physics.model.ContainerModel;

import javax.swing.*;

/**
 * @author oleksiy sayankin
 */
public class ParticlePanel extends JPanel {
    private ContainerModel containerModel;
    private ContainerView containerView;

    public void startMovement() {
        new Thread(containerModel).start();
    }

    public void initParticleContainer() {
        containerView = new ContainerView(getGraphics());
        containerModel = new ContainerModel(containerView, currentPanelDimension());
        containerView.clearAll();
    }

    public void stopMovement() {
        if (containerModel != null) {
            containerModel.stop();
        }
    }

    public void refresh() {
        if (containerModel != null && containerView != null) {
            containerModel.setDimension(currentPanelDimension());
            containerView.setGraphics(getGraphics());
        }
    }

    private MathDimension currentPanelDimension() {
        double newWidth = getWidth() - Constants.Panel.MARGIN * 2;
        double newHeight = getHeight() - Constants.Panel.MARGIN * 2;
        newWidth = newWidth > Constants.Common.CONTAINER_MIN_WIDTH ? newWidth : Constants.Common.CONTAINER_MIN_WIDTH;
        newHeight = newHeight > Constants.Common.CONTAINER_MIN_HEIGHT ? newHeight : Constants.Common.CONTAINER_MIN_HEIGHT;
        return new MathDimension(newWidth, newHeight);
    }
}
