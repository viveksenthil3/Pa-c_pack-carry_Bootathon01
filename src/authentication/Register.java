package authentication;
import java.awt.event.*; 
import javax.swing.*; 
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class Register {
	JTextField userName;
	JTextField email;
	JPasswordField password1;
	JPasswordField password2;
	JTextField mobno;
	String gender;
	JTextField city;
	JFrame frame;
	void init()
	{
		frame=new JFrame();
		JLabel login=new JLabel("Register");
		login.setBounds(300-(100/2),50,100,25);
		JLabel user=new JLabel("UserName:");
		user.setBounds(150,125,100,40);
		JLabel eid=new JLabel("Email:");
		eid.setBounds(150,175,100,40);
		JLabel pass1=new JLabel("Password:");
		pass1.setBounds(150,225,100,40);
		JLabel pass2=new JLabel("Confirm Password");
		pass2.setBounds(150,275,200,40);
		JLabel mob=new JLabel("Mobile:");
		mob.setBounds(150,325,100,40);
		JLabel gender_label=new JLabel("Gender:");
		gender_label.setBounds(150,375,100,40);
		JRadioButton male =new JRadioButton("Male");   
		JRadioButton female =new JRadioButton("Female");
		male.setBounds(300,375,75,40);
		female.setBounds(375,375,75,40);
		ButtonGroup bg=new ButtonGroup();    
		bg.add(male);
		bg.add(female);
		JLabel city1=new JLabel("City:");
		city1.setBounds(150,425,100,40);
		userName=new JTextField();
		userName.setBounds(300,125,100,40);
		email=new JTextField();
		email.setBounds(300,175,100,40);
		password1=new JPasswordField();
		password1.setBounds(300,225,100,40);
		password2=new JPasswordField();
		password2.setBounds(300,275,100,40);
		mobno=new JTextField();
		mobno.setBounds(300,325,100,40);
		city=new JTextField();
		city.setBounds(300,424,100,40);
		frame.add(login);
		frame.add(user);
		frame.add(eid);
		frame.add(pass1);
		frame.add(pass2);
		frame.add(mob);
		frame.add(gender_label);
		frame.add(male);
		frame.add(female);
		frame.add(city1);
		frame.add(userName);
		frame.add(email);
		frame.add(password1);
		frame.add(password2);
		frame.add(mobno);
		frame.add(city);
		male.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                gender="Male";
            }
        });

        female.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            gender="Female";
        }
        });
        
		JButton b=new JButton("Submit");
		b.setBounds(200,500,100,40);
		b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
             String s1=userName.getText();
             String s2=email.getText();
             String s3=String.valueOf(password1.getPassword());
             String s4=String.valueOf(password2.getPassword());
       		 String s5 = mobno.getText();
       		 String s8= city.getText();
       		if(s3.equals(s4))
       		{
       			System.out.println("Success");
       			putData();
       		}
       		else
       		{
       			System.out.println("Failed");
       		}
       	
       		}          
         });
		frame.add(b);
		frame.setSize(600,600);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public boolean putData()
	{
	try(
		Connection con = Database.connect();
		Statement  st = con.createStatement();)
	{
		
		//String query="CREATE TABLE student(userName integer,email text primary key,pass1 text,mobno int,gender text,city text)";
        //int result = st.executeUpdate(query);  
        String query1="insert into students values('"+userName.getText()+"','"+String.valueOf(password1.getPassword())+"','"+email.getText()+"','"+mobno.getText()+"','"+gender+"','"+city.getText()+"')";
        st.executeUpdate(query1);
//        con.close();
        JOptionPane.showMessageDialog(frame,"Successfully Registered");
        System.out.println("Success!"+query1); 
        return true;
	}
	catch(Exception e)
	{
		System.out.println("---------->  "+e.getMessage());
		return false;
	}
	}
	public static void main(String[] args) {
		Register r1=new Register();
		r1.init();

	}

}
