package training;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login implements ActionListener{
	JTextField user_in, pass_in;
	
	Login(){
		JFrame f = new JFrame();
		
		JLabel title = new JLabel("Login");
		title.setBounds(250-(100/2), 50, 100, 25);
		f.add(title);
		
		JLabel user = new JLabel("User Name :");
		user.setBounds(50, 150, 100, 25);
		f.add(user);
		
		user_in = new JTextField();
		user_in.setBounds(200, 150, 100, 25);
		f.add(user_in);
		
		JLabel pass = new JLabel("Password :");
		pass.setBounds(50, 200, 100, 25);
		f.add(pass);
		
		pass_in = new JTextField();
		pass_in.setBounds(200, 200, 100, 25);
		f.add(pass_in);
		
		
		
		JButton login_btn = new JButton("Login");
		login_btn.setBounds(250-(100/2), 300, 100, 25);
		f.add(login_btn);
		login_btn.addActionListener(this);
		
		f.setSize(500, 500);
		
		f.setLayout(null);
		f.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
//		System.out.println(user_in.getText());
//		System.out.println(pass_in.getText());
//		
		if(user_in.getText().equals("vivek") && pass_in.getText().equals("bootcamp")) {
			System.out.println("Login granted");
		}
		else
			System.out.println("Login denied");
	}
	
	public static void main(String args[]) {
		new Login();
	}
}
