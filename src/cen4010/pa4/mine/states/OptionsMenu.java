package cen4010.pa4.mine.states;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import cen4010.pa4.mine.core.DocumentSizeFilter;
import cen4010.pa4.mine.core.State;
import cen4010.pa4.mine.core.Window;

public class OptionsMenu extends State {
	private static final long serialVersionUID = 1L;
	
	BoxLayout boxLayout;

	public OptionsMenu(Window parent) {
		super("Options", parent);
	}
	
	@Override
	public void load() {
		mainPanel.setBackground(Color.darkGray);
		mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		boxLayout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
		mainPanel.setLayout(boxLayout);
		
		JButton BackButton = new JButton("Back");
		BackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		int fontSize = BackButton.getFont().getSize();
//		BackButton.setMaximumSize(new Dimension(40, 40));
		
		mainPanel.add(BackButton);


		JPanel Dimensions = new JPanel();
		Dimensions.setLayout(new BoxLayout(Dimensions, BoxLayout.LINE_AXIS));
		Dimensions.setSize(new Dimension(500 + 0 * mainPanel.getSize().width / 4, Dimensions.getSize().height));
		
		JTextField WidthField = new JTextField();
		WidthField.setText("3");
		WidthField.setAlignmentX(Component.CENTER_ALIGNMENT);
		WidthField.setColumns(10);
		WidthField.setMaximumSize(new Dimension(40, fontSize + 10));
		((AbstractDocument) WidthField.getDocument()).setDocumentFilter(new DocumentSizeFilter(5));

		JTextField HeightField = new JTextField();
		HeightField.setText("3");
		HeightField.setAlignmentX(Component.CENTER_ALIGNMENT);
		HeightField.setColumns(10);
		HeightField.setMaximumSize(new Dimension(40, fontSize + 10));
		((AbstractDocument) HeightField.getDocument()).setDocumentFilter(new DocumentSizeFilter(5));
		
		Dimensions.add(WidthField);
		Dimensions.add(Box.createRigidArea(new Dimension(20, Dimensions.getSize().height)));
		Dimensions.add(HeightField);

		mainPanel.add(Dimensions);
		

		
		BackButton.addActionListener((ActionEvent e) -> {
			getParent().popState();
		});
	}

	@Override
	public void enter() {
	}

	@Override
	public void update() {
	}

	@Override
	public void draw() {
	}

	@Override
	public void exit() {
	}

}
