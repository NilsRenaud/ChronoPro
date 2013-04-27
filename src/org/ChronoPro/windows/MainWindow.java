package org.ChronoPro.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import org.ChronoPro.config.DisplayConstants;
import org.ChronoPro.core.Chrono;
import org.ChronoPro.core.ChronoException;
import org.ChronoPro.util.Constants;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -1633350608145279239L;
	
	private final JMenuBar menuBar = new JMenuBar();
	private final JPanel buttonPanel = new JPanel();
	private final JPanel messagePanel = new JPanel();
	private final JPanel chronoPanel = new JPanel();
	private final JPanel mainPanel = new JPanel();
	
	private final JButton startButton = new JButton("Start");
	private final JButton stopButton = new JButton("Stop");
	private final JButton pauseButton = new JButton("Pause");
	
	private final JLabel errorMessage = new JLabel();
	private final JLabel chronoLabel = new JLabel();
	
	private final Timer timer;
	private final Timer timerMessage;
	
	private final Chrono chrono = new Chrono();
	
	
	public MainWindow() {	
		
		
		createWindowProterties();
		createMenuBar();
		
		createChronoField();
		
		createButtonField();
		
		createErrorMessageField();
		
		this.getContentPane().add(mainPanel);
		
		this.setVisible(true);
		
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshChronoLabel();
			}
		});
		
		timerMessage = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				errorMessage.setText("");
				timerMessage.stop();
			}
		});
		
	}
	
	private void createWindowProterties(){
		this.setTitle(Constants.MAIN_WINDOW_TITLE);
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	}
	
	private void createMenuBar(){
		JMenu menu1 = new JMenu();
	
		menu1.setText(Constants.MENU_BAR_TITLE);
		JMenuItem menuItem1 = new JMenuItem(Constants.MENU_BAR_OPT);
		menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("BLA BLA");
				
			}
		});
		menu1.add(menuItem1);
		
		menuBar.add(menu1);
		this.setJMenuBar(menuBar);
	}
	
	private void createChronoField(){
		
		chronoLabel.setForeground(new Color(255,255,255));
		chronoLabel.setFont(DisplayConstants.fontChrono);
		
		chronoPanel.setLayout(new GridBagLayout());
		chronoPanel.add(chronoLabel);
		chronoPanel.setBackground(new Color(100, 100, 100));
		chronoPanel.setMinimumSize(new Dimension(300, 200));
		chronoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		chronoPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		Dimension dim = new Dimension(Integer.MAX_VALUE, 200);
	    chronoPanel.setMaximumSize(dim);
		mainPanel.add(chronoPanel);
	}
	
	private void createButtonField(){
		
		setActionToButtons();
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(pauseButton);
		
		mainPanel.add(buttonPanel);
	}
	
	private void setActionToButtons(){
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					chrono.start();
					timer.start();
					setMessage("Chrono started ! ");
					pauseButton.requestFocusInWindow();
				} catch (ChronoException e1) {
					setError(e1.getMessage());
				}
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					chrono.stop();
					timer.stop();
					refreshChronoLabel();
				} catch (ChronoException e1) {
					setError(e1.getMessage());
				}
			}
		});
		
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					chrono.pause();
					timer.stop();
					refreshChronoLabel();
				} catch (ChronoException e1) {
					try {
						chrono.resume();
						timer.start();
					} catch (ChronoException e2) {
						setError(e1.getMessage());
					}
				}
			}
		});
		
		pauseButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				pauseButton.doClick();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	
	private void createErrorMessageField(){
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.LINE_AXIS));
		messagePanel.add(errorMessage);
		
		mainPanel.add(messagePanel);
	}
	
	private void setMessage(String message){
		errorMessage.setForeground(new Color(0,255,0));
		errorMessage.setText(message);
		timerMessage.start();
	}
	
	private void setError(String message){
		errorMessage.setForeground(new Color(255,0,0));
		errorMessage.setText(message);
		timerMessage.start();
	}
	
	private void refreshChronoLabel(){
		chronoLabel.setText(chrono.getTime().getCompleteDate());
	}
	
}
