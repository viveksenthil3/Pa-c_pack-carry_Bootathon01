package authentication;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Login {
    JFrame lf;
    JLabel user,pass;
    JTextArea username;
    JPasswordField password;
    JButton login;
    JOptionPane popup;
    void init(){
     lf=new JFrame("Login");
     lf.setSize(600,600);
     lf.setLayout(null);
     user = new JLabel("Username:");
     user.setBounds(50, 100, 150, 50);
     lf.add(user);
     username = new JTextArea();
     username.setBounds(140, 110, 150, 30);
     lf.add(username);
     pass = new JLabel("Password:");
     pass.setBounds(50,150, 150, 50);
     lf.add(pass);
     password =new JPasswordField();
     password.setBounds(140, 160, 150, 30);
     lf.add(password);
     
     login = new JButton("Login");
     login.setBounds(150, 300, 150, 50);
     lf.add(login);
     login.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            if(true){
                try {
                    isValid();
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
         }
     });
     lf.setVisible(true);
    }
    void isValid() throws SQLException{
        int flag=0;
        String p =String.valueOf(password.getPassword());
       Connection con = Database.connect();
      Statement st = con.createStatement();
      String query ="select *from students";
      ResultSet r=st.executeQuery(query);
      while(r.next()){
           if(username.getText().equals(r.getString("user_name"))){
          if(p.equals(r.getString("password"))){
               System.out.println("Login Successful");
               JOptionPane.showMessageDialog(lf, "Login Successful");
               
               //popup.setSize(200,200);
//               popup.setBounds(500, 500,300,200);
//               lf.add(popup);
//               lf.setVisible(true);
               flag++;
               break;
           }
      } 
        }
      if(flag==0){
          System.out.println("Wrong credentials");
               popup =new JOptionPane("Invalid Username or Password");
               JOptionPane.showMessageDialog(lf, "Invalid Username or Password");
//               popup.setSize(200,200);
//               popup.setBounds(500, 500,300,200);
//               lf.add(popup);
//               lf.setVisible(true);
               //popup.setVisible(true);
      }
    }
    public static void main(String a[]){
        new Login().init();
    }
}

