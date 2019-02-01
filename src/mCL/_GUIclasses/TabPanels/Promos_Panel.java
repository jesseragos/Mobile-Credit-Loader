package mCL._GUIclasses.TabPanels;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import mCL.IO_generate.*;
import mCL._Domains.LoadBalance;
import mCL._Domains.PhoneNumber;
import mCL._GUIclasses.*;
import mCL.dataStructs.LinkedListString;
import mCL.mainOperations.MatchedItems;
import mCL.mainOperations.SInfoProcessor;

public class Promos_Panel extends JPanel implements ActionListener, ItemListener{

	private static final long serialVersionUID = 1L;
	private JPanel promosMenu, psSelectP, psBP;
	private JButton callPromoB, textPromoB, unsubscribeB; 
	private JLabel textPromoL, surfPromoL, callPromoL, psHelpL, psHelpL2, psDDL, psDDL2;
	private JComboBox<Object>[] psDD, psDD2;
	private CardLayout sections;
	private JPanel callPromoP, textPromoP, surfSectionP, surfNetSectionP, surfAppSectionP;
	private JButton surfPromoB;
	private JCheckBox[] appCB;
	private JTextField[] pctrTF;
	
	private PhoneNumber pNum_Promos;
	private Color[] colorPromoMenu;
	private String[][] promoLabelData = new String[4][3];
	private JPanel[] pSections;
	private JButton[] confirmPromoB, back2promosB;
	private String[] callDDlist, textDDlist, surfDDlist, surfAppDDlist;
	private String[] callDDlist2, textDDlist2, surfDDlist2;
	private int[] callCostList, textCostList, surfNetCostList, surfAppCostList;
	private int[] callCostList2, textCostList2, surfNetCostList2;
	private boolean isSubscribed = false;
	
	private JButton promoDetailsB;
	
	public Promos_Panel(PhoneNumber pNo)
	{
		pNum_Promos = pNo;
		colorPromoMenu = pNum_Promos.getTelecom().getColorPrefs();
		
		sections = new CardLayout();
		setLayout(sections);
		
		setPromoMenu();
		assignPromoSections();
		setSurfPromoSections();
		setPSubscriptionArea();
		
		add(promosMenu, "Promo Menu");
		for(int i = 0; i<pSections.length; i++)
			add(pSections[i], promoLabelData[i][0] + " Menu");
		add(surfSectionP, "Surf Section");
		
		sections.show(this, "Promo Menu");
	}

	private void setPromoMenu() 
	{
		promosMenu = new JPanel();
		
		promosMenu.setLayout(null);
		promosMenu.setBackground(colorPromoMenu[3]);

		setPMcomps();
		
		promosMenu.add(callPromoB);
		promosMenu.add(textPromoB);
		promosMenu.add(surfPromoB);
		promosMenu.add(callPromoL);
		promosMenu.add(textPromoL);
		promosMenu.add(surfPromoL);
		promosMenu.add(currentPromoL);
		promosMenu.add(promoDetailsB);
		promosMenu.add(unsubscribeB);
	}
	
	private String promo = " Promo";
	private int hPos_Comps = 100;
	private void setPMcomps() 
	{
		Icon i = new ImageIcon(FileIO.getChosenFile("CallPromoIcon"));
		Icon i2 = new ImageIcon(FileIO.getChosenFile("TextPromoIcon"));
		Icon i3 = new ImageIcon(FileIO.getChosenFile("SurfPromoIcon"));
		
		callPromoB = new JButton(i);
		textPromoB = new JButton(i2);
		surfPromoB = new JButton(i3);
		
		callPromoL = new JLabel("CALL"+promo.toUpperCase());
		textPromoL = new JLabel("TEXT"+promo.toUpperCase());
		surfPromoL = new JLabel("SURF"+promo.toUpperCase());
		
		currentPromoL = new JLabel();
		
		promoDetailsB = new JButton("Your" + promo + " details <<");
		promoDetailsB.setBackground(colorPromoMenu[6]);
		promoDetailsB.setForeground(colorPromoMenu[7]);
		
		unsubscribeB = new JButton("Unsubscribe to this" + promo);
		unsubscribeB.setBackground(colorPromoMenu[6]);
		unsubscribeB.setForeground(colorPromoMenu[7]);
		
		callPromoL.setForeground(colorPromoMenu[5]);
		textPromoL.setForeground(colorPromoMenu[5]);
		surfPromoL.setForeground(colorPromoMenu[5]);
		
		currentPromoL.setForeground(colorPromoMenu[5]);
		
		callPromoB.setBounds(170, 130, hPos_Comps, hPos_Comps);
		textPromoB.setBounds(370, 130, hPos_Comps, hPos_Comps);
		surfPromoB.setBounds(570, 130, hPos_Comps, hPos_Comps);
		
		callPromoL.setBounds(182, 210, hPos_Comps, hPos_Comps);
		textPromoL.setBounds(383, 210, hPos_Comps, hPos_Comps);
		surfPromoL.setBounds(582, 210, hPos_Comps, hPos_Comps);
		
		//currentPromoL.setBounds(575, 320, 200, 100);
		currentPromoL.setFont(new Font("Corbel", Font.BOLD + Font.ITALIC , 12));
		currentPromoL.setSize(400, hPos_Comps);
		
		promoDetailsB.setBounds(hPos_pSub, 395, 200, 30);
		unsubscribeB.setBounds(hPos_pSub, 435, 220, 30);
		
		callPromoB.addActionListener(this);
		textPromoB.addActionListener(this);
		surfPromoB.addActionListener(this);
		promoDetailsB.addActionListener(this);
		unsubscribeB.addActionListener(this);
	}
	
