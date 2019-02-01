/*	Main program interface - access most services	*/

package mCL._GUIclasses;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import mCL.IO_generate.FileIO;
import mCL._Domains.*;
import mCL._GUIclasses.TabPanels.*;
import mCL.mainOperations.TimeEvaluate;

public class MobileCreditLoader extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JTabbedPane prepaidApp = new JTabbedPane();
	private static JPanel loadAmountP;
	private static JTextField loadBalTF;
	private JLabel loadBalL;
	private static JLabel loadUpdateL, phoneNoL;
	private JMenuBar programMBar;
	private JMenu optionsMenu; //othersMenu;
	private JMenuItem switchPNumMI, programCreditsMI;
	
	private PhoneNumber pNum;
	private SubscriberLogin sl;
	private Color[] colorMCL;
	private final ImageIcon iconWindow = new ImageIcon(FileIO.getChosenFile("iconWindow")),
												iconCredits = new ImageIcon(FileIO.getChosenFile("iconCredits"));
	private final String titleName = " | Mobile Credit Loader";
	private String credits;
	
	public MobileCreditLoader(PhoneNumber phoneNo_mcl) throws IOException{
		
		pNum = phoneNo_mcl;																													//	Assign user's phone no. to a variable
		colorMCL = pNum.getTelecom().getColorPrefs();																		//	Assign user's telecom color preferences to a variable 
		
		Container pane = getContentPane();																							//	Access content pane
		
			//	Set layout and color background
		pane.setLayout(new BorderLayout());
		pane.setBackground(colorMCL[0]);
		
		setMenuBar();																																	//	Set menu bar w/ its items
		tPane_addTabs();																																//	Add tabs
		tPane_setDesign();																															//	Design tabs
		setProgramCredits();																														//	Set the program credits
		
			//	Add tabbed panel
		pane.add(prepaidApp);
		pane.add(new SInfoPanel(), BorderLayout.SOUTH);																
		
		addWindowListener(new PromptBeforeClose());																		//	Add window listener to prompt user before close
		setIconImage(iconWindow.getImage());																						//	Set window icon
		setTitle(phoneNo_mcl.getTelecom() + titleName);																		//	Set title
		setBounds(270, 70, 850, 600);																										//	Set bounds
		setResizable(false);																															//	Set not re-sizable
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);															//	Do nothing on close to allow dialog box to control program termination
	}
	
	private void setMenuBar() 
	{
			//	Instantiate JMenuBar and set its color BG
		programMBar = new JMenuBar();
		programMBar.setBackground(new Color(50, 50, 50));
		
		setOptionsMenu();																															//	Set options menu
		
		programMBar.add(optionsMenu);																								//	Add menu options to menu bar
		
//	programMBar.add(othersMenu);
		
		setJMenuBar(programMBar);																										//	Add menu bar
	}
	
	private void setOptionsMenu() 
	{
		optionsMenu = new JMenu("≡ MCL");																							//	Instantiate JMenu titled "= MCL"
		optionsMenu.setForeground(Color.WHITE);																				//	Set JMenu font color as white
//othersMenu = new JMenu("Others");
//othersMenu.setForeground(Color.WHITE);
		
		switchPNumMI = new JMenuItem("Log out Phone no.");															//	Instantiate a menu item "Log out Phone no."
		switchPNumMI.addActionListener(this);																						//	Add action listener             ^
		programCreditsMI = new JMenuItem("About MCL");																//	Instantiate another menu item "About MCL"
		programCreditsMI.addActionListener(this);																				//	Add action listener                         ^
		
			//	Add those items to menu bar 
		optionsMenu.add(switchPNumMI);
		optionsMenu.add(programCreditsMI);
//othersMenu.add(programCreditsMI);
	}

	public class SInfoPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		private SInfoPanel() throws IOException 
		{
					//		Set up load info panel to inform user of their current load status
			loadBalTF = new JTextField(6);
			loadBalL = new JLabel("Current load bal:");
			loadUpdateL = new JLabel();
			loadAmountP = new JPanel();
			phoneNoL = new JLabel();
			
					//		Set layout and BG for it
			loadAmountP.setLayout(new FlowLayout(FlowLayout.LEFT));
			loadAmountP.setBackground(new Color(50, 50, 50));

					//		Set the color, styles for its fonts and BG. 
					//		Modify the TF and update load status of user's no.
			loadBalL.setForeground(Color.WHITE);
			loadUpdateL.setForeground(new Color(150, 150, 150));
			loadUpdateL.setFont(new Font("Calibri", Font.ITALIC + Font.BOLD, 13));
			loadBalTF.setEditable(false);
			loadBalTF.setBackground(new Color(90, 255, 164));
			loadBalTF.setForeground(Color.BLACK);
			loadBalTF.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			updateLoadBalP(pNum.getLoadBalance());
			
					//		Add those these components to the load info subpanel
			loadAmountP.add(loadBalL);
			loadAmountP.add(loadBalTF);
			loadAmountP.add(loadUpdateL);
			
					//		Set up phone no. info text subpanel
			phoneNoL.setText("Your phone number: " + pNum.getNum() + " ");
			phoneNoL.setForeground(new Color(255, 230, 30));
			
					//		Set BG and layout of subscriber's info panel
					//		Add those subpanels to the left and right sides of subscriber's info panel
			setBackground(new Color(50, 50, 50));
			setLayout(new BorderLayout());
			add(loadAmountP, BorderLayout.WEST);
			add(phoneNoL, BorderLayout.EAST);
		}
	}

	public static void updateLoadBalP(LoadBalance loadBal) 
	{
				//		To update, get latest load and set text to the TF
				//		Show the time of transaction and update panel modifications 
		loadBalTF.setText(loadBal.toString());
		loadUpdateL.setText("Last updated at " + TimeEvaluate.getCurrentFixedTimeFormat());
		loadAmountP.repaint();
	}

	private void tPane_addTabs() 
	{
				//		Add instantiated panels as tabs to the tabbed pane
		prepaidApp.addTab("Load", new Load_Panel(pNum));
		prepaidApp.addTab("Promos", new Promos_Panel(pNum));
		prepaidApp.addTab("Services", new Services_Panel(pNum));
		prepaidApp.addTab("Feedback", new FeedbackForm_Panel(pNum));
	}
	
	private void tPane_setDesign() 
	{
				//		Set color design of tabs
		prepaidApp.setBackground(colorMCL[1]);
		prepaidApp.setForeground(colorMCL[2]);
	}

	private void setProgramCredits() 
	{
				//		Set credit details
		credits = "\"Mobile Credit Loader\"\n" + 
						"Programmed by: Jessekiel N. Ragos\n" +
						//"Assisted by: John Vers Rignon\n" + 
						"Developed for NEU CompSci Case Study";
		credits += "\n\nDISCLAIMER: The software program is a stand-alone or just a ‘dummy’ program"
				+ "\ndemonstrating the idea of an alternative network carrier services, and that every"
				+ "\noperations and events occurring in this program version does not scope local or"
				+ "\nwide network connections, neither real transactions nor the service subscriptions."
				+ "\nAlso, it is not in our intention to collect information for those mentioned standards"
				+ "\naccording to the service protocol of the original system and these are all for the"
				+ "\nfunctionality of the software project. Those information are just stored and used"
				+ "\nfor that purpose and not for other personal interests and illegal activities.";
	}
	
	private int logOutChoice;
	public void actionPerformed(ActionEvent menuItemEv)
	{
				//		Detect 'log out' menu item event and prompt user for final decision
				//		If yes, terminate main program and instantiate/show login window again
		if(menuItemEv.getSource() == switchPNumMI){
			logOutChoice = DialogBox.showYesOrNoMsg(null, "Are you sure you want to log out?");
			if(logOutChoice == JOptionPane.YES_OPTION){
			// System.gc();
			dispose();
			sl = new SubscriberLogin();
			sl.setVisible(true);}
				//		Detect 'About MCL' menu item event to display credits 
		} else if (menuItemEv.getSource() == programCreditsMI)
			JOptionPane.showMessageDialog(null, credits, "Program Credits", JOptionPane.CLOSED_OPTION, iconCredits);
	}

}