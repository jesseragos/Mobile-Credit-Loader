package mCL._GUIclasses;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.text.JTextComponent;

public class FocusTF extends FocusAdapter{
	
	public void focusGained(FocusEvent e) {
        ((JTextComponent)e.getSource()).selectAll();			// select all when text clicked
     }

}