	private String currentPromoStr = "Current"+promo+": ",
							noPromo = "No current"+promo.toLowerCase()+" subscribed",
							timeStart = "Time started: ",
							pNotice = " (based on user selection & transactions)";
							
	private JLabel currentPromoL;
	private LinkedListString<String> pSubList;
	private String[] pSubInfo, tCodes = TransactionRecord.getTransactCodes();
	private int i;
	private void setPSubscriptionArea()																																		//	cdwfw?????????????? 
	{
		getPromoSubsInfo();
		
		if(pSubInfo.length == 0) processUnsubscribedPromoDetails();
		else for( i = 0; i<tCodes.length; i++)
			if(pSubInfo[1].equals(tCodes[i])) processSubscribedPromoDetails(i);
		
		//System.out.println("No sie");
			//else System.out.println("transactCodes not found in Promos Panel");}
	}

	private String promoTitle;
	private int hPos_pSub = 50;
	private void processSubscribedPromoDetails(int i)
	{
		promoTitle = promoLabelData[i][0] + promo;
		processSubsDetails(i);
		processPSubArea(promoTitle.toUpperCase(), true);
		setCurrentPromoLLoc(320);
	}
	
	private void processUnsubscribedPromoDetails() 
	{
		processPSubArea(noPromo, false);
		details = "";
		promoTitle = "";
		setCurrentPromoLLoc(400);
	}

	private void setCurrentPromoLLoc(int hSize) 
	{
		currentPromoL.setLocation(hPos_pSub, hSize);
	}

	private void processPSubArea(String promo, boolean b) 
	{
		printCPromoLabel(promo, b);
		cPromoCompsVisible(b);
		isSubscribed = b;
	}

	private void printCPromoLabel(String promo, boolean isSubcribed) 
	{
		if(isSubcribed) currentPromoL.setText(currentPromoStr + promo);
		else currentPromoL.setText(promo);
	}

	private String[] subsDetailsLabels;
	private String details = "", nl = "\n";
	private void processSubsDetails(int j) 
	{
		subsDetailsLabels = TransactionRecord.generatePromoLabels(j);
		
		for(j = 0; j<pSubInfo.length-3; j++)
			details += 	subsDetailsLabels[j] + pSubInfo[j+2] + nl;
		details += 	timeStart + pSubInfo[4];
		
		details += nl+nl+ pNotice;
	}

	@SuppressWarnings("unchecked")
	private void assignPromoSections() 
	{
		assignPromoLabels();
		setDropDownLists();
		
		pSections = new JPanel[] {callPromoP, textPromoP, surfNetSectionP, surfAppSectionP};
		
		psDD = new JComboBox[pSections.length];
		psDD2 = new JComboBox[pSections.length];
		confirmPromoB = new JButton[pSections.length];
		back2promosB = new JButton[pSections.length];
		pctrTF = new JTextField[pSections.length];
		promoCost = new double[pSections.length];
		
		int[] costDDdata = {}, costDDdata2 = {};
		for(int i = 0; i<pSections.length; i++){
			switch(i){
			case 0: costDDdata = callCostList; costDDdata2 = callCostList2; break;
			case 1: costDDdata = textCostList; costDDdata2 = textCostList2; break;
			case 2: costDDdata = surfNetCostList; costDDdata2 = surfNetCostList2; break;
			case 3: costDDdata = surfAppCostList;
			}
			setPromoSections(i, costDDdata, costDDdata2);
		}
	}

