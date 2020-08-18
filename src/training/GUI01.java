package training;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import restaurantGUI.Style;

public class GUI01 {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(null);
	    frame.setSize(500,500);
	    frame.setBackground(Color.white);
		
	    JLabel label = new JLabel(new ImageIcon("images/img01.jpg"));
	    label.setBounds(0, 0, 500, 500);
	    label.setBackground(Color.white);
	    frame.add(label);
	    
		JPanel panel01 = new JPanel(null);
		
//		panel01.add(new JButton("button 1"));
//		panel01.add(new JButton("button 2"));
//		panel01.add(new JButton("button 3"));
		
		panel01.setBounds(0, 0, 500, 500);
		panel01.setBackground(Color.orange);
		panel01.setOpaque(false);
		
		
		label.add(panel01);
//		/mnt/Programs/Codes/BootCampJava/Team30

	   
		 JLabel user = Style.getLabel("Username:");//new JLabel("Username:");
//	     user.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
	     user.setBounds(50, 100, 150, 50);
	     panel01.add(user);
	     
	     JTextField username = Style.getTextField();//new JTextField();
	     username.setBounds(140, 110, 150, 30);
//	     username.setOpaque(false);
//	     username.setBorder(BorderFactory.createEmptyBorder());
	     panel01.add(username);
	     
	     JSeparator split = new JSeparator();
	     split.setBounds(140, 140, 150, 30);
	     panel01.add(split);
	    
	    JRadioButton radio =new JRadioButton("Customer");
	     radio.setBounds(100, 200, 100, 50);
//	     radio.setOpaque(false);
//	     radio.setContentAreaFilled(false);
//	     panel01.add(radio);
	    
	     JButton btn = new JButton("settings", new ImageIcon("images/Dashboard/settings.png"));
	     btn.setBounds(100, 200, 100, 50);
	     panel01.add(btn);

	    
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
