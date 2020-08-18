//
//package training;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//import java.awt.event.*;
//import java.util.Date;
//import java.sql.ResultSet;
//import java.text.SimpleDateFormat;
//import javax.swing.*;
//
//import java.util.Calendar;
//public class Register {
//	JTextField userName;
//	JTextField email;
//	JPasswordField password;
//	JPasswordField confirmPassword;
//	JTextField phone;
//	JTextArea address;
//	JTextField city;
//	JTextField hotelName;
//	JComboBox type;
//	JSpinner startTime;
//	JSpinner closeTime;
//	JComboBox holiday;
//	JRadioButton users;
//	JRadioButton hotels;
//	JFrame frame;
//	void init()
//	{
//		frame = new JFrame();
//		frame.setLayout(new GridBagLayout());
//		frame.setSize(1000,1500);
//		GridBagConstraints gc = new GridBagConstraints();
//		gc.fill = GridBagConstraints.BOTH;
//		gc.weightx=0.8;
//		gc.weighty=0.8;		
//		JPanel panel0 = new JPanel(new GridBagLayout());
//		GridBagConstraints gcp0 = new GridBagConstraints();
//		gcp0.fill = GridBagConstraints.BOTH;
//		gcp0.weightx=1.0;
//		gcp0.weighty=1.0;
//		gc.gridx=0; gc.gridy=0;
//		frame.add(panel0, gc);
//		JPanel panel3 = new JPanel(new GridBagLayout());
//		GridBagConstraints gcp3 = new GridBagConstraints();
//		gcp3.gridx=0;
//		gcp3.gridy=0;
//		gc.weightx=0.2;
//		gc.weighty=0.2;	
//		gc.gridx=0; gc.gridy=1;
//		frame.add(panel3, gc);
//		users =new JRadioButton("Customer");   
//		hotels =new JRadioButton("Hotel");
//		ButtonGroup bg=new ButtonGroup();    
//		bg.add(users);
//		bg.add(hotels);
//		panel3.add(users,gcp3);
//		gcp3.gridx=1; gcp3.gridy=0;
//		panel3.add(hotels,gcp3);
//		JButton b=new JButton("Register");
//		b.setBounds(410,850,100,30);
//		b.setEnabled(false);
//		users.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//            	JPanel panel= userMenu();
//            	panel0.removeAll();
//    			panel0.add(panel, gcp0);
//    			panel0.validate();
//    			panel0.repaint();
//                b.setEnabled(true);
//            }
//        });
//		hotels.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//            	JPanel panel1 = hotelMenu();
//            	panel0.removeAll();
//    			panel0.add(panel1, gcp0);
//    			panel0.validate();
//    			panel0.repaint();
//                b.setEnabled(true);
//            }
//        });
//		b.addActionListener(new ActionListener() {  
//            public void actionPerformed(ActionEvent e) {
//            if(users.isSelected())
//            {
//            	String s3=String.valueOf(password.getPassword());
//                String s4=String.valueOf(confirmPassword.getPassword());
//                boolean flag=true;
//                
//                if(!userName.getText().isEmpty())
//                {
//                	try
//                	{
//                	ResultSet rs=new Database().get("select user_name from user where user_name='"+userName.getText()+"'");
//                    if(rs.next())
//                    {
//                    	flag=false;
//                    	JOptionPane.showMessageDialog(frame,"Username already exist","Error Message",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
//                	}
//                	catch(Exception en)
//                	{
//                		System.out.println(en.getMessage());
//                		
//                	}
//                }
//                if(!email.getText().isEmpty())
//                {
//                	try
//                	{
//                	ResultSet rs=new Database().get("select email from user where email='"+email.getText()+"'");
//                    if(rs.next())
//                    {
//                    	flag=false;
//                    	JOptionPane.showMessageDialog(frame,"Email Id already exist","Error Message",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
//                	}
//                	catch(Exception en)
//                	{
//                		System.out.println(en.getMessage());
//                		
//                	}
//                }
//                if(userName.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"User Name is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(email.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Email Id is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(String.valueOf(password.getPassword()).isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Password is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(String.valueOf(confirmPassword.getPassword()).isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Confirm Password is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(phone.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Mobile number is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(address.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Address is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(city.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"city is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(s3.equals(s4) && flag)
//           		{
//           			System.out.println("Success");
//           			register();
//           			JOptionPane.showMessageDialog(frame,"Successfully Registered");
//           		}
//           		else if(!flag)
//           		{
//           			System.out.println("Failed");
//           			JOptionPane.showMessageDialog(frame,"Wrong password","Error Message",
//           	                JOptionPane.ERROR_MESSAGE);
//           		}
//               
//            
//            	
//            	
//            }
//            else if(hotels.isSelected())
//            {
//            	String s3=String.valueOf(password.getPassword());
//                String s4=String.valueOf(confirmPassword.getPassword());
//                boolean flag=true;
//                
//                if(!userName.getText().isEmpty())
//                {
//                	try
//                	{
//                	ResultSet rs=new Database().get("select huser_name from hotel where huser_name='"+userName.getText()+"'");
//                    if(rs.next())
//                    {
//                    	flag=false;
//                    	JOptionPane.showMessageDialog(frame,"Username already exist","Error Message",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
//                	}
//                	catch(Exception en)
//                	{
//                		System.out.println(en.getMessage());
//                		
//                	}
//                }
//                else if(!email.getText().isEmpty())
//                {
//                	try
//                	{
//                	ResultSet rs=new Database().get("select email from hotel where email='"+email.getText()+"'");
//                    if(rs.next())
//                    {
//                    	flag=false;
//                    	JOptionPane.showMessageDialog(frame,"Email Id already exist","Error Message",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
//                	}
//                	catch(Exception en)
//                	{
//                		System.out.println(en.getMessage());
//                		
//                	}
//                }
//                if(userName.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"User Name is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(email.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Email Id is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(String.valueOf(password.getPassword()).isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Password is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(String.valueOf(confirmPassword.getPassword()).isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Confirm Password is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(phone.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Mobile number is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(address.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Address is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(city.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"city is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(hotelName.getText().isEmpty())
//                {
//                	JOptionPane.showMessageDialog(frame,"Hotelname is missing","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(type.getSelectedIndex()==-1)
//                {
//                	JOptionPane.showMessageDialog(frame,"select any type","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(holiday.getSelectedIndex()==-1)
//                {
//                	JOptionPane.showMessageDialog(frame,"select day","Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                else if(s3.equals(s4) && flag)
//           		{
//           			System.out.println("Success");
//           			if(register()) {
//           				JOptionPane.showMessageDialog(frame,"Successfully Registered");
//           				new Login().init();
//           				terminate();
//           			}
//           			else
//           				JOptionPane.showMessageDialog(frame,"sorry something went wrong");
//           		}
//           		else if(!flag)
//           		{
//           			System.out.println("Failed");
//           			JOptionPane.showMessageDialog(frame,"Wrong password");
//           		}
//            	
//            }
//        		}     
//		
//  
//         });
//		gcp3.gridx=0; gcp3.gridy=1;
//		gcp3.gridwidth=2;
//		panel3.add(b,gcp3);
//		frame.setSize(1000,1500);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//	JPanel userMenu()
//	{  
//		JPanel panel = new JPanel(new GridBagLayout());
////		panel.setBackground(Color.pink); 
//		GridBagConstraints g = new GridBagConstraints();
//		JLabel user=new JLabel("UserName:");
//		g.gridx=0;
//		g.gridy=1;
//		g.insets=new Insets(10,10,10,10);
//		panel.add(user,g);
//		JLabel eid=new JLabel("Email:");
//		g.gridx=0;
//		g.gridy=2;
//		panel.add(eid,g);
//		JLabel pass1=new JLabel("Password:");
//		g.gridx=0;
//		g.gridy=3;
//		panel.add(pass1,g);
//		JLabel pass2=new JLabel("Confirm Password");
//		g.gridx=0;
//		g.gridy=4;
//		panel.add(pass2,g);
//		JLabel mob=new JLabel("Phone:");
//		g.gridx=0;
//		g.gridy=5;
//		panel.add(mob,g);
//		JLabel addres=new JLabel("Address:");
//		g.gridx=0;
//		g.gridy=6;
//		panel.add(addres,g);
//		JLabel city1=new JLabel("City:");
//		g.gridx=0;
//		g.gridy=7;
//		panel.add(city1,g);
//		userName=new JTextField(15);
//		g.gridx=1;
//		g.gridy=1;
//		g.insets=new Insets(10,10,10,10);
//		panel.add(userName,g);
//		email=new JTextField(15);
//		g.gridx=1;
//		g.gridy=2;
//		panel.add(email,g);
//		password=new JPasswordField(15);
//		g.gridx=1;
//		g.gridy=3;
//		panel.add(password,g);
//		confirmPassword=new JPasswordField(15);
//		g.gridx=1;
//		g.gridy=4;
//		panel.add(confirmPassword,g);
//		phone=new JTextField(15);
//		g.gridx=1;
//		g.gridy=5;
//		panel.add(phone,g);
//		address=new JTextArea(4,15);
//		g.gridx=1;
//		g.gridy=6;
//		panel.add(address,g);
//		city=new JTextField(15);
//		g.gridx=1;
//		g.gridy=7;
//		panel.add(city,g);
//		return panel;
//	}
//	JPanel hotelMenu()
//	{
//		JPanel panel1 = new JPanel(new GridBagLayout());
////		panel1.setBackground(Color.pink);
//		GridBagConstraints gd = new GridBagConstraints();
//		JLabel user=new JLabel("UserName:");
//		gd.gridx=0;
//		gd.gridy=1;
//		gd.insets=new Insets(10,10,10,10);
//		panel1.add(user,gd);
//		JLabel eid=new JLabel("Email:");
//		gd.gridx=0;
//		gd.gridy=2;
//		panel1.add(eid,gd);
//		JLabel pass1=new JLabel("Password:");
//		gd.gridx=0;
//		gd.gridy=3;
//		panel1.add(pass1,gd);
//		JLabel pass2=new JLabel("Confirm Password");
//		gd.gridx=0;
//		gd.gridy=4;
//		panel1.add(pass2,gd);
//		JLabel mob=new JLabel("Phone:");
//		gd.gridx=0;
//		gd.gridy=5;
//		panel1.add(mob,gd);
//		JLabel addres=new JLabel("Address:");
//		gd.gridx=0;
//		gd.gridy=6;
//		panel1.add(addres,gd);
//		JLabel city1=new JLabel("City:");
//		gd.gridx=0;
//		gd.gridy=7;
//		panel1.add(city1,gd);
//		JLabel hotel_name=new JLabel("Hotel Name:");
//		gd.gridx=0;
//		gd.gridy=8;
//		panel1.add(hotel_name,gd);
//		JLabel types=new JLabel("Type:");
//		gd.gridx=0;
//		gd.gridy=9;
//		panel1.add(types,gd);
//		JLabel StartTime=new JLabel("Start Time:");
//		gd.gridx=0;
//		gd.gridy=10;
//		panel1.add(StartTime,gd);
//		JLabel EndTime=new JLabel("End Time:");
//		gd.gridx=0;
//		gd.gridy=11;
//		panel1.add(EndTime,gd);
//		JLabel holidays=new JLabel("Holiday:");
//		gd.gridx=0;
//		gd.gridy=12;
//		panel1.add(holidays,gd);
//		userName=new JTextField(15);
//		gd.gridx=1;
//		gd.gridy=1;
//		gd.insets=new Insets(10,10,10,10);
//		panel1.add(userName,gd);
//		email=new JTextField(15);
//		gd.gridx=1;
//		gd.gridy=2;
//		panel1.add(email,gd);
//		password=new JPasswordField(15);
//		gd.gridx=1;
//		gd.gridy=3;
//		panel1.add(password,gd);
//		confirmPassword=new JPasswordField(15);
//		gd.gridx=1;
//		gd.gridy=4;
//		panel1.add(confirmPassword,gd);
//		phone=new JTextField(15);
//		gd.gridx=1;
//		gd.gridy=5;
//		panel1.add(phone,gd);
//		address=new JTextArea(4,15);
//		gd.gridx=1;
//		gd.gridy=6;
//		panel1.add(address,gd);
//		city=new JTextField(15);
//		gd.gridx=1;
//		gd.gridy=7;
//		panel1.add(city,gd);
//		hotelName=new JTextField(15);
//		gd.gridx=1;
//		gd.gridy=8;
//		panel1.add(hotelName,gd);
//		String typ[]={"Veg","non veg"};
//		type=new JComboBox(typ);
//		gd.gridx=1;
//		gd.gridy=9;
//		Dimension d1 = new Dimension(160,30);
//		type.setPreferredSize(d1);
//		type.setMinimumSize(d1);
//		panel1.add(type,gd);		
//		SpinnerDateModel model = new SpinnerDateModel();
//		model.setCalendarField(Calendar.MINUTE);
//		startTime= new JSpinner();
//		startTime.setModel(model);
//		startTime.setEditor(new JSpinner.DateEditor(startTime, "hh:mm:ss"));
//		JFormattedTextField tf = ((JSpinner.DefaultEditor) startTime.getEditor()).getTextField();
//		tf.setEditable(false);
//		Dimension d2 = new Dimension(160,30);
//		startTime.setPreferredSize(d2);
//		startTime.setMinimumSize(d2);
//		gd.gridx=1;
//		gd.gridy=10;
//		panel1.add(startTime,gd);		
//		SpinnerDateModel model1 = new SpinnerDateModel();
//		model1.setCalendarField(Calendar.MINUTE);
//		closeTime= new JSpinner();
//		closeTime.setModel(model1);
//		closeTime.setEditor(new JSpinner.DateEditor(closeTime, "hh:mm:ss"));
//		JFormattedTextField t = ((JSpinner.DefaultEditor) closeTime.getEditor()).getTextField();
//		t.setEditable(false);
//		Dimension d3 = new Dimension(160,30);
//		closeTime.setPreferredSize(d3);
//		closeTime.setMinimumSize(d3);
//		gd.gridx=1;
//		gd.gridy=11;
//		panel1.add(closeTime,gd);
//		String days[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
//		holiday=new JComboBox(days);
//		gd.gridx=1;
//		gd.gridy=12;
//		Dimension d4 = new Dimension(160,30);
//		holiday.setPreferredSize(d4);
//		holiday.setMinimumSize(d4);
//		panel1.add(holiday,gd);
//		return panel1;
//		
//	}
//	public boolean register()
//	{
//		String query;
//		Date date = (Date)startTime.getValue();
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
//        String stime = format.format(date);
//        date = (Date)closeTime.getValue();
//        String ctime = format.format(date);
//		try
//		{
//			Database db = new Database();
//			if(users.isSelected())
//			{
//			query="insert into user values('"+userName.getText()+"','"+String.valueOf(password.getPassword())+"','"+email.getText()+"','"+phone.getText()+"','"+city.getText()+"','"+address.getText()+"')";
//			db.put(query);
//			}
//			else if(hotels.isSelected())
//			{
//			query="insert into hotel values('"+userName.getText()+"','"+String.valueOf(password.getPassword())+"','"+hotelName.getText()+"','"+email.getText()+"','"+phone.getText()+"','"+city.getText()+"','"+address.getText()+"','"+type.getSelectedItem()+"','"+stime+"','"+ctime+"','"+holiday.getSelectedItem()+"')";
//			db.put(query);
//			}
//			return true;
//		}
//		catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//			return false;
//		}
//	}
//	
//	void terminate()
//	{
//		frame.dispose();
//	}
//	
//	public static void main(String[] args) {
//		Register r1=new Register();
//		r1.init();
//
//
//	}
//
//}
//
