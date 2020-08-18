package restaurant;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.*;  
import javax.swing.table.*;
import restaurantGUI.Style;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HotelMenu extends HDashboard {
	JPanel panel1;
	JPanel panel2;
	JTable table1;
	DefaultTableModel model;
	JTextField name;
	JComboBox<String> type;
	JTextArea description;
	JSpinner price;
	JSpinner timetaken;
	JButton b1,b2,b3;
	int row;
	
	ArrayList<String> dishId=new ArrayList<String>();
	
	
	public void updateTable()
	{
		int no_of_rows = model.getRowCount();
		for(int i=no_of_rows-1;i>=0;i--)
		{
			model.removeRow(i);
		}
		
		Database db= new Database();
		
		try
    	{
    	ResultSet rs=db.get("select * from dishes where huser_name='"+this.userName+"'");
    	int sno=1;
    	
    	dishId.clear();
    	
    	while(rs.next())
        {
            dishId.add(rs.getString("dish_id"));
    		
	        String name= rs.getString("name");
	        String type= rs.getString("type");
	        String price = rs.getString("price");
	   	        
	        model.addRow(new Object[]{sno,name, type,price});
	        sno++;
	       
        }
        }
		
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	
	@Override
	public JScrollPane setMainPanel()
	{
		
		String columnNames[] = {"S.no","Name", "Type","Price"};
		
	    model = new DefaultTableModel();
		table1 = new JTable(model) {
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		};
		model.setColumnIdentifiers(columnNames);
		table1.setFont(new Font("Segoe UI", 4, 13));
		table1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table1.getColumnModel().getColumn(2).setPreferredWidth(50);
		table1.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		
		Database db= new Database();
		updateTable();
						
		
		ListSelectionModel sel=table1.getSelectionModel();
        sel.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) 
        {
        	if(!sel.isSelectionEmpty())
        	{

        		row=sel.getMinSelectionIndex();

        		b2.setEnabled(true);
        		b3.setEnabled(true);
        		b1.setText("Deselect All");
//        		b1.setIcon(new ImageIcon("images/HotelMenu/Deselectall btn.png"));
        		
                try
                 {
                	  ResultSet rs=db.get("select * from dishes where dish_id = "+dishId.get(row)+";");
                      if(rs.next())
                      {
                      name.setText(rs.getString("name")); 
                      type.setSelectedItem(rs.getString("type"));
                      description.setText(rs.getString("description"));
                      price.setValue(Double.valueOf(rs.getString("price")));
                      
                      }
                 }
                 catch(Exception ex)
                 {
                	   System.out.println(ex.getMessage());
                 }   
        	}
                
        	else if(sel.isSelectionEmpty())
        	{
        		b1.setText("Add Dish");
//        		b1.setIcon(new ImageIcon("images/HotelMenu/addDish btn.png"));
        		b2.setEnabled(false);
        		b3.setEnabled(false);
        		name.setText(""); 
                type.setSelectedItem("");
                description.setText("");
                price.setValue(Integer.valueOf("40"));               
                
        	}
        }
       });
               
		return new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	@Override
	public JScrollPane setSidePanelAction()
	{
		panel2 = new JPanel(new GridBagLayout());
		panel2=(JPanel)Style.setBackground(panel2, Style.PRIMARY_COLOR);
		GridBagConstraints g = new GridBagConstraints();
		
		JLabel n = new JLabel("Name");
		n=Style.setLabelStyle(n);
		g.gridx=0;
		g.gridy=1;
		g.insets=new Insets(10,10,10,10);
		panel2.add(n,g);
		
		JLabel t = new JLabel("Type");
		t=Style.setLabelStyle(t);
		g.gridx=0;
		g.gridy=2;
		panel2.add(t,g);
		
		JLabel d = new JLabel("Description");
		d=Style.setLabelStyle(d);
		g.gridx=0;
		g.gridy=3;
		panel2.add(d,g);
		
		JLabel p = new JLabel("Price");
		p=Style.setLabelStyle(p);
		g.gridx=0;
		g.gridy=4;
		panel2.add(p,g);
						
		name = new JTextField(10);
		g.gridx=1;
		g.gridy=1;
		g.insets=new Insets(10,10,10,10);
		panel2.add(name,g);
		
		String types[]= {"veg","non veg"};
		type = new JComboBox<String>(types);
		Dimension d1 = new Dimension(110,30);
		type.setPreferredSize(d1);
		g.gridx=1;
		g.gridy=2;
		panel2.add(type,g);
		
		description = new JTextArea(2,15);
		g.gridx=1;
		g.gridy=3;
		description.setLineWrap(true);
	    description.setWrapStyleWord(true);
		panel2.add(description,g);

		SpinnerModel sm = new SpinnerNumberModel(40,40,2500,1);
		price = new JSpinner(sm);
		Dimension d3 = new Dimension(110,30);
		price.setPreferredSize(d3);
		g.gridx=1;
		g.gridy=4;
		panel2.add(price,g);	

		b1=new JButton("Add Dish");//images/HotelMenu/Deselectall btn.png
//		b1=Style.getJButton("images/HotelMenu/addDish btn.png", false);
		g.gridx=0;
		g.gridy=10;
		panel2.add(b1,g);
		
//		b2=new JButton("Update Dish");
		b2=Style.getJButton("images/HotelMenu/updateDish btn.png", false);
		g.gridx=1;
		g.gridy=10;
		panel2.add(b2,g);
		
//		b3=new JButton("Delete");
		b3=Style.getJButton("images/HotelMenu/delete btn.png", false);
		g.gridx=0;
		g.gridy=11;
		panel2.add(b3,g);
		
		Database db = new Database();
		
		b1.addActionListener((event)->{
			JButton btn= (JButton)event.getSource();
			
			if(btn.getText().equals("Add Dish")) {
				try {
					db.put(String.format("insert into dishes (huser_name, name, type, description, price,time_taken) values('%s', '%s', '%s', '%s', %s,'%s');",this.userName, name.getText(), (String)type.getSelectedItem(), description.getText(), price.getValue(), "00:00:00"));
					JOptionPane.showMessageDialog(frame, "New dish added successfully");
					
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
				updateTable();
			}
			else {
				table1.getSelectionModel().clearSelection();
			}
			System.out.println(btn.getText());
		});
		
		b2.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	String query;
            	try
                {
            		System.out.println(dishId.get(row));
               	  query ="UPDATE dishes SET  name = '"+name.getText()+"' , type = '"+type.getSelectedItem()+"' , description= '"+description.getText()+"' , price = '"+price.getValue()+"' ,time_taken = '00:00:00' WHERE dish_id = "+dishId.get(row)+";";
               	  db.put(query); 
               	  
               	JOptionPane.showMessageDialog(frame, "dish Updated successfully");
                }
                catch(Exception ex)
                {
               	   System.out.println(ex.getMessage());
                }
            	updateTable();
    }
    }); 
        b3.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {

            	String query;
            	
            	try
                {
               	  query = "Delete from dishes WHERE dish_id = "+dishId.get(row)+";";
               	  db.put(query);
               	  
               	JOptionPane.showMessageDialog(frame, "dish Deleted successfully");
                }
                catch(Exception ex)
                {
               	   System.out.println(ex.getMessage());
                }
            	updateTable();
    }
    }); 
		
		
		b1.setEnabled(true);
		b2.setEnabled(false);
		b3.setEnabled(false);
		
		return new JScrollPane(panel2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	@Override
	public String setMenuButton() {
		return "images/HDashboard/Menu btn(selected).png";
	}
	
	public static void main(String[] args) {
		HotelMenu  hm = new HotelMenu();
		hm.init("mukhesh");

	}

}
