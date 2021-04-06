package cen4010.pa4.mine.states;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import cen4010.pa4.mine.core.State;
import cen4010.pa4.mine.core.Time;
import cen4010.pa4.mine.core.Tween;
import cen4010.pa4.mine.core.Window;

public class StartMenu extends State {
	private static final long serialVersionUID = 1L;

	public StartMenu(Window parent) {
		super("Main", parent);
	}
	JButton playButton;
	Point startPos;
	Point goalPos;
	
	@Override
	public void load() {
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
		mainPanel.setLayout(boxLayout);

		playButton = new JButton("Play");
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//		playButton.setPreferredSize(new Dimension(1280, 400));
		
		JButton testButton = new JButton("Test");
		testButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton optionsButton = new JButton("Options");
		optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton exitButton = new JButton("Exit");
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel.add(playButton);
		mainPanel.add(optionsButton);
		mainPanel.add(exitButton);
		
		playButton.addActionListener((ActionEvent e) -> {
			getParent().pushState("Game");
		});
		optionsButton.addActionListener((ActionEvent e) -> {
			getParent().pushState("Options");
		});
		exitButton.addActionListener((ActionEvent e) -> {
			getParent().listener.windowClosing(new WindowEvent(getParent(), WindowEvent.WINDOW_CLOSING));
		});		
	}

	@Override
	public void enter() {
		startPos = new Point(0, 200);
		goalPos = new Point(200, 0);
		playButton.setLocation(startPos);
	}

	@Override
	public void exit() {
	}
	@Override
	public void update() {
		Point position = Tween.tweenPoint(startPos, goalPos, Time.getTime());
		playButton.setLocation(position);
	}

	@Override
	public void draw() {
	}
}
