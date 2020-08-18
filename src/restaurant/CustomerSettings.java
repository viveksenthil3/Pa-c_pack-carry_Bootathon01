package restaurant;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class CustomerSettings extends CDashboard {	
	
	Register regObj = new Register();
	
	@Override
	public JScrollPane setMainPanel()
	{
		JPanel panel = regObj.customerMenu();
		
		
		GridBagConstraints g = new GridBagConstraints();		
		
		JButton save=new JButton("Save Changes");
		g.gridx=0;
		g.gridy=8;
		panel.add(save,g);
		
		JButton delete=new JButton("Delete");
		g.gridx=1;
		g.gridy=8;
		panel.add(delete,g);
		
		Database db = new Database();
		try
        {
       	  ResultSet rs=db.get("select * from user where user_name = '"+this.userName+"';");
           
       	  while(rs.next())
       	  {
             regObj.userName.setText(rs.getString("user_name")); 
             regObj.userName.setEditable(false);
             regObj.email.setText(rs.getString("email"));
             regObj.password.setText(rs.getString("password"));
             regObj.confirmPassword.setText(rs.getString("password"));
             regObj.phone.setText(rs.getString("phone"));
             regObj.address.setText(rs.getString("address"));
             regObj.city.setSelectedItem(rs.getString("city"));
       	  }
             
        }
        catch(Exception ex)
        {
       	   System.out.println(ex.getMessage());
        }
	
		save.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {

            	
                 if(regObj.validateAndRegisterCustomer(false)){
                	 int result = JOptionPane.showConfirmDialog(frame,"Sure? You want to Save Changes?", "Swing Tester",
                             JOptionPane.YES_NO_OPTION,
                             JOptionPane.QUESTION_MESSAGE);
            	if(result == JOptionPane.YES_OPTION)
            	{
            		Database db = new Database();
                	String query;
                	try
                    {
                   	  query ="UPDATE user SET  user_name = '"+regObj.userName.getText()+"' , password = '"+String.valueOf(regObj.password.getPassword())+"' , email = '"+regObj.email.getText()+"' , phone = '"+regObj.phone.getText()+"' , city = '"+(String)regObj.city.getSelectedItem()+"', address = '"+regObj.address.getText()+"' WHERE user_name = '"+userName+"';";
                   	  db.put(query); 
                   	  
                   	  JOptionPane.showMessageDialog(frame, "Changes has been Saved Successfully");
              	  
                    }
                    catch(Exception ex)
                    {
                   	   System.out.println(ex.getMessage());
                    }
            	}
            }
            }
            });
		
            delete.addActionListener(new ActionListener() {  
                public void actionPerformed(ActionEvent e) {
                	int result = JOptionPane.showConfirmDialog(frame,"Sure? You want to delete?", "Swing Tester",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                         if(result == JOptionPane.YES_OPTION){
                        	 Database db = new Database();
                         	String query;
                         	
                         	try
                             {
                            	  query = "Delete from user WHERE user_name = '"+userName+"';";
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
                
		return new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	

	public static void main(String[] args) {
		
		CustomerSettings cs = new CustomerSettings();
		cs.init("mvn6");
		
		
	}

}
