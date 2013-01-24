package com.javasensei.portfolio.balls.gui;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.ContainerView;
import com.javasensei.portfolio.balls.physics.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import static com.javasensei.portfolio.balls.Constants.*;

public class MainWindow extends JFrame {

    private JButton exitJButton;
    private JButton startJButton;
    private JButton stopJButton;
    private Container container;
    private final static MainWindow instance = new MainWindow();
    private boolean movementIsGoingOn = false;

    public static MainWindow getInstance() {
        return instance;
    }

    private MainWindow() {
        super();
        setSize(Window.WIDTH, Window.HEIGHT);
        getContentPane().setLayout(null);
        add(buildExitJButton());
        add(buildStartJButtonButton());
        add(buildStopJButtonButton());
        this.addComponentListener(new ResizeAdapter());
    }


    public void startMainWindow() {
        setVisible(true);
    }

    @Override
    public void validate(){
        super.validate();
    }

    private JButton buildExitJButton() {
        exitJButton = new JButton();
        exitJButton.setBounds(Window.MARGIN, Window.DIVIDER, Button.WIDTH, Button.HEIGHT);
        exitJButton.setText(Strings.EXIT);
        exitJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return exitJButton;
    }

    private JButton buildStartJButtonButton() {
        startJButton = new JButton();
        startJButton.setBounds(3 * Window.MARGIN + Button.WIDTH, Window.DIVIDER, Button.WIDTH, Button.HEIGHT);
        startJButton.setText(Strings.START);
        startJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!movementIsGoingOn){
                    startMovement();
                    stopJButton.setEnabled(true);
                    startJButton.setEnabled(false);
                    movementIsGoingOn = true;
                }
            }
        });
        return startJButton;
    }

    private JButton buildStopJButtonButton() {
        stopJButton = new JButton();
        stopJButton.setBounds(5 * Window.MARGIN + 2 * Button.WIDTH, Window.DIVIDER, Button.WIDTH, Button.HEIGHT);
        stopJButton.setText(Strings.STOP);
        stopJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(movementIsGoingOn){
                    stopMovement();
                    movementIsGoingOn = false;
                    stopJButton.setEnabled(false);
                    startJButton.setEnabled(true);
                }
            }
        });
        stopJButton.setEnabled(false);
        return stopJButton;
    }

    private void startMovement() {
        initBallContainer();
        new Thread(container).start();
    }

    private void initBallContainer() {
        container = new Container(new ContainerView(getGraphics()));
        for (int i = 0; i < 1000; i++) {
            container.addBall();
        }
    }

    private void stopMovement() {
        if (container != null) {
            container.stop();
        }
    }
}
