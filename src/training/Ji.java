package training;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Ji {
	
	public Ji() {
    	JFrame frame =new JFrame("Register");
		frame.setSize(600,600);
	    JTextField t1,t2,t3;
	    t1=new JTextField();
//	    String name1 = t1.getText();
	    
	    t1.setBounds(100,110,200,20);
	    t2=new JTextField();
	    
	    
	    t2.setBounds(100,160,150,20);
	    t3=new JTextField();
	    
	    t3.setBounds(100,210,300,20);
	    frame.add(t1);
	    frame.add(t2);
	    frame.add(t3);
	    JLabel l1,l2,l3;
	    l1=new JLabel("Patient Name");
	    l1.setBounds(100,85,100,20);
	    l2=new JLabel("Phone Number");
	    l2.setBounds(100,140,100,20);
	    l3=new JLabel("Address");
	    l3.setBounds(100,190,100,20);
	    frame.add(l1);
	    frame.add(l2);
	    frame.add(l3);
	    JButton button =new JButton("Submit");	    
	    button.setBounds(100,300,100,20);
	    
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = t1.getText();
				int name1 = Integer.parseInt(name);
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","root","root");
					Statement stm = con.createStatement();
					
					
					ResultSet rs = stm.executeQuery("select * from patient where id="+name1+";");
					
//					for saving
//					insert into patient values(2, 'vivek', '123454654');
					rs.next();
//					
					t2.setText(rs.getString("name"));
//					
					
					
				}
				catch(Exception er) {
					System.out.println(er.getMessage());
				}
				
				
				System.out.println(name);
//				System.out.println(t1.getText());
				
			}
		});
	    
	    frame.add(button);
	    
	    
	    frame.setLayout(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    

	}
          
		
	public static void main(String[] args) {
		new Ji();
		System.out.println("HAI");
}

}
