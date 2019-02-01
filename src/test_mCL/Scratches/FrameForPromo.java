package test_mCL.Scratches;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;

public class FrameForPromo extends JFrame{

	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JButton unsubscribe;

	public FrameForPromo(){
		
		Container pane = getContentPane();
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		
		l1 = new JLabel("Network scope: null");
		l2 = new JLabel("Text Limit: null");
		l3 = new JLabel("Data Allo: null");
		
		unsubscribe = new JButton("Unsubscribe to this promo");
		
		//l1.setVisible(false);
		pane.add(l1);
		pane.add(l2);
		pane.add(l3);
		pane.add(unsubscribe);
		//pack();
		setBounds(400,200,300,300);
	}
	
	public static void main(String[] args)
	{
		FrameForPromo fp = new FrameForPromo();
		fp.setVisible(true);
	}
	
}
