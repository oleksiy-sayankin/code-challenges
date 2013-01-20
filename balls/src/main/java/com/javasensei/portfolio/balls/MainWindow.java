package com.javasensei.portfolio.balls;

/**
 * @author oleksiy sayankin
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import static com.javasensei.portfolio.balls.Constants.*;

public class MainWindow extends JFrame {
		
	private JButton exitJButton;
	private JButton startJButton;
	private BallContainer ballContainer;
	private final static MainWindow instance = new MainWindow();

    public static MainWindow getInstance(){
        return instance;
    }

	private MainWindow() {
		super();
		setSize(Window.WIN_WIDTH, Window.WIN_HEGHT);
		getContentPane().setLayout(null);
		add(buildExitJButton());
		add(buildStartJButtonButton());		
	}	


	
	public void startMainWindow(){
		setVisible(true);
        ballContainer = new BallContainer(Window.WIN_WIDTH - Window.WIN_MARGIN * 2, Window.DIVIDER - Window.WIN_MARGIN * 2 - Window.WIN_HEADER, getGraphics());
        for (int i = 0; i < 1000; i++){
            ballContainer.addBall();
        }
	}

	private JButton buildExitJButton() {  
		exitJButton = new JButton();
		exitJButton.setBounds(Window.MARGIN, Window.DIVIDER, Window.BUTTON_WIDTH, Window.BUTTON_HEIGHT);
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
		startJButton.setBounds(3 * Window.MARGIN + Window.BUTTON_WIDTH , Window.DIVIDER, Window.BUTTON_WIDTH, Window.BUTTON_HEIGHT);
		startJButton.setText(Strings.START);
		startJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMovement();
            }
        });
        return startJButton;
	   }	


    private void startMovement(){
         new Thread(ballContainer).start();
    }
}