	private String[] units = {"Local Network", "All Network", "minutes", "hour", "hours", "day", "days", "MB"}; 
	private void setDropDownLists() 
	{
		callDDlist = new String[] {units[0], units[1]};
		callDDlist2 = new String[] {"10 "+units[2], "30 "+units[2], "1 "+units[3], "5 "+units[4], "12 "+units[4]};
		callCostList = new int[] {1, 2};
		callCostList2 = new int[] {5,10, 15, 20, 30};
		
		textDDlist = callDDlist;
		textDDlist2 = new String[] {"1 "+units[5], "2 "+units[6], "5 "+units[6]};
		textCostList = callCostList;
		textCostList2 = new int[] {20, 40, 80};
		
		surfDDlist = new String[] {"1 "+units[5], "5 "+units[6], "7 "+units[6], "14 "+units[6], "31 "+units[6]};
		surfDDlist2 = new String[] {"100 "+units[7], "300 "+units[7], "500 "+units[7], "800 "+units[7]}; 
		surfNetCostList = new int[] {1, 2, 3, 5, 6};
		surfNetCostList2 = new int[] {10, 20, 30, 50};
		
		surfAppDDlist = new String[] {"25 "+units[7], "50 "+units[7], "150 "+units[7], "300 "+units[7]};
		surfAppCostList = new int[] {2, 3, 7 ,10};
	}

	private void assignPromoLabels()
	{
		String select = "Select", time = "duration";
		
		promoLabelData[0][0] = "Call";
		promoLabelData[0][1] = select + " network scope: ";
		promoLabelData[0][2] = select + " call " + time + ": ";
		
		promoLabelData[1][0] = "Text";
		promoLabelData[1][1] = promoLabelData[0][1];
		promoLabelData[1][2] = select + " unlimited texts " + time + ": ";
		
		promoLabelData[2][0] = "Internet Surf";
		promoLabelData[2][1] = select + " surf " + time + ": ";
		promoLabelData[2][2] = select + " data allocation: ";
		
		promoLabelData[3][0] = "Apps/Sites Surf";
		promoLabelData[3][1] = select + " app/site choice(s): ";
		promoLabelData[3][2] = promoLabelData[2][2];
	}
	
	private String[] ddData = {}, ddData2 = {};
	private void setPromoSections(int i, int[] costList, int[] costList2) 
	{
		pSections[i] = new JPanel();
		
		pSections[i].setLayout(null);
		pSections[i].setBackground(colorPromoMenu[3]);
		
		getDDlist(i);
		
		setPScomps(i, ddData, ddData2);
		setPCtr(costList, costList2, i);
		
		callPromoL.setBounds(30, 20, 300, 50);
		psHelpL.setBounds(40, 50, 450, 50);
		psHelpL2.setBounds(115, 70, 500, 50);
		pctrP.setBounds(245, 270, 200, 40);
		psBP.setBounds(310, 305, 200, 110);
		
		pSections[i].add(callPromoL);
		pSections[i].add(psHelpL);
		pSections[i].add(psHelpL2);
		
		pSections[i].add(psSelectP);
		pSections[i].add(pctrP);
		pSections[i].add(psBP);
	}
	
	private void getDDlist(int i) 
	{
		switch(i){
		case 0: ddData = callDDlist; ddData2 = callDDlist2; break;
		case 1: ddData = textDDlist; ddData2 = textDDlist2; break;
		case 2: ddData = surfDDlist; ddData2 = surfDDlist2; break;
		case 3: ddData2 = surfAppDDlist;
		}
	}

