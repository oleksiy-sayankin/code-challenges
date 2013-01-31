package com.javasensei.portfolio.balls.gui;

import com.javasensei.portfolio.balls.Constants;
import com.javasensei.portfolio.balls.physics.veiw.ContainerView;
import com.javasensei.portfolio.balls.physics.model.ContainerModel;

import javax.swing.*;

/**
 * @author asayankin
 */
public class BallPanel extends JPanel{
    private ContainerModel containerModel;
    private ContainerView containerView;


    public void startMovement() {
        new Thread(containerModel).start();
    }

    public void initBallContainer() {
        containerView = new ContainerView(getGraphics());
        containerModel = new ContainerModel(containerView);
        containerView.clearAll();
    }

    public void stopMovement() {
        if (containerModel != null) {
            containerModel.stop();
        }
    }

    public void refresh(){
        if(containerModel != null && containerView != null){
            containerModel.setWidthAndHeight(getWidth() -  Constants.Panel.MARGIN * 2, getHeight() - Constants.Panel.MARGIN * 2);
            containerView.setGraphics(getGraphics());
        }
    }

}
