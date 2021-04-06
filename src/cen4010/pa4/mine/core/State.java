package cen4010.pa4.mine.core;

import javax.swing.JPanel;

// a window's current state
public abstract class State implements StateMethods {
	protected boolean loaded = false;

	private Window parentWindow;
	protected JPanel mainPanel;	// each state has a JPanel for its components
	
	protected String name;
	
	public State(String name, Window parent) {
		super();
		mainPanel = new JPanel();
		this.parentWindow = parent;
		this.name = name;
	}

	public Window getParent() {
		return parentWindow;
	}

	public void setParent(Window parent) {
		this.parentWindow = parent;
	}
}
