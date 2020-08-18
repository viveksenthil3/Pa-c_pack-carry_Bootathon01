package restaurant;


import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.*;


import restaurantGUI.Style;



public class Login {
    JFrame frame;
    JLabel user,pass;
    JTextField username;
    JPasswordField password;
    JButton login;
    JOptionPane popup;
    JRadioButton radio,radio1;
    
    void init(){
     frame=new JFrame("Login");
     frame.setSize(800,500);
     frame.setLayout(null);
     
     JLabel background = new JLabel(new ImageIcon("images/Login/bg.png"));
	 background.setBounds(0, 0, 800, 500);
	 frame.add(background);
	 

   
     user = Style.getLabel("Username:");     
     user.setBounds(20, 230, 150, 50);
     background.add(user);
     
     username = Style.getTextField();
     username.setBounds(140, 240, 150, 30);
     background.add(username);
     
     JSeparator split01 = new JSeparator();
     split01.setBounds(140, 270, 150, 30);
     background.add(split01);
     
     pass = Style.getLabel("Password:");
     pass.setBounds(20,280, 150, 50);
     background.add(pass);
     
     password = Style.getPasswordField();
     password.setBounds(140, 290, 150, 30);
     background.add(password);
     
     JSeparator split02 = new JSeparator();
     split02.setBounds(140, 320, 150, 30);
     background.add(split02);
     
     radio =new JRadioButton("Customer");
     radio.setBounds(50, 330, 100, 50);
     radio.setBackground(new Color(0,0,0,0));
     background.add(radio);
     
     radio1=new JRadioButton("Hotel");
     radio1.setBounds(150, 330, 100, 50);
     radio1.setBackground(new Color(0,0,0,0));
     background.add(radio1);
     
     login = Style.getButton("images/Login/loginBtnOutline.png");
     login.setBounds(55, 400, 200, 50);
     login.setEnabled(false);
     background.add(login);     
     
     ButtonGroup b=new ButtonGroup();
     b.add(radio);
     b.add(radio1);
     
     int closeSize=35;
	 JButton close = Style.getJButton("images/close.png", false, closeSize, closeSize);
	 close.setBounds(755, 15, closeSize, closeSize);
	 background.add(close);
	
	 close.addActionListener((event)->{
	 	 frame.dispose();
	 });
     
     
     
     radio.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent e) {
          if(radio.isSelected()){
             login.setEnabled(true);
             login.setIcon(new ImageIcon("images/Login/loginBtn.png"));
          }
         }
     });
      radio1.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent e) {

          if(radio1.isSelected()){
             login.setEnabled(true);
             login.setIcon(new ImageIcon("images/Login/loginBtn.png"));
          }
         }
     });
     
     login.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {

          if(isValid()){
              JOptionPane popup = null;
              System.out.println("Login Successful");
               JOptionPane.showMessageDialog(frame, "Login Successful"); 
               terminate();

               if(radio.isSelected()) {
            	   new CustomerHotel().init(username.getText());
               }
               else {
            	   new HotelOrder().init(username.getText());
               }
          }
          else{
               popup =new JOptionPane("Invalid Username or Password");
              JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
             popup.setSize(200,200);
              popup.setBounds(500, 500,300,200);
              frame.add(popup);
               frame.setVisible(true);
            popup.setVisible(true);
          }
        }
     });
     
     frame.setLocationRelativeTo(null);
     frame.setUndecorated(true);
     frame.setResizable(false);
     frame.setVisible(true);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
   boolean isValid(){
       int flag=0;
	String p=String.valueOf(password.getPassword());
	String query = null;
	if(radio.isSelected()){
	query="select * from user where user_name='"+username.getText()+"'and password='"+p+"';";    
	}
	if(radio1.isSelected()){
	     query="select * from hotel where huser_name='"+username.getText()+"'and password='"+p+"';";
	}
      try(ResultSet rs = new Database().get(query);){
			if(rs.next()) {
			System.out.println("Login granted");
                        flag =1;
                        return true;
                        }
                        else{
                        System.out.println("Login not granted");
                      return false;
                        }
		}catch(Exception e) {
			System.out.println(e.getMessage());
                        return false;
		}
   } 
   
   void terminate()
	{
		frame.dispose();
	}
    public static void main(String a[]){
        new Login().init();
    }
}

