package restaurant;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartUtilities;


public class HotelSettings extends HDashboard {
    JPanel mp;
    Database db = new Database();
    Register regObj=new Register();
    
    @Override
    public JScrollPane setMainPanel(){
                JPanel panel2= regObj.hotelMenu();
                GridBagConstraints gc1= new GridBagConstraints();                
                gc1.weightx=gc1.weighty=0.5;
                
                JButton save = new JButton("Save Changes");
                gc1.gridx=1;
                gc1.gridy=6;                
                panel2.add(save,gc1);
                
                JButton delete = new JButton("Delete");
                gc1.gridx=2;
                panel2.add(delete,gc1);
                
                JPanel dishesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                dishesPanel.setBorder(BorderFactory.createTitledBorder("Dishes Settings"));
                gc1.gridx=0;gc1.gridy=7;
                gc1.gridwidth=4;gc1.gridheight=3;
                gc1.fill=GridBagConstraints.HORIZONTAL;
                panel2.add(dishesPanel, gc1);
                
                JButton export = new JButton("Export Dishes");
                dishesPanel.add(export);
                
                JButton importBtn = new JButton("Import Dishes");
                dishesPanel.add(importBtn);
                
                JScrollPane s1= new JScrollPane(panel2);
                ResultSet rs = db.get("select* from hotel where huser_name ='"+userName+"';");
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            while(rs.next()){
                regObj.userName.setText(rs.getString("huser_name"));
                regObj.userName.setEnabled(false);
                regObj.password.setText(rs.getString("password"));
                regObj.confirmPassword.setText(rs.getString("password"));        
                regObj.phone.setText(rs.getString("phone"));
                regObj.email.setText(rs.getString("email"));
                regObj.address.setText(rs.getString("address"));
                regObj.hotelName.setText(rs.getString("hotel_name"));
                regObj.city.setSelectedItem(rs.getString("city"));
                regObj.type.setSelectedItem(rs.getString("type"));
                regObj.holiday.setSelectedItem(rs.getString("holiday"));
                regObj.startTime.setValue(format.parseObject(rs.getString("start_time")));
                regObj.closeTime.setValue(format.parseObject(rs.getString("close_time")));
		
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(HotelSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(regObj.validateAndRegisterHotel(false)){
                             int result = JOptionPane.showConfirmDialog(frame,"Update Changes?", "Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                         if(result == JOptionPane.YES_OPTION){
                            String query;
                            Date date =(Date)regObj.startTime.getValue();
                            SimpleDateFormat format= new SimpleDateFormat("HH:mm:ss");
                            String stime=format.format(date);
                                
                            date = (Date)regObj.closeTime.getValue();
                            String ctime=format.format(date);
                            query="UPDATE hotel SET huser_name = '"+regObj.userName.getText()+"', password = '"+String.valueOf(regObj.password.getPassword())+"', hotel_name = '"+regObj.hotelName.getText()+"', email = '"+regObj.email.getText()+"', phone = '"+regObj.phone.getText()+"', city = '"+(String)regObj.city.getSelectedItem()+"', address = '"+regObj.address.getText()+"',holiday = '"+(String)regObj.holiday.getSelectedItem()+"',type = '"+(String)regObj.type.getSelectedItem()+"',start_time='"+stime+"',close_time= '"+ctime+"' WHERE (huser_name = '"+userName+"');";
                             db.put(query);
                             JOptionPane.showMessageDialog(frame, "Your account is updated");
                         }
                             
                        }
                    }
                });
        
        delete.addActionListener(new ActionListener() {  
                public void actionPerformed(ActionEvent e) {
                	int result = JOptionPane.showConfirmDialog(frame,"Are You sure to delete your account", "Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                         if(result == JOptionPane.YES_OPTION){
                        	 Database db = new Database();
                         	String query;
                         	
                         	try
                             {
                            	  query = "Delete from hotel WHERE huser_name = '"+userName+"';";
                            	  db.put(query);
                            	  JOptionPane.showMessageDialog(frame, "Successfully Deleted this Account");
                            	  new Main().init();
                            	  terminate();
                             }
                             catch(Exception ex)
                             {
                            	   System.out.println(ex.getMessage());
                             }
                         }
                }
                });
        
        export.addActionListener((event)->{
        	Database db= new Database();
    		
        	JFileChooser fileChooser = new JFileChooser();
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv","csv");
    		fileChooser.setFileFilter(filter);
    		int i = fileChooser.showDialog(frame, "Export");
    		
    		if(i==JFileChooser.APPROVE_OPTION) {
    			File file = fileChooser.getSelectedFile();
//    			System.out.println(file.getAbsolutePath());
    			
    			try(CSV csv = new CSV();) {
        			ResultSet rs1=db.get("select * from dishes where huser_name='"+this.userName+"'");
        			csv.writeCSV(file.getAbsolutePath(), "name", "type", "description", "price");
        			
        			
        			while(rs1.next())
        	        {
        		        csv.writeRow(rs1.getString("name"), rs1.getString("type"), rs1.getString("description"), rs1.getString("price"));        		        
        	        }
        			
        			JOptionPane.showMessageDialog(null, "Your dishes has been Exported \n Successful");

        		}
        		catch(Exception e) {
        			System.out.println(e.getMessage());
        		}

    			
    		}

    		
        });
        
        
        importBtn.addActionListener((event)->{
        	Database db= new Database();
    		
        	JFileChooser fileChooser = new JFileChooser();
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv","csv");
    		fileChooser.setFileFilter(filter);

    		int i = fileChooser.showDialog(frame, "Import");
    		
    		if(i==JFileChooser.APPROVE_OPTION) {
    			File file = fileChooser.getSelectedFile();

    			
    			try(CSV csv = new CSV();) {
    				if(csv.readCSV(file.getAbsolutePath(), "name", "type", "description", "price")) {
    					
    					List<List<String>> rows = csv.readRow();
    					

    					for(List<String> row : rows) {
    						db.put(String.format("insert into dishes (huser_name, name, type, description, price,time_taken) values('%s', '%s', '%s', '%s', %s,'%s');",this.userName, row.get(0), row.get(1), row.get(2), row.get(3), "00:00:00"));
    					}
    					JOptionPane.showMessageDialog(frame, "Your dishes has been Imported \n Successful");
    				}
    				else {
    					JOptionPane.showMessageDialog(frame, "Please select a valid csv file", JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
    				}
        			

        		}
        		catch(Exception e) {
        			System.out.println(e.getMessage());
        		}

    			
    		}

    		
        });
        
        return s1;
        
    }
    
    public static void main(String args[]){
        HotelSettings hs = new HotelSettings();
        hs.init("mukhesh");
    }
}
