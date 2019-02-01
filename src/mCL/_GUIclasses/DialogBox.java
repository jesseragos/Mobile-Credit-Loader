package mCL._GUIclasses;

import javax.swing.JOptionPane;

public class DialogBox {
	
	public static void showDialogMsg(String title, String msg, int dIcon)
	{
		JOptionPane.showMessageDialog(null, msg, title, dIcon);
	}
	
	public static int showYesOrNoMsg(String title, String msg)
	{
		return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION);
	}
	
	public static int showOK_CancelMsg(String title, String msg)
	{
		return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.OK_CANCEL_OPTION);
	}

}
