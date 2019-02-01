package mCL._GUIclasses.TabPanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import mCL.IO_generate.FeedbackForm;
import mCL._Domains.PhoneNumber;
import mCL._GUIclasses.DialogBox;
import mCL._GUIclasses.FocusTF;

public class FeedbackForm_Panel extends JPanel implements ActionListener, ItemListener{
	
	private static final long serialVersionUID = 1L;

	private static JLabel nickNameL, ageL, professionL, genderL, ratingL, commentsL;
	private static JTextField nickNameTF, professionTF;
	@SuppressWarnings("rawtypes")
	private static JComboBox ageDD, ratingDD;
	private static JRadioButton maleRB, femaleRB;
	private static ButtonGroup genderBGroup;
	private static JTextArea commentsTA;
	private static JPanel feedBackFormP, nP, aP, gP, pP, cP;
	private static JButton confirmFB_B;
	private static JScrollPane scrollComment;
	
	private static Integer[] ageList, ratingList;
	private final String[] gender = {"Male", "Female"}, fbFormData = new String[6];
	private String genderState;
	private boolean isValidForm = true, isGenderPick = false;
	private PhoneNumber pNum_feedback;
	private Color[] colorFBMenu;

	private JLabel feedbackTitleL, feedbackTitleHelpL;
	
	public FeedbackForm_Panel(PhoneNumber pNo){
		pNum_feedback = pNo;
		colorFBMenu = pNum_feedback.getTelecom().getColorPrefs(); 
		
		setLayout(null);
		
		setMenuTitle();
		setNickName();
		setAge();
		setGender();
		setRating();
		setProfession();
		setComments();
		setFeedBackForm();
		setConfirmB();
		
		add(feedbackTitleL);
		add(feedbackTitleHelpL);
		add(feedBackFormP);
		add(confirmFB_B);
		
		setBackground(colorFBMenu[3]);
		
		System.gc();
	}

	private void setMenuTitle() 
	{
		feedbackTitleL = new JLabel("Feedback Menu");
		feedbackTitleL.setFont(new Font("Calibri", Font.BOLD, 20));
		feedbackTitleL.setForeground(colorFBMenu[5]);
		feedbackTitleL.setBounds(30, 10, 200, 50);
		
		feedbackTitleHelpL = new JLabel("Your feedback serves as reactions to our services and systems to fairly improve the matters");
		feedbackTitleHelpL.setForeground(colorFBMenu[5]);
		feedbackTitleHelpL.setBounds(40, 40, 590, 50);
	}

