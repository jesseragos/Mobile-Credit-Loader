package mCL._GUIclasses.TabPanels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import mCL.IO_generate.*;
import mCL._Domains.LoadBalance;
import mCL._Domains.PhoneNumber;
import mCL._GUIclasses.*;
import mCL.dataStructs.LinkedListString;
import mCL.mainOperations.MatchedItems;
import mCL.mainOperations.SInfoProcessor;

public class Services_Panel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel servicesMenu, shareLoadP, slP;
	private JButton updateLoadBalB, shareLoadSecB, shareLoadB, back2servicesB;
	private JLabel load2PNumHelpL, load2PNumL;
	private JTextField load2PNumTF;
	private CardLayout pages;
	private JLabel load2PNumAmountL;
	private JTextField load2PNumAmountTF;
	private JLabel load2PNumTitleL;
	
	private String num, load2share;
	private PhoneNumber pNum_Services, otherPNum = new PhoneNumber();
	private Color[] colorServMenu;
	private JLabel load2PNumHelpL2;
	private JPanel slBP;
	
	public Services_Panel(PhoneNumber pNo)
	{
		pNum_Services = pNo;
		colorServMenu = pNum_Services.getTelecom().getColorPrefs(); 
		
		pages = new CardLayout();
		setLayout(pages);
		
		setServicesMenu();
		setShareLoadSection();
		
		add(servicesMenu, "Service Menu");
		add(shareLoadP, "Share load Section");
		pages.show(this, "Service Menu");
		
	}

	private void setServicesMenu() 
	{
		servicesMenu = new JPanel();
		
		servicesMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 215));
		servicesMenu.setBackground(colorServMenu[3]);

		setSMcomps();
		
		servicesMenu.add(updateLoadBalB);
		servicesMenu.add(shareLoadSecB);
	}
	
	private void setSMcomps() 
	{
		updateLoadBalB = new JButton("Update/Recheck Load Balance");
		shareLoadSecB = new JButton("Share Load Balance");
		
		updateLoadBalB.setBackground(colorServMenu[6]);
		updateLoadBalB.setForeground(colorServMenu[7]);
		
		shareLoadSecB.setBackground(colorServMenu[6]);
		shareLoadSecB.setForeground(colorServMenu[7]);
		
		updateLoadBalB.addActionListener(this);
		shareLoadSecB.addActionListener(this);
	}
	
	private void setShareLoadSection() 
	{
		shareLoadP = new JPanel();
		
		shareLoadP.setLayout(null);
		shareLoadP.setBackground(colorServMenu[3]);
		
		setSLScomps();
		
		load2PNumTitleL.setBounds(30, 20, 200, 50);
		load2PNumHelpL.setBounds(40, 50, 470, 50);
		load2PNumHelpL2.setBounds(115, 70, 300, 50);										//////fw
		slP.setBounds(230, 165, 340, 55);
		slBP.setBounds(230, 270, 350, 45);
		
		shareLoadP.add(load2PNumTitleL);
		shareLoadP.add(load2PNumHelpL);
		shareLoadP.add(load2PNumHelpL2);
		
		shareLoadP.add(slP);
		shareLoadP.add(slBP);
		
	}
	
	private void setSLScomps()
	{
		load2PNumTitleL = new JLabel("Share Load Section");
		load2PNumHelpL = new JLabel("Instructions: Enter the phone number "
																+ "you want to share an exact amount load with");
		load2PNumHelpL2 = new JLabel("NOTE: P1/load share & partial load share is INVALID");
		load2PNumL = new JLabel("Enter receiver's phone number: ");
		load2PNumTF = new JTextField(11);
		load2PNumAmountL = new JLabel("Enter load amount to share: ");
		load2PNumAmountTF = new JTextField(5);
		shareLoadB = new JButton("Share load");
		back2servicesB = new JButton("<< Back to menu");
		
		load2PNumTitleL.setFont(new Font("Calibri", Font.BOLD, 20));
		
		load2PNumTitleL.setForeground(colorServMenu[5]);
		load2PNumHelpL.setForeground(colorServMenu[5]);
		load2PNumHelpL2.setForeground(colorServMenu[5]);
		load2PNumL.setForeground(colorServMenu[5]);
		load2PNumAmountL.setForeground(colorServMenu[5]);
		
		slP = new JPanel();
		slP.setLayout(new FlowLayout());
		slP.setBackground(colorServMenu[1]);
		slP.add(load2PNumL);
		slP.add(load2PNumTF);
		slP.add(load2PNumAmountL);
		slP.add(load2PNumAmountTF);
		
		shareLoadB.setBackground(colorServMenu[6]);
		shareLoadB.setForeground(colorServMenu[7]);
		
		back2servicesB.setBackground(colorServMenu[6]);
		back2servicesB.setForeground(colorServMenu[7]);
				
		shareLoadB.addActionListener(this);
		back2servicesB.addActionListener(this);
		
		slBP = new JPanel();
		slBP.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 1));
		slBP.setBackground(colorServMenu[3]);
		slBP.add(back2servicesB);
		slBP.add(shareLoadB);
	}
	
	private double loadAmount, pNum_amountLeft, pNum_origAmount;
	private String otherPNoStr, zeroDecs = "";
	private int indxLoad, decChars; 
	private void validateLoad2Share()
	{
		num = load2PNumTF.getText();
		load2share = load2PNumAmountTF.getText();
		loadAmount = 0;
		
		try {
			pNum_origAmount = pNum_Services.getLoadBalance().getAmount();
		} catch (IOException e2) {
			System.out.print("CANNOT FIND LOAD AMOUNT IN SERVICES PANEL");
		}
		
		try {
			otherPNum.setPNo(num);
			otherPNoStr = otherPNum.getNum();
		} catch (Exception e1) {
			System.out.print("CANNOT SET PNUM IN SERVICES PANEL");
		}
		
		if(num.equals("") || otherPNoStr.equals("null"))
			showMessageBox(3);
		else if(!pNum_Services.getTelecom().getName().equals(otherPNum.getTelecom().getName()))
			showMessageBox(8);
		else if(otherPNoStr.equals(pNum_Services.getNum()))
			showMessageBox(4);
		else try{
			loadAmount = Double.parseDouble(load2share);
			indxLoad = load2share.indexOf(".");
			
			getZeroDecimalCharacters();
			
			if(indxLoad != -1 && !load2share.substring(indxLoad+1, load2share.length()).equals(zeroDecs))
				showMessageBox(10);
			else if(loadAmount+1 > pNum_origAmount)
				showMessageBox(5);
			else if(loadAmount < 1)
				showMessageBox(9);
			else processLoad2Share(pNum_Services, otherPNum, loadAmount);
		} catch(Exception loadFormat){
			showMessageBox(6);
		}
		zeroDecs = "";
	}
	
	private void getZeroDecimalCharacters() 
	{
		decChars = load2share.length() - (indxLoad+1);
		while(decChars > 0){
			zeroDecs += "0";
			decChars--;
		}		
	}

	private final double charge = 1;
	private LinkedListString<String> sInfoList;
	private String[] dataReceipt;
	private String receipt = "";
	private LoadBalance loadLeft = new LoadBalance(),
											loadSend = new LoadBalance();
	private void processLoad2Share(PhoneNumber pNum_Services, PhoneNumber otherPNum, double loadAmount)
	{
		sInfoList = new LinkedListString<String>();
		double otherRecentLoad = 0;
		dataReceipt = new String[5];
		
		try {
			loadSend.setAmount(loadAmount);
			loadAmount = loadSend.getAmount();
			loadLeft.setAmount(pNum_Services.getLoadBalance().getAmount() - loadSend.getAmount() - charge);
			pNum_amountLeft = loadLeft.getAmount();
			sInfoList = FileIO.extractedDB("sInfo DB");
			String[] dataRow_Num = MatchedItems.findIn(pNum_Services.getNum(), sInfoList, 0);
			String[] dataRow_otherNum = MatchedItems.findIn(otherPNum.getNum(), sInfoList, 0);
			
			if(dataRow_Num.length != 0){
				if(dataRow_otherNum.length != 0){
					sInfoList.remove(dataRow_otherNum[0] + "," + dataRow_otherNum[1] + "," + dataRow_otherNum[2]);
					try{
						otherRecentLoad = Double.parseDouble(dataRow_otherNum[2]);
					} catch(Exception e){
						System.out.println("CANNOT PARSE dataRow_otherNum[2]");
					}
					sInfoList.add(dataRow_otherNum[0] + "," + dataRow_otherNum[1] + "," + (loadAmount + otherRecentLoad));
				}
			sInfoList.remove(dataRow_Num[0] + "," + dataRow_Num[1] + "," + dataRow_Num[2]);
			dataReceipt[1] = "P"+dataRow_Num[2];
			dataRow_Num[2] = pNum_amountLeft+"";
			SInfoProcessor.finalizeSInfoUpdate(sInfoList, dataRow_Num);
			
			dataReceipt[0] = pNum_Services.getNum();
			dataReceipt[2] = "P"+loadAmount;
			dataReceipt[3] = "P"+charge;
			dataReceipt[4] = "P"+pNum_amountLeft;
			
			receipt = TransactionRecord.generateTransactions("Share Load", dataReceipt, pNum_Services);
			Load_Panel.updateTRreceiptTA(receipt);
			
			MobileCreditLoader.updateLoadBalP(pNum_Services.getLoadBalance());
			showMessageBox(7);
			clearShareLoadInput();
			} else System.out.println("dataRow_Num NOT FOUND");
		} catch (IOException e) {
			System.out.println("CANNOT GET PNUM LOAD AMOUNT IN SERVICES PANEL");
		}
		
		System.gc();
	}
	
	private void clearShareLoadInput() 
	{
		load2PNumTF.setText("");
		load2PNumAmountTF.setText("");
		shareLoadP.repaint();
	}

	public void actionPerformed(ActionEvent confirmFBformEv)
	{
		if(confirmFBformEv.getSource() == updateLoadBalB){
			try {
				MobileCreditLoader.updateLoadBalP(pNum_Services.getLoadBalance());
				showMessageBox(1);
			} catch (IOException e) {
				System.out.print("INVALID LOAD BAL AT SERVICES_PANEL");
			}
		}
		else if(confirmFBformEv.getSource() == shareLoadSecB){
			try {
				if(pNum_Services.getLoadBalance().getAmount() > 1)
					pages.show(this, "Share load Section");
				else showMessageBox(2);
			} catch (IOException e) {
				System.out.print("INVALID LOAD BAL(2) AT SERVICES_PANEL");
			}
		}
		else if(confirmFBformEv.getSource() == shareLoadB){
			validateLoad2Share();
		}
		else if(confirmFBformEv.getSource() == back2servicesB)
			pages.show(this, "Service Menu");
	}

	private final String loadNotEnough = "Not Enough Load Balance", 
										invalidLoad = "Invalid Load Amount",
										invalidPhone = "Invalid Phone number";
	private String load = "";
	private void showMessageBox(int c)
	{
		String title = "", msg = "";
		int icon = JOptionPane.WARNING_MESSAGE;
		try {
			load = pNum_Services.getLoadBalance().toString();
		} catch (IOException e) {
			load = "unknown";
		}
		
		switch(c){
		case 1: title = "Load Balance Updated"; msg = "Your latest load balance: " + load + " \nCheck below panel for updated load balance"; 
																																									icon = JOptionPane.INFORMATION_MESSAGE; break;
		case 2: title = loadNotEnough; msg = "Make sure you have enough load to continue to this section"; break;
		case 3: title = invalidPhone; msg = "Make sure you have typed a valid phone number"; icon = JOptionPane.ERROR_MESSAGE; break;
		case 4: title = invalidPhone; msg = "You cannot share on own phone number"; icon = JOptionPane.ERROR_MESSAGE; break;
		case 5: title = loadNotEnough; msg = "You are trying to share a load amount greater/equal than yours"; icon = JOptionPane.ERROR_MESSAGE; break;
		case 6: title = invalidLoad; msg = "Please enter a proper amount"; icon = JOptionPane.ERROR_MESSAGE; break;
		case 7: title = "Load Share Success"; msg = "You have successfully shared P" + loadAmount + " w/ remaining balance of " + load + 
																					"\nPlease see your receipt at Load Menu"; icon = JOptionPane.INFORMATION_MESSAGE; break;
		case 8: title = "Invalid Telecom Receiver"; msg = "You cannot send to other local networks"; icon = JOptionPane.ERROR_MESSAGE; break;
		case 9: title = invalidLoad; msg = "You can only share load more/equal to P1\nunless you're trying to share nothing"; icon = JOptionPane.ERROR_MESSAGE; break;
		case 10: title = invalidLoad; msg = "You cannot send a partial number of your sending amount except zero decimals"; icon = JOptionPane.ERROR_MESSAGE; break;
		}
		
		DialogBox.showDialogMsg(title, msg, icon);
	}

}
