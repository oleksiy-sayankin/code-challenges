package com.javasensei.portfolio.balls.gui;

/**
 * @author oleksiy sayankin
 */

import com.javasensei.portfolio.balls.Constants;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import static com.javasensei.portfolio.balls.Constants.*;

public class MainWindow extends JFrame {

    private JButton exitJButton;
    private JButton startJButton;
    private JButton stopJButton;
    private BallPanel ballPanel;
    private JPanel buttonPanel;
    private final static MainWindow instance = new MainWindow();
    private boolean movementIsGoingOn = false;

    public static MainWindow getInstance() {
        return instance;
    }

    private MainWindow() {
        super();
        setSize(Constants.Window.WIDTH, Constants.Window.HEIGHT);
        setLayout(new BorderLayout());
        add(buildJPanel(), BorderLayout.SOUTH);
        add(buildBallPanel(), BorderLayout.CENTER);
    }


    public void startMainWindow() {
        setVisible(true);
    }

    @Override
    public void validate(){
        super.validate();

//        for(long i = 1; i <= 999999; i++){
//            Math.random();
//        }
//        try{
//            //do what you want to do before sleeping
//            Thread.currentThread().sleep(100);//sleep for 1000 ms
//            //do what you want to do after sleeptig
//        }
//         catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        ballPanel.refresh();
    }

    private JPanel buildJPanel(){
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(buildStartJButtonButton());
        buttonPanel.add(buildStopJButtonButton());
        buttonPanel.add(buildExitJButton());
        return buttonPanel;
    }

    private JButton buildExitJButton() {
        exitJButton = new JButton();
        exitJButton.setBounds(Constants.Window.MARGIN, Constants.Window.DIVIDER, Constants.Button.WIDTH, Constants.Button.HEIGHT);
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
        startJButton.setBounds(3 * Constants.Window.MARGIN + Constants.Button.WIDTH, Constants.Window.DIVIDER, Constants.Button.WIDTH, Constants.Button.HEIGHT);
        startJButton.setText(Strings.START);
        startJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!movementIsGoingOn){
                    ballPanel.initBallContainer();
                    ballPanel.startMovement();
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
        stopJButton.setBounds(5 * Constants.Window.MARGIN + 2 * Constants.Button.WIDTH, Constants.Window.DIVIDER, Constants.Button.WIDTH, Constants.Button.HEIGHT);
        stopJButton.setText(Strings.STOP);
        stopJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(movementIsGoingOn){
                    ballPanel.stopMovement();
                    movementIsGoingOn = false;
                    stopJButton.setEnabled(false);
                    startJButton.setEnabled(true);
                }
            }
        });
        stopJButton.setEnabled(false);
        return stopJButton;
    }

    private BallPanel buildBallPanel(){
        ballPanel = new BallPanel();
        return ballPanel;
    }
}