	private void setPScomps(int i, String[] ddList, String[] ddList2)
	{
		psSelectP = new JPanel();
		psSelectP.setLayout(new FlowLayout());
		psSelectP.setBackground(colorPromoMenu[1]);
		callPromoL = new JLabel(promoLabelData[i][0] + " Promo Subscription");
		psHelpL = new JLabel("Instructions: Select choices of promo units together with the cost amount");
		psHelpL2 = new JLabel("NOTE: Use the counter below to monitor cost amounts");
		
		psDDL = new JLabel(promoLabelData[i][1]);
		psDDL.setForeground(colorPromoMenu[5]);
		psSelectP.add(psDDL);
		
		if(i != pSections.length-1){
			psDD[i] = new JComboBox<Object>(ddList);
			psDD[i].addItemListener(this);
			psSelectP.add(psDD[i]);
		} else{
			setAppSelections();
			for(int ctr = 0; ctr<appCB.length; ctr++)
				psSelectP.add(appCB[ctr]);
		}
		
		psDDL2 = new JLabel(promoLabelData[i][2]);
		psDDL2.setForeground(colorPromoMenu[5]);
		
		psDD2[i] = new JComboBox<Object>(ddList2);
		
		psSelectP.add(psDDL2);
		psSelectP.add(psDD2[i]);
		
		if(i != pSections.length-1)
			psSelectP.setBounds(270, 170, 255, 65);
		else
			psSelectP.setBounds(290, 145, 230, 115);
		
		confirmPromoB[i] = new JButton("Confirm promo selections");
		back2promosB[i] = new JButton("<< Back to menu");
		
		callPromoL.setFont(new Font("Calibri", Font.BOLD, 20));
		
		callPromoL.setForeground(colorPromoMenu[5]);
		psHelpL.setForeground(colorPromoMenu[5]);
		psHelpL2.setForeground(colorPromoMenu[5]);
		
		psDD2[i].addItemListener(this);
		
		confirmPromoB[i].setBackground(colorPromoMenu[6]);
		confirmPromoB[i].setForeground(colorPromoMenu[7]);
		
		back2promosB[i].setBackground(colorPromoMenu[6]);
		back2promosB[i].setForeground(colorPromoMenu[7]);
				
		confirmPromoB[i].addActionListener(this);
		back2promosB[i].addActionListener(this);
		
		psBP = new JPanel();
		psBP.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 25));
		psBP.setBackground(colorPromoMenu[3]);
		psBP.add(confirmPromoB[i]);
		psBP.add(back2promosB[i]);
	}
	
	private JLabel pctrL;
	private JPanel pctrP;
	private int ddIndx, dd2Indx;
	private double[] promoCost;
	private JButton surfNetB, surfAppB;
	private void setPCtr(int[] costList, int[] costList2, int ddi)
	{
		pctrP = new JPanel();
		pctrP.setBackground(colorPromoMenu[3]);
		
		pctrL = new JLabel("Total cost: ");
		pctrL.setForeground(colorPromoMenu[5]);
		
		pctrTF[ddi] = new JTextField(5);
		pctrTF[ddi].setEditable(false);
		pctrTF[ddi].setForeground(Color.WHITE);
		pctrTF[ddi].setBackground(Color.BLACK);
		
		pctrP.add(pctrL);
		pctrP.add(pctrTF[ddi]);
		updateUnitChoices(psDD[ddi], psDD2[ddi], costList, costList2, ddi);
	}
	
	private int appCtr = 0;
	private JButton surfBack2menu;
	private void updateUnitChoices(JComboBox<Object> dd, JComboBox<Object> dd2, int[] costList, int[] costList2, int i)
	{
		getDDIndex(dd2);
		
		if(i != pSections.length-1){
			ddIndx = dd.getSelectedIndex();
			promoCost[i] = costList[ddIndx] * costList2[dd2Indx];
			
		} else promoCost[i] = costList[dd2Indx]*appCtr;
		
		pctrTF[i].setText("P" + promoCost[i]);
		pctrTF[i].repaint();
	}
	
	private void getDDIndex(JComboBox<Object> dd2)
	{
		dd2Indx = dd2.getSelectedIndex();
	}

	private void setSurfPromoSections() 
	{
		surfSectionP = new JPanel();

		surfSectionP.setLayout(new FlowLayout(FlowLayout.CENTER, 180, 200));
		surfSectionP.setBackground(colorPromoMenu[3]);

		setSPcomps();
		
		surfSectionP.add(surfNetB);
		surfSectionP.add(surfAppB);
		surfSectionP.add(surfBack2menu);
	}
	
	private void setSPcomps() 
	{
		surfNetB = new JButton("Internet Promo");
		surfAppB = new JButton("Apps/Sites Promo");
		surfBack2menu = new JButton("<< Back to Promo Menu"); 
		
		surfNetB.setBackground(colorPromoMenu[6]);
		surfNetB.setForeground(colorPromoMenu[7]);
		
		surfAppB.setBackground(colorPromoMenu[6]);
		surfAppB.setForeground(colorPromoMenu[7]);
		
		surfBack2menu.setBackground(colorPromoMenu[6]);
		surfBack2menu.setForeground(colorPromoMenu[7]);
		
		surfNetB.addActionListener(this);
		surfAppB.addActionListener(this);
		surfBack2menu.addActionListener(this);
	}

	private String[] apps = {"Facebook", "Twitter", "Youtube", "Viber"};
	private void setAppSelections() 
	{
		appCB = new JCheckBox[4];
		for(int ctr = 0; ctr<appCB.length; ctr++){
			appCB[ctr] = new JCheckBox(apps[ctr]);
			//appCB[ctr].setBackground(colorPromoMenu[1]);
			//appCB[ctr].setForeground(colorPromoMenu[5]);
			appCB[ctr].addItemListener(this);
		}
	}

	private Object aSource;
	public void actionPerformed(ActionEvent promoAEv)
	{
		aSource = promoAEv.getSource();
		
		if(aSource == callPromoB)
			sections.show(this, promoLabelData[0][0] + " Menu");
		
		else if(aSource == textPromoB)
			sections.show(this, promoLabelData[1][0] + " Menu");
		
		else if(aSource == surfPromoB)
			sections.show(this, "Surf Section");
		
		else if(aSource == surfNetB)
			sections.show(this, promoLabelData[2][0] + " Menu");
		
		else if(aSource == surfAppB)
			sections.show(this, promoLabelData[3][0] + " Menu");

		else if(aSource == back2promosB[0] || aSource == back2promosB[1] || aSource == surfBack2menu)
			sections.show(this, "Promo Menu");

		else if(aSource == back2promosB[2] || aSource == back2promosB[3])
			sections.show(this, "Surf Section");
		
		else if(aSource == unsubscribeB)
			processUnsubscription();
				
		else if(aSource == promoDetailsB)
			DialogBox.showDialogMsg(promoTitle, details, JOptionPane.DEFAULT_OPTION);
		
		else handleOtherActions();
	}

	private int yesORno;
	private void processUnsubscription() 
	{
		if(isSubscribed){
			yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to unsubscribe?", null, JOptionPane.YES_NO_OPTION);
			if(yesORno == JOptionPane.YES_OPTION){
			processUnsubscribedPromoDetails() ;
			updatePromoSubsFile();
			DialogBox.showDialogMsg("Promo Unsubscribed",
					"You're now unsubscribed and can proceed to any new promo subscription",
					JOptionPane.INFORMATION_MESSAGE);
			}
		} else DialogBox.showDialogMsg("Unsubscription Invalid", 
				"You don't have any current subscription",
				JOptionPane.ERROR_MESSAGE);
	}

	private void updatePromoSubsFile()
	{
		 getPromoSubsInfo();
		 if(pSubInfo.length != 0)
			 pSubList.remove(MatchedItems.getMidIndex());
		 
		 try { FileIO.updateFile(pSubList, promoSubscriptionsPath);
		} catch (IOException e) { System.out.print(promoSubscriptionsPath + " PROBLEM UPDATING in Promos Panel"); }
	}
	
	private final String promoSubscriptionsPath = "promoSubscriptions DB";
	private void getPromoSubsInfo()
	{
		try { pSubList = FileIO.extractedDB(promoSubscriptionsPath);
		} catch (IOException e) { System.out.print(promoSubscriptionsPath + " NOT FOUND in Promos Panel"); }
		
		pSubInfo = MatchedItems.findIn(pNum_Promos.getNum(), pSubList, 0);
	}

	private void cPromoCompsVisible(boolean b) 
	{
		promoDetailsB.setVisible(b);
		unsubscribeB.setVisible(b);
	}

	private Object iSource;
	public void itemStateChanged(ItemEvent choiceE)
	{
		iSource = choiceE.getSource();
		
		if(iSource == psDD[0]|| iSource == psDD2[0])
			updateUnitChoices(psDD[0], psDD2[0], callCostList, callCostList2, 0);
		
		else if(iSource == psDD[1] || iSource == psDD2[1])
			updateUnitChoices(psDD[1], psDD2[1], textCostList, textCostList2, 1);
		
		else if(iSource == psDD[2]|| iSource == psDD2[2])
			updateUnitChoices(psDD[2], psDD2[2], surfNetCostList, surfNetCostList2, 2);
		
		else if(iSource == appCB[0] || iSource == appCB[1] || iSource == appCB[2] || 
					iSource == appCB[3] || iSource == psDD2[3]){
			getAppChoices();
			updateUnitChoices(psDD[2], psDD2[3], surfAppCostList, surfNetCostList2, 3);
		}
	}
	
	private String appChoices;
	private void getAppChoices() 
	{
		appChoices = "";
		appCtr = 0;
		for(int i = 0; i<appCB.length; i++)
			if(appCB[i].isSelected()){
				appCtr++;
				appChoices += "[" + apps[i] + "] "; 
			}
		appChoices = appChoices.trim();
	}

	private String invalidPromo = "Promo Subscription Invalid";
	private void handleOtherActions()
	{
		try { pNum_origAmount = pNum_Promos.getLoadBalance().getAmount();
		} catch (IOException e) {System.out.println("LOAD NOT FOUND IN PROMOS PANEL");}
		
		for(int i = 0; i<confirmPromoB.length; i++)
			if(aSource == confirmPromoB[i]){
				if(pNum_origAmount <= 1 || pNum_origAmount < promoCost[i])
					DialogBox.showDialogMsg(invalidPromo,
						"Insufficient balance to process subscription",
						JOptionPane.WARNING_MESSAGE);
				else if(isSubscribed)
					DialogBox.showDialogMsg(invalidPromo, 
							"Please unsubscribe to another current promo to subscribe in this",
							JOptionPane.WARNING_MESSAGE);
				else
					try{ processSubscription(i); }
					catch(IOException ioe) { System.out.println("FILE NOT FOUND IN PROMOS PANEL FOR TR");}
				
				break;
			}
	}

	private String[] dataTR = new String[6], sData;
	private double pNum_amountLeft, pNum_origAmount;
	private LinkedListString<String> sList;
	private boolean isProceed = true;
	private LoadBalance loadPNum = new LoadBalance();
	private void processSubscription(int pChoice) throws IOException														//bweeeeeeeee 
	{
		loadPNum.setAmount(pNum_origAmount - promoCost[pChoice]);
		pNum_amountLeft = loadPNum.getAmount();
		dataTR[0] = pNum_Promos.getNum();
		dataTR[1] = "P" + pNum_origAmount;
		
		getDDIndex(psDD2[pChoice]);
		getDDlist(pChoice);
		
		if(pChoice != pSections.length-1){
			getDDselections(ddData[ddIndx]);
		} else if(appCtr == 0){
				DialogBox.showDialogMsg(invalidPromo,
						"Please select any app services",
						JOptionPane.INFORMATION_MESSAGE);
				isProceed = false;
		} else	getDDselections(appChoices);
		
		if(isProceed){
		dataTR[3] = ddData2[dd2Indx];
		dataTR[4] = "P" + promoCost[pChoice];
		dataTR[5] = "P" + pNum_amountLeft;
		
		sList = FileIO.extractedDB("sInfo DB");
		sData = MatchedItems.findIn(pNum_Promos.getNum(), sList, 0);
		sList.remove(sData[0] + "," + sData[1] + "," + sData[2]);
		sData[2] = pNum_amountLeft+"";
		
		SInfoProcessor.finalizeSInfoUpdate(sList, sData);					//			UPDATED TO METHOD
		
		Load_Panel.updateTRreceiptTA(TransactionRecord.generateTransactions(promoLabelData[pChoice][0], dataTR, pNum_Promos));
		
		setPSubscriptionArea();
		
		MobileCreditLoader.updateLoadBalP(loadPNum);
		
		//isSubscribed = true;
		
		DialogBox.showDialogMsg("Promo Subscription Success", 
															"You are now subscribed to " +
															promoLabelData[pChoice][0] + " Promo\nCheck the TR receipts for information", 
															JOptionPane.INFORMATION_MESSAGE);
		}
		
		isProceed = true;
		
		System.gc();
	}

	private void getDDselections(String ddStr) 
	{
		dataTR[2] = ddStr;
	}
	
	/*public static void main(String[] args) throws Exception
	{
		PhoneNumber num = new PhoneNumber();
		num.setPNo("09276795487");
		
		JFrame fr = new JFrame(); 
		
		Promos_Panel p = new Promos_Panel(num);
		
		fr.add(p);
		fr.setSize(800, 600);
		fr.setVisible(true);
	}*/

}
