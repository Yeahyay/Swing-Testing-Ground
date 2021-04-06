package cen4010.pa3.mine.core;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Window extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	HashMap<String, State> allStates;
	Stack<State> stateStack;
	
	public WindowAdapter listener;
	
	CardLayout cardLayout;
	
	public Window(int sizeX, int sizeY, GraphicsConfiguration graphicsConfiguration) {
		super(graphicsConfiguration);

		// initialize book keeping data structures
		allStates = new HashMap<>();	// all states are kept track of in a HashMap by name so that only their names are needed
		stateStack = new Stack<>();
		
		cardLayout = new CardLayout();
		setLayout(cardLayout);
	
		Insets insets = getInsets();
		setPreferredSize(new Dimension(sizeX + insets.left + insets.right, sizeY + insets.bottom + insets.top));
		pack();	// attempts to size every component to their preferred size
		
		// these two lines functionally do the same thing
		setLocationRelativeTo(null);
	//	window.setLocation(new Point(window.getSize()) - graphicsEnvironment.getCenterPoint());
		
		// instantiates an anonymous WindowAdapter to listen for window close events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		listener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("AHHHH");
				e.getWindow().dispose();
			}
		};
		
		addWindowListener(listener);
		
		addComponentListener(new ComponentAdapter() {
		});
		
		setVisible(true);
	}

	public void addState(State state) {
		// store the state in a HashMap
		allStates.put(state.name, state);

		// assume that the first state pushed is the start state; might not be a good idea
		if (stateStack.size() == 0) {
			stateStack.push(state);
		}
		add(state.mainPanel, state.name);
		
		// initially loads each state
		if (!state.loaded) {
			state.load();
			state.loaded = true;
		}
		System.out.printf("State %s loaded\n", state.name);
	}
	
	public void removeState(State state) {
		
	}
	
	@Override
	public void run() {
		peekState().update();
	}

	public void displayState(State from, State to) {
		System.out.printf("From %s to %s\n", from.name, to.name);
		from.exit();
		to.enter();
		cardLayout.show(getContentPane(), to.name);
		pack();
	}

	public State peekState() {
		return stateStack.peek();
	}

	public void popState() {
		State fromState = stateStack.pop();
		fromState.exit();
		// if the state stack has nothing
		if (stateStack.size() <= 0) {
			// invokes the windowClosing event
			listener.windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		displayState(fromState, stateStack.peek());
	}

	public void pushState(State toState) {
		State fromState = peekState();
		stateStack.push(toState);
		displayState(fromState, toState);
	}
	public void pushState(String stateName) {
		// checks if the string is registered
		if (allStates.containsKey(stateName)) {
			State toState = allStates.get(stateName);
			State fromState = peekState();
			stateStack.push(toState);
			displayState(fromState, toState);
		}
	}
}
