package mCL._GUIclasses;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class PromptBeforeClose extends WindowAdapter{
	
	private int decision; 
	
	public void windowClosing(WindowEvent e){
		decision = DialogBox.showOK_CancelMsg("Confirm Exit", "Are you sure you want to exit?\n"
																										+ "(Please ensure and save your transactions and other credentials)");
		
		if(decision == JOptionPane.OK_OPTION)
			System.exit(0);
	}

}