	private void setNickName() 
	{
		nickNameL = new JLabel("NickName (max. 10 chars): ");
		nickNameTF = new JTextField(10);
		
		nickNameL.setForeground(colorFBMenu[5]);
		
		nP = new JPanel();
		nP.setBackground(colorFBMenu[1]);
		nP.add(nickNameL);
		nP.add(nickNameTF);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setAge() 
	{
		ageL = new JLabel("Age: ");
		ageL.setForeground(colorFBMenu[5]);
		
		ageList = getIntegerList(new Integer[100], 1);
		
		ageDD = new JComboBox(ageList);
		ageDD.setMaximumRowCount(11);
		
		aP = new JPanel();
		aP.setBackground(colorFBMenu[1]);
		aP.add(ageL);
		aP.add(ageDD);
	}
	
	private void setGender() 
	{
		genderL = new JLabel("Gender (please select one): ");
		genderL.setForeground(colorFBMenu[5]);
		
		maleRB = new JRadioButton(gender[0]);
		femaleRB = new JRadioButton(gender[1]);
		maleRB.addItemListener(this);
		femaleRB.addItemListener(this);
		
		genderBGroup = new ButtonGroup();
		genderBGroup.add(maleRB);
		genderBGroup.add(femaleRB);
		
		gP = new JPanel();
		gP.setBackground(colorFBMenu[1]);
		gP.add(genderL);
		gP.add(maleRB);
		gP.add(femaleRB);
	}

	private String otherStr = "Others..";
	private void setProfession() 
	{
		professionL = new JLabel("Profession: ");
		professionL.setForeground(colorFBMenu[5]);
		
		professionTF	= new JTextField(otherStr, 15);
		professionTF.addFocusListener(new FocusTF());
		
		pP = new JPanel();
		pP.setBackground(colorFBMenu[1]);
		pP.add(professionL);
		pP.add(professionTF);
		pP.add(ratingL);
		pP.add(ratingDD);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setRating() 
	{
		ratingL = new JLabel("Rating: ");
		ratingL.setForeground(colorFBMenu[5]);
		
		ratingList = getIntegerList(new Integer[5], 0);
		
		ratingDD = new JComboBox(ratingList);
		//ratingDD.setMaximumRowCount(5);
	}

	private void setComments()
	{
		commentsL = new JLabel("Comments/Suggestions: (max. 100 chars)");
		commentsL.setForeground(colorFBMenu[5]);
		
		commentsTA = new JTextArea(5, 20);
		commentsTA.setLineWrap(true);
		commentsTA.setWrapStyleWord(true);
		scrollComment = new JScrollPane(commentsTA);
		
		cP = new JPanel();
		cP.setBackground(colorFBMenu[1]);
		cP.setLayout(new FlowLayout());
		cP.add(commentsL);
		cP.add(scrollComment);
	}

	private void setFeedBackForm() 
	{
		feedBackFormP= new JPanel();
		
		feedBackFormP.setLayout(new BoxLayout(feedBackFormP, BoxLayout.Y_AXIS));
		feedBackFormP.setBackground(colorFBMenu[1]);
		
		feedBackFormP.add(nP);
		feedBackFormP.add(aP);
		feedBackFormP.add(gP);
		feedBackFormP.add(pP);
		feedBackFormP.add(cP);
		
		feedBackFormP.setBounds(230, 110, 350, 335);
	}

	private void setConfirmB() 
	{
		confirmFB_B = new JButton("Send feedback");
		confirmFB_B.setBackground(colorFBMenu[6]);
		confirmFB_B.setForeground(colorFBMenu[7]);
		confirmFB_B.addActionListener(this);
		
		confirmFB_B.setBounds(610, 420, 140, 30);
	}	
	
	private Integer[] getIntegerList(Integer[] list, int choice)
	{
		if(choice == 1)
			for(int i = 0; i<list.length; i++)
				list[i] = i + 1;
		else{
			int ct = list.length;
			for(int i = 0; i<list.length; i++)
				list[i] = ct--;
		}
		
		return list;
	}
	
	public void actionPerformed(ActionEvent confirmFBformEv)
	{
		if(confirmFBformEv.getSource() == confirmFB_B){
			try {
				processActionEvents();
			} catch (IOException e) {
				System.out.println("CANNOT FIND FILE: feedbackForm DB in FeedbackForm_Panel");
			}
		}
	}

	private void processActionEvents() throws IOException 
	{
		isValidForm = true;
		String nickName = nickNameTF.getText();
		String comment = commentsTA.getText();
		if(nickName.length() > 11 || comment.isEmpty() || comment.length() > 101 || !isGenderPick)
			isValidForm = showWarningDialog();
		else{
			if(nickName.isEmpty()) nickNameTF.setText("Anonymous");
		fbFormData[0] = nickNameTF.getText();
		fbFormData[1] = ageList[ageDD.getSelectedIndex()] + " y/o";
		fbFormData[2] = genderState;
		fbFormData[3] = professionTF.getText();
		fbFormData[4] = ratingList[ratingDD.getSelectedIndex()] + " pts";
		fbFormData[5] = "\"" + comment + "\"";
		}
		
		if(isValidForm){
		FeedbackForm.generateFormToFile(fbFormData, pNum_feedback);
		clearInput();
		showConfirmationDialog();
		}
		
		System.gc();
	}

	public void itemStateChanged(ItemEvent chosenE)
	{
		if(chosenE.getSource() == maleRB){
			genderState = gender[0];
			isGenderPick = true;
		} else	if(chosenE.getSource() == femaleRB){
			genderState = gender[1];
			isGenderPick = true;
		}
	}

	private boolean showWarningDialog() 
	{
		String msg = "INVALID FORM INPUT\n";
		msg += "NOTE: Inspect proper measures for valid input";
		
		DialogBox.showDialogMsg("Feedback Error", msg, JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private void showConfirmationDialog()
	{
		DialogBox.showDialogMsg("Feedback Sent", "Thank you for your feedback :D"
														+ "\nWe assure to improve and maintain the system permitting your wonderful suggestions", 
															JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void clearInput()
	{
		nickNameTF.setText("");
		maleRB.setSelected(false);
		femaleRB.setSelected(false);
		professionTF.setText(otherStr);
		commentsTA.setText("");
		
		repaint();
	}

}
