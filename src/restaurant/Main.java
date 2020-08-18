package restaurant;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.*;

import restaurantGUI.Style;

public class Main {
	private JFrame frame;
	
	public void init() {
		frame = new JFrame();
		int width=500,height=500;
		
		JLabel background = new JLabel(new ImageIcon("images/Main/Main Page bg.png"));
		background.setBounds(0, 0, width, height);
		frame.add(background);
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBounds(0,0,width, height);
		panel.setOpaque(false);
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.anchor=GridBagConstraints.SOUTH;
		gc.insets = new Insets(100, 10, 0, 10);
		
		gc.gridx = 0; gc.gridy = 0; 
		JButton login = Style.getButton("images/Main/login btn.png");

		login.addActionListener((event)->{
			
			new Login().init();
			terminate();
		});
		panel.add(login, gc);
		
		gc.insets = new Insets(10, 0, 0, 0);
		gc.gridy = 1;
		JButton register = Style.getButton("images/Main/register btn.png");
		register.setOpaque(false);
		register.addActionListener((event)->{
			
			new Register().init();
			terminate();
		});
		panel.add(register, gc);
		
		background.add(panel);
		
		int closeSize=35;
		JButton close = Style.getJButton("images/close.png", false, closeSize, closeSize);
		close.setBounds(455, 20, closeSize, closeSize);
		background.add(close);
		
		close.addActionListener((event)->{
			frame.dispose();
		});
		
		frame.setUndecorated(true);
		frame.setLayout(null);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void terminate() {
		frame.dispose();
	}
	
	public static void main(String[] a) {
		new Main().init();
	}
	
}
