/*	~		SUBSCRIBER'S LOGIN WINDOW		~
 * 	Providing user's phone no. to activate program service	*/

package mCL._GUIclasses;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import mCL._Domains.PhoneNumber;

public class SubscriberLogin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private final int width = 400, height = 300;
	private final int hGap = 480, vGap = 210;
	private JLabel loginL;
	private JTextField loginTF;
	private JButton loginB;
	private final String titleName = "Log in | Mobile Credit Loader";
	private MobileCreditLoader mainProg;
	
	private PhoneNumber pNo = new PhoneNumber();
	
	public SubscriberLogin(){

				//		Access pane and set layout, BG
		Container pane = getContentPane();																							//	Instantiate content pane to access pane and allow components to work properly
		
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 100));											//	Set to FlowLayout and center align components and clamp spaces for other components to restrict to next below location
		pane.setBackground(new Color(50, 50, 50));																			//	Set background color to dark grey
		
			//		Initialize and set GUI components for the frame
		setLoginL();																																		//	Set login label
		setLoginTF();																																		//	Set login text field
		setLoginB();																																		//	Set login button
			
			//		Add those components
		pane.add(loginL);																																//	Add login label to pane
		pane.add(loginTF);																															//	Add login text field to pane
		pane.add(loginB);																															//	Add login button to pane
		
			//		Set up window properties
		setBounds(hGap, vGap, width, height);																						//	Set bounds (locations and sizes) of frame
		setTitle(titleName);																															//	Set title of frame
		setResizable(false);																															//	Disable re-sizable window
		setDefaultCloseOperation(EXIT_ON_CLOSE);																				//	Set default close to exit on close
	}

	private void setLoginL() 
	{
		loginL = new JLabel("Enter your phone number: ");																	//	Instantiate new JLabel
		loginL.setForeground(new Color(255, 230, 30));																		//	Set color to yellow
		loginL.setFont(new Font("OCR A Extended", Font.BOLD, 14));												//	Set font
	}
	
	private void setLoginTF() 
	{
		loginTF = new JTextField(11);																										//	Instantiate JTextField with 11 max columns
		loginTF.addKeyListener(new LoginKeyHandler());																	//	Add key listener to allow key action 
		loginTF.setToolTipText("Type your valid phone number in this field");									//	Add tool tip
	}
	
	private void setLoginB() 
	{
		loginB = new JButton("Proceed >>");																							//	Instantiate JButton
		loginB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));								//	*unknown*
		loginB.setFont(new Font("Century Gothic", Font.PLAIN, 15));												//	Set font
		loginB.setBackground(new Color(84, 255, 164));																		//	Set color
		loginB.addActionListener(new LoginButtonHandler());															//	Add action listener (enter key pressed) to trigger next phase
	}
		
	private class LoginKeyHandler extends KeyAdapter {
		  public void keyPressed(KeyEvent evt) {
			  if (evt.getKeyCode() == KeyEvent.VK_ENTER)																		//	Event key code == enter key
				  validateNumInput();																												//	Validate number input
		  }
		  
	}
	
	private class LoginButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent loginE)
		{
			validateNumInput();
		}		
	}
	
	private void validateNumInput()
	{
				//		Validate phone no. through selection structure
		try {
			pNo.setPNo(loginTF.getText());																									//	Get input from loginTF and assign as phone no.
			
			if(pNo.getNum().equals("null"))																								//	Throw exception if no. == to null 
				throw new NumberFormatException();
			
			if(pNo.getNum().equals("IDF"))																									//	^^                                           to IDF
				throw new ArithmeticException();
			
//	setVisible(false);
			
				//		 Dispose login and get to main program
			dispose();
			instantiateMainProg();
			
		} catch (NumberFormatException e) {																							//	Exception if number input invalid	
			JOptionPane.showMessageDialog(null, "Invalid input\nTry again", "INVALID NUMBER", JOptionPane.ERROR_MESSAGE);
		}	catch (ArithmeticException ae) {																								//	Exception if international format
			JOptionPane.showMessageDialog(null, "International dialling format is not considered\n"
																					+ "Please use the domestic format \"0(xxx) xxx-xxxx\"", "INVALID NUMBER", JOptionPane.ERROR_MESSAGE);
		}	catch (Exception e) {																													//	Final exception for not handled events
			System.out.println("ERROR ON LOGIN: ");
			e.printStackTrace();
		}										
	}

	private void instantiateMainProg() throws IOException {
				//		Instantiate and show main program
		mainProg = new MobileCreditLoader(pNo);																				//	Instantiate main program
		mainProg.setVisible(true);																											//	View the program
	}
		
}
