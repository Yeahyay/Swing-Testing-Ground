package cen4010.pa3.mine.core;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentSizeFilter extends DocumentFilter {
	private int maxCharacters;
	
	public DocumentSizeFilter(int size) {
		super();
		maxCharacters = size;
	}
	
	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		if ((fb.getDocument().getLength() + text.length()) <= maxCharacters) {
			super.insertString(fb, offset, text, attrs);
		} else {
			Toolkit.getDefaultToolkit().beep();
		}
	}
	
	
}
