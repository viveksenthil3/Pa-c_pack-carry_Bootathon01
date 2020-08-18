//package training;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.event.*;  
//import javax.swing.table.*;
//import java.sql.ResultSet;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//public class CustomerMenu extends Dashboard {
//	JPanel panel1;
//	JPanel panel2;
//	JTable table1;
//	JLabel name;
//	JLabel type;
//	JLabel description;
//	JLabel price;
//	JLabel timetaken;
//	
//	ArrayList<String> dishId=new ArrayList<String>();
//	String husername;
//	int row;
//	
//	CustomerMenu(String husername)
//	{
//		this.husername=husername;
//		
//	}
//	
//	public JScrollPane setMainPanel()
//	{
//		
//		String columnNames[] = {"S.no","Name", "Type","Price","TimeTaken"};
//		
//		DefaultTableModel model = new DefaultTableModel();
//		table1 = new JTable(model);
//		model.setColumnIdentifiers(columnNames);
//		table1.setFont(new Font("Segoe UI", 4, 13));
//		table1.getColumnModel().getColumn(0).setPreferredWidth(30);
//		table1.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table1.getColumnModel().getColumn(2).setPreferredWidth(50);
//		table1.getColumnModel().getColumn(3).setPreferredWidth(100);
//		table1.getColumnModel().getColumn(4).setPreferredWidth(100);
//		
//		
//		Database db= new Database();
//		
//		try
//    	{
//    	ResultSet rs=db.get("select * from dishes where huser_name='"+this.husername+"'");
//    	int sno=1;
//    	
//    	while(rs.next())
//        {
//            dishId.add(rs.getString("dish_id"));
//    		
//	        String name= rs.getString("name");
//	        String type= rs.getString("type");
//	        String price = rs.getString("price");
//	        String timetaken = rs.getString("time_taken");
//	        
//	        model.addRow(new Object[]{sno,name, type,price,timetaken});
//	        sno++;
//	       
//        }
//        }
//		
//		catch (Exception ex)
//		{
//			System.out.println(ex.getMessage());
//		}
//		
//		ListSelectionModel sel=table1.getSelectionModel();
//        sel.addListSelectionListener(new ListSelectionListener() {
//        @Override
//        public void valueChanged(ListSelectionEvent e) 
//        {
//        	if(!sel.isSelectionEmpty())
//        	{
//        		row=sel.getMinSelectionIndex();
//        		
//                try
//                 {
//                	  ResultSet rs=db.get("select * from dishes where dish_id = "+dishId.get(row)+";");
//                      if(rs.next())
//                      {
//                      name.setText(rs.getString("name")); 
//                      type.setText(rs.getString("type"));
//                      description.setText(rs.getString("description"));
//                      price.setText(rs.getString("price"));
//                      timetaken.setText(rs.getString("time_taken"));
//                      }
//                 }
//                 catch(Exception ex)
//                 {
//                	   System.out.println(ex.getMessage());
//                 }
//            }
//        }
//       });
//           
//		return new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		
//	}
//	
//	public JScrollPane setSidePanelContent()
//	{
//		panel2 = new JPanel(new GridBagLayout());
//		GridBagConstraints g = new GridBagConstraints();
//		
//		JLabel n = new JLabel("Name");
//		g.gridx=0;
//		g.gridy=1;
//		g.insets=new Insets(10,10,10,10);
//		panel2.add(n,g);
//		
//		JLabel t = new JLabel("Type");
//		g.gridx=0;
//		g.gridy=2;
//		panel2.add(t,g);
//		
//		JLabel d = new JLabel("Description");
//		g.gridx=0;
//		g.gridy=3;
//		panel2.add(d,g);
//		
//		JLabel p = new JLabel("Price");
//		g.gridx=0;
//		g.gridy=4;
//		panel2.add(p,g);
//		
//		JLabel tt = new JLabel("TimeTaken");
//		g.gridx=0;
//		g.gridy=5;
//		panel2.add(tt,g);
//		
//		JLabel qnty= new JLabel("Quantity");
//		g.gridx=0;
//		g.gridy=6;
//		panel2.add(qnty,g);
//		
//		
//		name = new JLabel();
//		g.gridx=1;
//		g.gridy=1;
//		g.insets=new Insets(10,10,10,10);
//		panel2.add(name,g);
//		
//		type = new JLabel();
//		g.gridx=1;
//		g.gridy=2;
//		panel2.add(type,g);
//		
//		description = new JLabel();
//		g.gridx=1;
//		g.gridy=3;
//		panel2.add(description,g);
//		
//		price = new JLabel();
//		g.gridx=1;
//		g.gridy=4;
//		panel2.add(price,g);
//		
//		timetaken = new JLabel();
//		g.gridx=1;
//		g.gridy=5;
//		panel2.add(timetaken,g);
//				
//		JSpinner quantity = new JSpinner();
//		Dimension d3 = new Dimension(50,20);
//		quantity.setPreferredSize(d3);
//		g.gridx=1;
//		g.gridy=6;
//		panel2.add(quantity,g);
//		
//		JButton b=new JButton("Order");
//		g.gridx=0;
//		g.gridy=10;
//		panel2.add(b,g);
//		
//		b.addActionListener((event)->{
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			LocalDateTime now = LocalDateTime.now();
//			
//			String query = String.format("insert into orders (user_name, dish_id, huser_name, time_of_order, status, quantity) values('%s',%s,'%s','%s','pending',%s);", this.userName, this.dishId.get(this.row), this.husername, dtf.format(now), (Integer)quantity.getValue());
//			
////			System.out.println(dtf.format(now));
//			
//			try {
//				new Database().put(query);
//				
//				new JOptionPane().showMessageDialog(frame, "Your order has been placed");
//				
//			}
//			catch(Exception e) {
//			
//				new JOptionPane().showMessageDialog(frame, "Sorry something went wrong \n    please try again");
//				System.out.println(e.getMessage());
////				System.out.println(e.getStackTrace());
//			}
//		});
//		
//		
//		return new JScrollPane(panel2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		
//	}
//	
//	public static void main(String[] args) {
//		CustomerMenu  cm = new CustomerMenu("mukhesh");
//		cm.init("Nivi");
//
//	}
//
//}
