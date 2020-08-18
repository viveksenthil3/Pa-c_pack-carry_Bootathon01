package restaurant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import restaurantGUI.Style;


public class CustomerHotel extends CDashboard {
    JPanel panel,sp1;
    Database db =new Database();
    JLabel hname,htype,hopen,hclose,hphone,haddress;
    ArrayList<String> husers=new ArrayList<String>();
    String huser;
    JButton view;
    
    @Override
    public JScrollPane setMainPanel(){
            JPanel p= new JPanel();
            DefaultTableModel mod = new DefaultTableModel();
            JTable jt=new JTable(mod){
                public boolean isCellEditable(int row,int column){
                    return false;
                }
            };
            Date date =new Date();
            String[] days = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
            
         String a= days[date.getDay()];
         
         DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
         LocalDateTime currentTime = LocalDateTime.now();

            mod.addColumn("S.no");
            mod.addColumn("Hotel Name");
            mod.addColumn("Type");
            mod.addColumn("Phone Number");
            Object[] array= new Object[4];
            try(ResultSet rs =db.get("select huser_name, h.password, hotel_name, h.email, h.phone, h.city, h.address, type, start_time, close_time, holiday from hotel h inner join user u on h.city=u.city where u.user_name='"+this.userName+"'and h.holiday!='"+a+"' and h.start_time<='"+timeFormat.format(currentTime)+"' and '"+timeFormat.format(currentTime)+"'<=h.close_time;");){    
                int i=1;
			while(rs.next()) {
                            array[0]=i;
                            husers.add(rs.getString("huser_name"));
                            array[1]=rs.getString("hotel_name");
                            array[2]=rs.getString("type");
                            array[3]=rs.getString("phone");
                            mod.addRow(array);
                            i++;
                        }
                      }
            catch(Exception e) {
			System.out.println(e.getMessage());
		}			
            JScrollPane sp=new JScrollPane(jt);
            ListSelectionModel sel=jt.getSelectionModel();
            sel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
               
                    if(!sel.isSelectionEmpty()){
                        view.setEnabled(true);
                        int selrow=sel.getMinSelectionIndex();
                        try {ResultSet rs =db.get(String.format("select * from  hotel where huser_name='%s';",husers.get(selrow)));
                        while(rs.next()){
                            hname.setText("Hotel Name: "+rs.getString("hotel_name"));
                            htype.setText("Type: "+rs.getString("type"));
                            hopen.setText("Opening Time:"+rs.getString("start_time"));
                            hclose.setText("Closing Time: "+rs.getString("close_time"));
                            haddress.setText("Address: "+rs.getString("address"));
                            hphone.setText("Phone Number: "+rs.getString("phone"));
                            huser = rs.getString("huser_name");
                        
                        }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                    }
                }
                    
            });                             
        return sp; 
    }   
    
    @Override
    public JScrollPane setSidePanelAction(){
        sp1= new JPanel(new GridBagLayout());
        sp1=(JPanel)Style.setBackground(sp1, Style.PRIMARY_COLOR);
        sp1=(JPanel)Style.setBackground(sp1, Style.PRIMARY_COLOR);
        GridBagConstraints gc= new GridBagConstraints();
        gc.fill=GridBagConstraints.BOTH;
        gc.insets=new Insets(10,10,10,10);
        JScrollPane spc=new JScrollPane();
        gc.gridx=0;
        gc.gridy=0;
        hname=new JLabel("Hotel Name:");
        hname=Style.setLabelStyle(hname);
        sp1.add(hname,gc);
        gc.gridx=0;
        gc.gridy=1;
        htype= new JLabel("Type:");
        htype=Style.setLabelStyle(htype);
        sp1.add(htype,gc);
        gc.gridx=0;
        gc.gridy=2;
        hopen=new JLabel("Opening Time:");
        hopen=Style.setLabelStyle(hopen);
        sp1.add(hopen,gc);
        gc.gridx=0;
        gc.gridy=3;
        hclose =new JLabel("Closing Time:");
        hclose=Style.setLabelStyle(hclose);
        sp1.add(hclose,gc);
        gc.gridx=0;
        gc.gridy=4;
        haddress=new JLabel("Address:");
        haddress=Style.setLabelStyle(haddress);
        sp1.add(haddress,gc);
        gc.gridx=0;
        gc.gridy=5;
        hphone=new JLabel("Phone Number:");
        hphone=Style.setLabelStyle(hphone);
        sp1.add(hphone,gc);
        view =new JButton("View Menu");
        view.setEnabled(false);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerMenu cm = new CustomerMenu(huser);
                cm.init(userName);
                terminate();
            }
        });
        gc.gridx=0;
        gc.gridy=6;
        sp1.add(view,gc);
        spc=new JScrollPane(sp1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return spc;
    }
    
    @Override
    public String setHomeButton() {
		return "images/CDashboard/Home btn(selected).png";
	}
    
    public static void main(String args[]){
       CustomerHotel c=new CustomerHotel();
       c.init("mvn3");
    }
}
