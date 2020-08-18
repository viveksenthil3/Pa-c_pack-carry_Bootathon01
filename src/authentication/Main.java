package authentication;

import javax.swing.*;

public class Main {
	JFrame frame;

	public static void main(String[] args) {
		new Main().init();
		
	}
	
	
	public void init() {
		frame = new JFrame();
		
		JButton login = new JButton("Login");
		login.setBounds(100, 100, 150, 30);
		login.addActionListener((event)->{
			new Login().init();
		});
		frame.add(login);
		
		JButton register = new JButton("Register");
		register.setBounds(100, 150, 150, 30);
		register.addActionListener((event)->{
			new Register().init();
		});
		frame.add(register);
		frame.setLayout(null);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

}
