package mCL._GUIclasses.TabPanels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

import mCL._Domains.PINnumber;
import mCL._Domains.PhoneNumber;
import mCL._GUIclasses.*;
import mCL.mainOperations.Validator.PIN_Validator;

public class Load_Panel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private final FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 105, 50);
	private JLabel welcomeL, input_PINL, receiptTR_L;
	private static JLabel totalReceiptsL;
	private JTextField inputPIN_TF;
	private static JTextArea receiptTR_TA;
	private JButton confirmLoadB;
	private static JButton copyTRReceiptB;
	private JPanel inputPIN, receiptTR;
	private JScrollPane scrollTRreceipt;
	private PINnumber pinNumValidate = new PINnumber();
	
	private PhoneNumber phoneNum;
	private Color[] colorLoadMenu;
	private Font[] fontLoadMenu;
	
	public Load_Panel(PhoneNumber phoneNo_LP){
		
				//		Get and set phone no. and telecom preferences for use in design and info
		phoneNum = phoneNo_LP;
		colorLoadMenu = phoneNum.getTelecom().getColorPrefs(); 
		fontLoadMenu = phoneNum.getTelecom().getFontPrefs(); 

				//		Set components
		setWelcome();
		setConfirmButton();
		setInput_PIN(); 
		setReceiptTR();
		setCopyReceiptButton();
		
				//		Set and modify layouts for the components in a subpanel
		inputPIN.setLayout(new FlowLayout());
		receiptTR.setLayout(new BoxLayout(receiptTR, BoxLayout.Y_AXIS));
		
				//		Set layout for main panel
		setLayout(fl);
		
				//		Add those subpanels and components to the main panel
		add(welcomeL);
		add(inputPIN);
		//add(confirmLoadB);
		add(receiptTR);
		add(copyTRReceiptB);
		
				//		Set color BG for main program
		setBackground(colorLoadMenu[3]);
	}

	private void setWelcome() 
	{
				//		Set Welcome label w/ the telecom name
		welcomeL = new JLabel("Welcome, " + phoneNum.getTelecom().toString() + " subscriber");
		welcomeL.setFont(fontLoadMenu[0]);
		welcomeL.setForeground(colorLoadMenu[4]);
	}
	
	private void setInput_PIN() 
	{
				//		Set PIN input label for prompt line and font color
		input_PINL = new JLabel("Enter a PIN number:");
		input_PINL.setForeground(colorLoadMenu[5]);

				//		Set its TF w/ a tooltip
		inputPIN_TF = new JTextField(14);
		inputPIN_TF.setToolTipText("Type PIN number from your purchased load card");
		
				//		Set PIN input panel and color BG
				//		Add those components to that panel
		inputPIN = new JPanel();
		inputPIN.setBackground(colorLoadMenu[1]);
		inputPIN.add(input_PINL);
		inputPIN.add(inputPIN_TF);
		inputPIN.add(confirmLoadB);
	}
	
	private void setReceiptTR() 
	{
				//		Set receiptTR label and its color
		receiptTR_L = new JLabel("Transaction Receipt(s):");
		receiptTR_L.setForeground(colorLoadMenu[5]);		
		
				//		Set text area w/ color BG and make not editable
				//		Add focus listener to register TA event
		receiptTR_TA = new JTextArea(6, 48);
		receiptTR_TA.setBackground(new Color(255, 254, 189));
		receiptTR_TA.setEditable(false);
		receiptTR_TA.addFocusListener(new FocusTF());
		
				//		Add a scroll pane for the receipt TA to navigate multiple receipts
		scrollTRreceipt = new JScrollPane(receiptTR_TA);
		
				//		Set receipt counter info w/ its color BG and visibility(set to false for no transaction)
		totalReceiptsL = new JLabel();
		totalReceiptsL.setForeground(colorLoadMenu[5]);
		totalReceiptsL.setVisible(false);
		
				//		Set receipt transaction panel w/ color BG
				//		Add those components
		receiptTR = new JPanel();
		receiptTR.setBackground(colorLoadMenu[1]);
		receiptTR.add(receiptTR_L);
		receiptTR.add(scrollTRreceipt);
		receiptTR.add(totalReceiptsL);
	}
	
	private void setCopyReceiptButton()
	{
				//		Set up 'copy receipt' button w/ color for font and BG. Also a copy text function
				//		Add action listener and disable functionality for this button for no transaction
		copyTRReceiptB = new JButton(new DefaultEditorKit.CopyAction());
		copyTRReceiptB.setText("Copy Receipt");
		copyTRReceiptB.setBackground(colorLoadMenu[6]);
		copyTRReceiptB.setForeground(colorLoadMenu[7]);
		copyTRReceiptB.addActionListener(this);
		copyTRReceiptB.setEnabled(false);
	}
	
	private void setConfirmButton() 
	{
				//		Set confirm button w/ color font and BG
				//		Add action listener
		confirmLoadB = new JButton("Reload");
		
		confirmLoadB.setBackground(colorLoadMenu[6]);
		confirmLoadB.setForeground(colorLoadMenu[7]);
		confirmLoadB.addActionListener(this);
	}
	
	private static String totalReceipts = "   Total receipts: ";
	private static int receiptCtr = 0;
	public static void updateReceiptCtr() 
	{
				//		Control the manner of receipt transaction event updates by:
				//		Enable 'copy receipt' button if not
				//		Increment receipt counter and set total receipts text
				//		Enable total receipts panel visibility
		if(!copyTRReceiptB.isEnabled()) copyTRReceiptB.setEnabled(true);
		
		receiptCtr++;
		totalReceiptsL.setText(totalReceipts + receiptCtr);
		if(!totalReceiptsL.isVisible()) totalReceiptsL.setVisible(true);
	}

	public void actionPerformed(ActionEvent buttonLoadMenuEv)
	{
		if(buttonLoadMenuEv.getSource() == confirmLoadB)
			try {
				processPINnum(inputPIN_TF.getText(), phoneNum);
			} catch (Exception pinEx) {
				DialogBox.showDialogMsg("PIN Invalid", "INVALID PIN INPUT", JOptionPane.ERROR_MESSAGE);
			}
		else if(buttonLoadMenuEv.getSource() == copyTRReceiptB){
			copyTRReceipt();
		}

	}

	public static void copyTRReceipt() 
	{
		receiptTR_TA.requestFocusInWindow();
		//receiptTR_TA.requestFocus(true);
		receiptTR_TA.selectAll();
		if(!receiptTR_TA.getText().equals(""))
			DialogBox.showDialogMsg("Copied TR receipt", "You can now paste it to any text editor", JOptionPane.INFORMATION_MESSAGE);
		else DialogBox.showDialogMsg("TR receipt not copied ", "You don't have any transactions yet", JOptionPane.WARNING_MESSAGE);
	}
	
	public void clearInputTF()
	{
		inputPIN_TF.setText("");
		inputPIN_TF.repaint();
	}

	public static void updateTRreceiptTA(String receipt)
	{
		receiptTR_TA.append(receipt);
		updateReceiptCtr();
		receiptTR_TA.repaint();
	}
	
	public void processPINnum(String input_PINstr, PhoneNumber phoneNum) throws Exception 
	{
		pinNumValidate.setPIN(input_PINstr);
		
		if(PIN_Validator.loadPINvalidate(pinNumValidate, phoneNum)){
			MobileCreditLoader.updateLoadBalP(phoneNum.getLoadBalance());
			updateTRreceiptTA(PIN_Validator.getTransactionRecord());
			clearInputTF();
			JOptionPane.showMessageDialog(null,  "You have successfully loaded " + PIN_Validator.getPINload() + " to your phone number: "+ 
																													phoneNum.getNum(), "PIN valid", JOptionPane.INFORMATION_MESSAGE);
		} else
			throw new Exception();
	}

}
