package com.javacogito.portfolio.particles.gui;

/**
 * @author oleksiy sayankin
 */

import com.javacogito.portfolio.particles.Constants;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import static com.javacogito.portfolio.particles.Constants.*;

public final class MainWindow extends JFrame {

    private JButton exitJButton;
    private JButton startJButton;
    private JButton stopJButton;
    private ParticlePanel particlePanel;
    private JPanel buttonPanel;
    private final static MainWindow INSTANCE = new MainWindow();
    private boolean movementIsGoingOn = false;

    public static MainWindow getInstance() {
        return INSTANCE;
    }

    private MainWindow() {
        super();
        setSize(Constants.Window.WIDTH, Constants.Window.HEIGHT);
        setLayout(new BorderLayout());
        add(buildJPanel(), BorderLayout.SOUTH);
        add(buildParticlePanel(), BorderLayout.CENTER);
    }


    public void startMainWindow() {
        setVisible(true);
    }

    @Override
    public void validate() {
        super.validate();
        particlePanel.refresh();
    }

    private JPanel buildJPanel() {
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(buildStartJButtonButton());
        buttonPanel.add(buildStopJButtonButton());
        buttonPanel.add(buildExitJButton());
        return buttonPanel;
    }

    private JButton buildExitJButton() {
        exitJButton = new JButton();
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
        startJButton.setText(Strings.START);
        startJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!movementIsGoingOn) {
                    particlePanel.initParticleContainer();
                    particlePanel.startMovement();
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
        stopJButton.setText(Strings.STOP);
        stopJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movementIsGoingOn) {
                    particlePanel.stopMovement();
                    movementIsGoingOn = false;
                    stopJButton.setEnabled(false);
                    startJButton.setEnabled(true);
                }
            }
        });
        stopJButton.setEnabled(false);
        return stopJButton;
    }

    private ParticlePanel buildParticlePanel() {
        particlePanel = new ParticlePanel();
        return particlePanel;
    }
}
