package training;

import java.awt.FlowLayout;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JDBC02 {
	
	public void database(String name) {
		try{
			Connection ji = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","root","root");
			Statement suj = ji.createStatement();
//			String name = "mukesh";
				
			suj.executeUpdate("insert into hotel values(8,8,\""+name+"\",\"veg\",\"00:00:00\",\"00:00:00\",\"Sunday\");");
			
			ResultSet rs = suj.executeQuery("select * from hotel;");//("insert into emp values(1,'"+name+"');");

			while(rs.next()) {
				System.out.println(rs.getInt("user_id")+"\t"+rs.getString("name")+"\t"+rs.getString("type")+"\t"+rs.getString("start_time")+"\t");
			}
//			rs.close();
//			stm.close();
//			con.close();
			
		}
		catch(SQLException er) {
			System.out.println(er.getMessage());
		}
		

	}
	
	
	public void display() {
		JFrame frame =new JFrame();
		
		JTextField name = new JTextField(20);
		frame.add(name);
		
		JButton btn = new JButton("store");
		btn.addActionListener((event)->{
			database(name.getText());
		});
		frame.add(btn);
		
		frame.setLayout(new FlowLayout());
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	public static void main(String[] args) {

//		new JDBC02().display();
		JDBC02 obj = new JDBC02();
		obj.display();

}}
