package restaurant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.*;

import restaurantGUI.Style;

import java.util.Calendar;
public class Register {
	JTextField userName;
	JTextField email;
	JPasswordField password;
	JPasswordField confirmPassword;
	JTextField phone;
	JTextArea address;
	JComboBox<String> city;
	JTextField hotelName;
	JComboBox<String> type;
	JSpinner startTime;
	JSpinner closeTime;
	JComboBox<String> holiday;
	JFrame frame;
	boolean isCustomer=true;
	
	
	void init()
	{
		int width=1100,height=600;
		
		frame = new JFrame();
		frame.setLayout(null);
		frame.setSize(width,height);
		
		JLabel background = new JLabel(new ImageIcon("images/Register/Customer/Cbg.png"));
		background.setBounds(0, 0, width, height);
		frame.add(background);

		
		JPanel leftPanel = new JPanel(null);
		leftPanel.setBackground(new Color(0,0,0,0));
		leftPanel.setBounds(0, 0, 350, height);
		background.add(leftPanel);
		
		JButton switchRegister = Style.getButton("images/Register/Customer/HRegistration btn.png");
		switchRegister.setBounds(50, 470, 280, 50);
		leftPanel.add(switchRegister);
		
		
		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.setBackground(new Color(0,0,0,0));
		rightPanel.setOpaque(false);

		rightPanel.setBounds(350, 0, width-350, height);
		background.add(rightPanel);
		
		rightPanel.add(customerMenu());
		
		JPanel panel = new JPanel(null);
		panel.setBackground(new Color(0,0,0,0));
		panel.setOpaque(false);
		panel.setBounds(350, 0, width-350, height);
		background.add(panel);
		
		JButton register = Style.getButton("images/Register/Register btn.png");
		register.setBounds(420, 510, 260, 45);
		panel.add(register);
		
		int closeSize=35;
		JButton close = Style.getJButton("images/close.png", false, closeSize, closeSize);
		close.setBounds(1055, 15, closeSize, closeSize);
		background.add(close);
		
		close.addActionListener((event)->{
			frame.dispose();
		});
		
		register.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
        	
	            if(isCustomer)
	            {
	            	validateAndRegisterCustomer(true);	            	
	            }
	            else 
	            {
	               validateAndRegisterHotel(true);
	            }
	            
    		} 
         });
		
		switchRegister.addActionListener((event)->{
			if(isCustomer) {
				background.setIcon(new ImageIcon("images/Register/Hotel/Hbg.png"));
				switchRegister.setIcon(new ImageIcon("images/Register/Hotel/CRegistration btn.png"));
				rightPanel.removeAll();
				rightPanel.add(hotelMenu());
				
				isCustomer=false;
				
				frame.validate();
				frame.repaint();
			}
			else {
				background.setIcon(new ImageIcon("images/Register/Customer/Cbg.png"));
				switchRegister.setIcon(new ImageIcon("images/Register/Customer/HRegistration btn.png"));
				rightPanel.removeAll();
				rightPanel.add(customerMenu());
				
				isCustomer=true;
				
				frame.validate();
				frame.repaint();
			}
		});

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public boolean validateAndRegisterHotel(boolean register) {

    	String s3=String.valueOf(password.getPassword());
        String s4=String.valueOf(confirmPassword.getPassword());
        boolean flag=true;
        Pattern regex;
    	Matcher match;
        
        if(!userName.getText().isEmpty() && register)
        {
        	try
        	{
        	ResultSet rs=new Database().get("select huser_name from hotel where huser_name='"+userName.getText()+"'");
            if(rs.next())
            {
            	flag=false;
            	JOptionPane.showMessageDialog(frame,"Username already exist","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
        	}
        	catch(Exception en)
        	{
        		System.out.println(en.getMessage());
        		
        	}
        }
         if(!email.getText().isEmpty()&& flag)
        {
        	regex = Pattern.compile("[a-zA-Z0-9.]*[@][a-zA-Z0-9.]+[.][a-zA-Z0-9]+");
        	match = regex.matcher(email.getText());
        	
        	if(match.matches() && register) {
        		try
            	{
            	ResultSet rs=new Database().get("select email from hotel where email='"+email.getText()+"'");
                if(rs.next())
                {
                	flag=false;
                	JOptionPane.showMessageDialog(frame,"Email Id already exist","Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            	}
            	catch(Exception en)
            	{
            		System.out.println(en.getMessage());
            		
            	}
        	}
        	else if(!match.matches()){
        		JOptionPane.showMessageDialog(frame,"Enter a valid Email","Error Message",
                        JOptionPane.ERROR_MESSAGE);
        		flag=false;
        	}
        	
        	
        }
        
        
        if(flag) {
        	if(userName.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"User Name is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(email.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Email Id is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(String.valueOf(password.getPassword()).isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Password is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(String.valueOf(confirmPassword.getPassword()).isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Confirm Password is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(phone.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Mobile number is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(address.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Address is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(city.getSelectedItem().equals("not selected"))
            {
            	JOptionPane.showMessageDialog(frame,"city is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(hotelName.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Hotelname is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(type.getSelectedIndex()==-1)
            {
            	JOptionPane.showMessageDialog(frame,"select any type","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(holiday.getSelectedIndex()==-1)
            {
            	JOptionPane.showMessageDialog(frame,"select day","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                	regex = Pattern.compile("[\\d]{10}");
                	match = regex.matcher(phone.getText());
                	
                	if(!match.matches()) {
                		JOptionPane.showMessageDialog(frame,"Invalid phone no.","Error Message",
                                JOptionPane.ERROR_MESSAGE);
                		flag=false;
                	}
                	regex = Pattern.compile("[\\w\\W]{6,}");
                	match = regex.matcher(s3);
                	
                	if(!match.matches()) {
                		JOptionPane.showMessageDialog(frame,"Invalid Password min length 6 ","Error Message",
                                JOptionPane.ERROR_MESSAGE);
                		flag=false;
                	}

                else if(s3.equals(s4)&& flag)
           		{
                	
                	if(!register)
                		return true;
              
           			if(register()) {
           				JOptionPane.showMessageDialog(frame,"Successfully Registered");
           				new Login().init();
           				terminate();
           			}
           			else           				
           				JOptionPane.showMessageDialog(frame,"sorry something went wrong","Error Message",
                            JOptionPane.ERROR_MESSAGE);
           		}
           		else if(flag)
           		{
           			
           			JOptionPane.showMessageDialog(frame,"password didn't match","Error Message",
                            JOptionPane.ERROR_MESSAGE);
           			
           		}
            }
        }
        return false;
	}
	
	
	public boolean validateAndRegisterCustomer(boolean register) {

    	String s3=String.valueOf(password.getPassword());
        String s4=String.valueOf(confirmPassword.getPassword());
        boolean flag=true;
        Pattern regex;
    	Matcher match;
        
        
        if(!userName.getText().isEmpty() && register)
        {
        	try
        	{
        	ResultSet rs=new Database().get("select user_name from user where user_name='"+userName.getText()+"'");
            if(rs.next())
            {
            	flag=false;
            	JOptionPane.showMessageDialog(frame,"Username already exist","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
        	}
        	catch(Exception en)
        	{
        		System.out.println(en.getMessage());
        		
        	}
        }
        
         if(!email.getText().isEmpty()&&flag)
        {	
        	
        	regex = Pattern.compile("[a-zA-Z0-9.]*[@][a-zA-Z0-9.]+[.][a-zA-Z0-9]+");
            match = regex.matcher(email.getText());
        	
        	if(match.matches() && register)                	
            	try
            	{
            	ResultSet rs=new Database().get("select email from user where email='"+email.getText()+"'");
                if(rs.next())
                {
                	flag=false;
                	JOptionPane.showMessageDialog(frame,"Email Id already exist","Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            	}
            	catch(Exception en)
            	{
            		System.out.println(en.getMessage());
            		
            	}
        	else if(!match.matches()){
        		JOptionPane.showMessageDialog(frame,"Enter a valid Email","Error Message",
                        JOptionPane.ERROR_MESSAGE);
        		flag=false;
    		}
        }
        
        if(flag) {
        	if(userName.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"User Name is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(email.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Email Id is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(String.valueOf(password.getPassword()).isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Password is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(String.valueOf(confirmPassword.getPassword()).isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Confirm Password is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(phone.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Mobile number is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(address.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(frame,"Address is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(city.getSelectedItem().equals("not selected"))
            {
            	JOptionPane.showMessageDialog(frame,"city is missing","Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {

                	regex = Pattern.compile("[\\d]{10}");
                    match = regex.matcher(phone.getText());

                	
                	if(!match.matches()) {
                		JOptionPane.showMessageDialog(frame,"Invalid phone no.","Error Message",
                                JOptionPane.ERROR_MESSAGE);
                		flag=false;
                	}

                	regex = Pattern.compile("[\\w\\W]{6,}");
                	match = regex.matcher(s3);
                	
                	if(!match.matches()&&flag) {
                		JOptionPane.showMessageDialog(frame,"Invalid Password min length 6 ","Error Message",
                                JOptionPane.ERROR_MESSAGE);
                		flag=false;
                	}
                else if(s3.equals(s4) && flag)
           		{
                	if(!register)
                		return true;
                	
           			
           			register();
           			JOptionPane.showMessageDialog(frame,"Successfully Registered");
           			
           			new Login().init();
           			terminate();
           		}
           		else if(flag)
           		{
           			
           			JOptionPane.showMessageDialog(frame,"password didn't match","Error Message",
           	                JOptionPane.ERROR_MESSAGE);
           		}
            }
        }
        return false;
	}
	
	public JPanel customerMenu()
	{  
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		GridBagConstraints g = new GridBagConstraints();
		JLabel user=new JLabel("UserName:");
		g.gridx=0;
		g.gridy=1;
		g.insets=new Insets(10,10,10,10);
		panel.add(user,g);
		
		JLabel eid=new JLabel("Email:");
		g.gridx=0;
		g.gridy=2;
		panel.add(eid,g);
		
		JLabel pass1=new JLabel("Password:");
		g.gridx=0;
		g.gridy=3;
		panel.add(pass1,g);
		
		JLabel pass2=new JLabel("Confirm Password");
		g.gridx=0;
		g.gridy=4;
		panel.add(pass2,g);
		
		JLabel mob=new JLabel("Phone:");
		g.gridx=0;
		g.gridy=5;
		panel.add(mob,g);
		
		JLabel addres=new JLabel("Address:");
		g.gridx=0;
		g.gridy=6;
		panel.add(addres,g);
		
		JLabel city1=new JLabel("City:");
		g.gridx=0;
		g.gridy=7;
		panel.add(city1,g);
		
		userName=new JTextField(15);
		g.gridx=1;
		g.gridy=1;
		g.insets=new Insets(10,10,10,10);
		panel.add(userName,g);
		
		email=new JTextField(15);
		g.gridx=1;
		g.gridy=2;
		panel.add(email,g);
		
		password=new JPasswordField(15);
		g.gridx=1;
		g.gridy=3;
		panel.add(password,g);
		
		confirmPassword=new JPasswordField(15);
		g.gridx=1;
		g.gridy=4;
		panel.add(confirmPassword,g);
		
		phone=new JTextField(15);
		g.gridx=1;
		g.gridy=5;
		panel.add(phone,g);
		
		address=new JTextArea(4,15);
		g.gridx=1;
		g.gridy=6;
		panel.add(address,g);
		
		Database db = new Database();
		
		city=new JComboBox<String>();
		city.addItem("not selected");
		try
		{
			ResultSet rs=db.get("select city_name from city");
		
			while(rs.next())
			{
			    city.addItem(rs.getString("city_name"));
			    
			   
			}
		}
		catch(Exception ex)
        {
       	   System.out.println(ex.getMessage());
        }
		
		city.setSelectedItem("not selected");
		Dimension d1 = new Dimension(160,30);
		city.setPreferredSize(d1);
		panel.add(city,g);
		g.gridx=1;
		g.gridy=7;
		panel.add(city,g);
		return panel;
	}
	
	
	
	public JPanel hotelMenu(){
        JPanel panel1 = new JPanel(new GridBagLayout());
        panel1.setOpaque(false);
        
	GridBagConstraints gd = new GridBagConstraints();
	
	JLabel user=new JLabel("UserName:");
	gd.gridx=0;
	gd.gridy=0;
	gd.insets=new Insets(5,5,5,5);
            gd.anchor=GridBagConstraints.PAGE_START;
	panel1.add(user,gd);
	
	JLabel eid=new JLabel("Email:");
	gd.gridx=0;
	gd.gridy=1;
	panel1.add(eid,gd);
	JLabel pass1=new JLabel("Password:");
	gd.gridy=2;
	panel1.add(pass1,gd);
            
	JLabel pass2=new JLabel("Confirm Password:");
    gd.gridy=3;
    panel1.add(pass2,gd);
    
	JLabel mob=new JLabel("Phone:");
	gd.gridx=0;
	gd.gridy=4;
	panel1.add(mob,gd);
	
	JLabel addres=new JLabel("Address:");
	gd.gridx=0;
	gd.gridy=5;
	panel1.add(addres,gd);
	
	JLabel city1=new JLabel("City:");
	gd.gridx=2;
	gd.gridy=0;
	panel1.add(city1,gd);
	
	JLabel hotel_name=new JLabel("Hotel Name:");
	gd.gridx=2;
	gd.gridy=1;
	panel1.add(hotel_name,gd);
	
	JLabel types=new JLabel("Type:");
	gd.gridx=2;
	gd.gridy=2;
	panel1.add(types,gd);
	
	JLabel StartTime=new JLabel("Start Time:");
	gd.gridx=2;
	gd.gridy=3;
	panel1.add(StartTime,gd);
	
	JLabel EndTime=new JLabel("End Time:");
	gd.gridx=2;
	gd.gridy=4;
	panel1.add(EndTime,gd);
	
	JLabel holidays=new JLabel("Holiday:");
	gd.gridx=2;
	gd.gridy=5;
	panel1.add(holidays,gd);
	
	userName=new JTextField(15);
	gd.gridx=1;
	gd.gridy=0;
	gd.insets=new Insets(10,10,10,10);
	panel1.add(userName,gd);
	
	email=new JTextField(15);
	gd.gridx=1;
	gd.gridy=1;
	panel1.add(email,gd);
	
	password=new JPasswordField(15);
	gd.gridx=1;
	gd.gridy=2;
	panel1.add(password,gd);
	
	confirmPassword=new JPasswordField(15);
	gd.gridx=1;
	gd.gridy=3;
	panel1.add(confirmPassword,gd);
	
	phone=new JTextField(15);
	gd.gridx=1;
	gd.gridy=4;
	panel1.add(phone,gd);
	
	address=new JTextArea(4,15);
	gd.gridx=1;
	gd.gridy=5;
	panel1.add(address,gd);
	
	city=new JComboBox<String>();
	city.addItem("not selected");
	Database db = new Database();
	try
	{
		ResultSet rs=db.get("select city_name from city");
	
		while(rs.next())
		{
		    city.addItem(rs.getString("city_name"));
		}
	}
	catch(Exception ex)
    {
   	   System.out.println(ex.getMessage());
    }
	city.setSelectedItem("not selected");
	gd.gridx=3;
	gd.gridy=0;
	panel1.add(city,gd);
	
	hotelName=new JTextField(15);
	gd.gridx=3;
	gd.gridy=1;
	panel1.add(hotelName,gd);
	
	String typ[]={"veg","non veg"};
	type=new JComboBox<String>(typ);
	gd.gridx=3;
	gd.gridy=2;
	Dimension d1 = new Dimension(160,30);
	type.setPreferredSize(d1);
	type.setMinimumSize(d1);
	panel1.add(type,gd);	
	
	SpinnerDateModel model = new SpinnerDateModel();
	model.setCalendarField(Calendar.MINUTE);
	startTime= new JSpinner();
	startTime.setModel(model);
	startTime.setEditor(new JSpinner.DateEditor(startTime, "hh:mm:ss a"));
	JFormattedTextField tf = ((JSpinner.DefaultEditor) startTime.getEditor()).getTextField();
	tf.setEditable(false);
	Dimension d2 = new Dimension(160,30);
	startTime.setPreferredSize(d2);
	startTime.setMinimumSize(d2);
	gd.gridx=3;
	gd.gridy=3;
	panel1.add(startTime,gd);		
	
	SpinnerDateModel model1 = new SpinnerDateModel();
	model1.setCalendarField(Calendar.MINUTE);
	closeTime= new JSpinner();
	closeTime.setModel(model1);
	closeTime.setEditor(new JSpinner.DateEditor(closeTime, "hh:mm:ss a"));
	JFormattedTextField t = ((JSpinner.DefaultEditor) closeTime.getEditor()).getTextField();
	t.setEditable(false);
	Dimension d3 = new Dimension(160,30);
	closeTime.setPreferredSize(d3);
	closeTime.setMinimumSize(d3);
	gd.gridx=3;
	gd.gridy=4;
	panel1.add(closeTime,gd);
	
	String days[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","null"};
	holiday=new JComboBox<String>(days);
	gd.gridx=3;
	gd.gridy=5;
	Dimension d4 = new Dimension(160,30);
	holiday.setPreferredSize(d4);
	holiday.setMinimumSize(d4);
	panel1.add(holiday,gd);
	
            return panel1;
    }
	
	
	public boolean register()
	{
		String query,ctime=null,stime=null;
		if(!isCustomer)
		{
		Date date = (Date)startTime.getValue();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        stime = format.format(date);
        date = (Date)closeTime.getValue();
        ctime = format.format(date);
		}
		try
		{
			Database db = new Database();
			if(isCustomer)
			{
			query="insert into user values('"+userName.getText()+"','"+String.valueOf(password.getPassword())+"','"+email.getText()+"','"+phone.getText()+"','"+(String)city.getSelectedItem()+"','"+address.getText()+"')";
			db.put(query);
			}
			else
			{
			query="insert into hotel values('"+userName.getText()+"','"+String.valueOf(password.getPassword())+"','"+hotelName.getText()+"','"+email.getText()+"','"+phone.getText()+"','"+(String)city.getSelectedItem()+"','"+address.getText()+"','"+type.getSelectedItem()+"','"+stime+"','"+ctime+"','"+holiday.getSelectedItem()+"')";
			db.put(query);
			}
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	void terminate()
	{
		frame.dispose();
	}
	
	public static void main(String[] args) {
		Register r1=new Register();
		r1.init();

	}

}