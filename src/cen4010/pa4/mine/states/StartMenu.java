package cen4010.pa4.mine.states;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import cen4010.pa4.mine.core.State;
import cen4010.pa4.mine.core.Window;
import cen4010.pa4.mine.tween.Tween;

public class StartMenu extends State {
	public StartMenu(Window parent) {
		super("Start", parent);
	}
//	JButton playButton;
	Point startPos;
	Point goalPos;
	
	@Override
	public void load() {
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
		mainPanel.setLayout(boxLayout);

		JButton playButton = new JButton("Play");
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton optionsButton = new JButton("Options");
		optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton exitButton = new JButton("Exit");
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel.add(playButton);
		mainPanel.add(Box.createRigidArea(new Dimension(100, 100)));
		mainPanel.add(optionsButton);
		mainPanel.add(Box.createRigidArea(new Dimension(100, 100)));
		mainPanel.add(exitButton);
	
		// cool hover animations
		MouseAdapter hoverLogic = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				JButton button = (JButton)e.getSource();
				Dimension size = button.getSize();
				Dimension goalSize = new Dimension(size.width + 20, size.height + 30);
				Point location = button.getLocation();
				Tween.to(size, goalSize,
				(from, to, equation) -> {
					return new Dimension((int) equation.math(from.width, to.width), (int) equation.math(from.height, to.height));
				}, (delta) -> {
					button.setSize(delta);
					button.setLocation(location.x + (size.width - delta.width) / 2, location.y + (size.height - delta.height) / 2);
				}).duration(0.1).start();
				System.out.println(button.getLocation());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				JButton button = (JButton)e.getSource();
				Dimension size = button.getSize();
				Point location = button.getLocation();
				Dimension goalSize = button.getPreferredSize();
				Tween.to(size, goalSize,
				(from, to, equation) -> {
					return new Dimension((int) equation.math(from.width, to.width), (int) equation.math(from.height, to.height));
				}, (delta) -> {
					button.setSize(delta);
					button.setLocation(location.x + (size.width - delta.width) / 2, location.y + (size.height - delta.height) / 2);
				}).duration(0.1).start();;
			}
		};
		playButton.addMouseListener(hoverLogic);
		optionsButton.addMouseListener(hoverLogic);
		exitButton.addMouseListener(hoverLogic);

		// what the buttons do
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
	}

	@Override
	public void exit() {
	}
	
	@Override
	public void update() {
	}

	@Override
	public void draw() {
	}
}
