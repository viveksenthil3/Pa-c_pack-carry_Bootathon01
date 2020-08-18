package restaurant;
import java.awt.Color;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
public class CustomerMenu extends CDashboard {
	JPanel panel1;
	JPanel panel2;
	JTable table1;
	JLabel name;
	JLabel type;
	JTextArea description;
	JLabel price;
	JSpinner quantity;
	
	ArrayList<String> dishId=new ArrayList<String>();
	String husername;
	int row;
	
	public CustomerMenu(String husername)
	{
		this.husername=husername;
		
	}
	
	@Override
	public JScrollPane setMainPanel()
	{
		
		String columnNames[] = {"S.no","Hotel Name","Name", "Type","Price",};
		
		DefaultTableModel model = new DefaultTableModel();
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
		table1.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		
		Database db= new Database();
		
		try
    	{
    	ResultSet rs=db.get("select d.*, h.hotel_name from dishes d inner join hotel h on d.huser_name=h.huser_name where d.huser_name='"+this.husername+"';");
    	int sno=1;
    	
    	while(rs.next())
        {
            dishId.add(rs.getString("dish_id"));
    		
	        String name= rs.getString("name");
	        String type= rs.getString("type");
	        String price = rs.getString("price");
	        String hotel_Name = rs.getString("hotel_name");
	        
	        model.addRow(new Object[]{sno,hotel_Name,name,type,price});
	        sno++;
	       
        }
        }
		
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		ListSelectionModel sel=table1.getSelectionModel();
        sel.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) 
        {
        	if(!sel.isSelectionEmpty())
        	{
        		row=sel.getMinSelectionIndex();
        		
                try
                 {
                	  ResultSet rs=db.get("select * from dishes where dish_id = "+dishId.get(row)+";");
                      if(rs.next())
                      {
                      name.setText(rs.getString("name")); 
                      type.setText(rs.getString("type"));
                      description.setText(rs.getString("description"));
                      price.setText(rs.getString("price"));
                      quantity.setValue(Integer.parseInt("1"));
                      }
                 }
                 catch(Exception ex)
                 {
                	   System.out.println(ex.getMessage());
                 }
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
				
		JLabel qnty= new JLabel("Quantity");
                qnty=Style.setLabelStyle(qnty);
		g.gridx=0;
		g.gridy=6;
		panel2.add(qnty,g);
		
		name = new JLabel();
                name=Style.setLabelStyle(name);
		g.gridx=1;
		g.gridy=1;
		g.insets=new Insets(10,10,10,10);
		panel2.add(name,g);
		
		type = new JLabel();
                type=Style.setLabelStyle(type);
		g.gridx=1;
		g.gridy=2;
		panel2.add(type,g);
		
		description = new JTextArea(4,15);
		g.gridx=1;
		g.gridy=3;
		description.setEditable(false);
		description.setLineWrap(true);
	    description.setWrapStyleWord(true);
		panel2.add(description,g);
		
		price = new JLabel();
                price=Style.setLabelStyle(price);
		g.gridx=1;
		g.gridy=4;
		panel2.add(price,g);
				
		SpinnerModel sm = new SpinnerNumberModel(1,1,20,1);
		quantity = new JSpinner(sm);
		Dimension d3 = new Dimension(50,20);
		quantity.setPreferredSize(d3);
		g.gridx=1;
		g.gridy=6;
		panel2.add(quantity,g);
		
		JButton b=new JButton("Order");
		g.gridx=0;
		g.gridy=10;
		panel2.add(b,g);
		
		
		b.addActionListener((event)->{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			
			String query = String.format("insert into orders (user_name, dish_id, huser_name, time_of_order, status, quantity) values('%s',%s,'%s','%s','pending',%s);", this.userName, this.dishId.get(this.row), this.husername, dtf.format(now), (Integer)quantity.getValue());
			
			try {
				new Database().put(query);
				
				JOptionPane.showMessageDialog(frame, "Your order has been placed");
				
			}
			catch(Exception e) {
			
			    JOptionPane.showMessageDialog(frame, "Sorry something went wrong \n    please try again");
				System.out.println(e.getMessage());
			}
		});
		
		
		return new JScrollPane(panel2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	@Override
    public String setHomeButton() {
		return "images/CDashboard/Home btn(selected).png";
	}
	
	public static void main(String[] args) {
		CustomerMenu  cm = new CustomerMenu("mukhesh");
		cm.init("Nivi");

	}

}

