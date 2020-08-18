package restaurant;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import restaurantGUI.Style;

public class CustomerOrderHistory extends CDashboard {
    ArrayList<String> ord=new ArrayList<String>();
    Database db= new Database();
    JButton btn;
    JLabel order_id,user_name,dish_name,time_of_order,status,quantity; 
    DefaultTableModel mod = new DefaultTableModel();
    JTable jt;    
    int selrow;
    
    
    public void updateTable(){
        int no_of_rows=mod.getRowCount();
         Object[] array= new Object[5];
         for(int j=no_of_rows-1;j>=0;j--){
             mod.removeRow(j);
         }
            int i=1;
     
            try(ResultSet rs= db.get("select  o.order_id , o.user_name, d.name, o.user_name, o.time_of_order, o.status, o.quantity from orders o inner join dishes d on o.dish_id=d.dish_id where  o.user_name='"+userName+"'order by time_of_order desc;");){                
              while(rs.next()){
                  ord.add(rs.getString("order_id"));
                  array[0]=i;
                  array[1]=rs.getString("order_id");
                  array[2]=rs.getString("name");
                  array[3]=rs.getString("time_of_order");
                  array[4]=rs.getString("status");
                  mod.addRow(array);
                  i++;
              }  
            } catch (SQLException ex) {
            Logger.getLogger(HotelOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public JScrollPane setMainPanel(){
        JScrollPane mp;
        JPanel p= new JPanel();
        jt=new JTable(mod){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
            
           
            mod.addColumn("SNo");
            mod.addColumn("Order Id");
            mod.addColumn("Dish Name");
            mod.addColumn("Time Of Order");
            mod.addColumn("Status");
            updateTable();
            Object[] array= new Object[4];
            int i=1;
            ListSelectionModel sel=jt.getSelectionModel();
            sel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
               
                    if(!sel.isSelectionEmpty()){
                        btn.setEnabled(true);
                        selrow=sel.getMinSelectionIndex();
                    
                        try(ResultSet rs= db.get("select  o.order_id , o.user_name, d.name, o.huser_name, o.time_of_order, o.status, o.quantity from orders o inner join dishes d on o.dish_id=d.dish_id where o.order_id ='"+ord.get(selrow)+"';");){
                            while(rs.next()){
                                
                            order_id.setText("Order Id: "+rs.getString("order_id"));
                            ord.add(rs.getString("order_id"));
                            user_name.setText("Username: "+rs.getString("user_name"));
                            dish_name.setText("Name of Dish: "+rs.getString("name"));
                            time_of_order.setText("Time of Order: "+rs.getString("time_of_order"));
                            status.setText("Status of Order: "+rs.getString("status"));
                            quantity.setText("Quantity: "+rs.getString("quantity"));
                            }
                               
                            
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(HotelOrder.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                    
            });
           
            mp=new JScrollPane(jt);
        return mp;
    }
    
    @Override
    public JScrollPane setSidePanelAction(){
        JPanel spa= new JPanel(new GridBagLayout());
        spa=(JPanel)Style.setBackground(spa, Style.PRIMARY_COLOR);
        GridBagConstraints gc= new GridBagConstraints();
        gc.fill=GridBagConstraints.BOTH;
        gc.insets=new Insets(10,10,10,10);
        JScrollPane spc=new JScrollPane();
        gc.gridx=0;
        gc.gridy=0;
        order_id=new JLabel("Order Id: ");
        order_id=Style.setLabelStyle(order_id);
        spa.add(order_id,gc);
        gc.gridx=0;
        gc.gridy=1;
        user_name= new JLabel("Username: ");
        user_name=Style.setLabelStyle(user_name);
        spa.add(user_name,gc);
        gc.gridx=0;
        gc.gridy=2;
        dish_name=new JLabel("Name of the Dish:");
        dish_name=Style.setLabelStyle(dish_name);
        spa.add(dish_name,gc);
        gc.gridx=0;
        gc.gridy=3;
        time_of_order =new JLabel("Time of Order:");
        time_of_order=Style.setLabelStyle(time_of_order);
        spa.add(time_of_order,gc);
        gc.gridx=0;
        gc.gridy=4;
        status=new JLabel("Status of Order:");
       status=Style.setLabelStyle(status);
        spa.add(status,gc);
        gc.gridx=0;
        gc.gridy=5;
        quantity=new JLabel("Quantity:");
        quantity=Style.setLabelStyle(quantity);
        spa.add(quantity,gc);
        btn =new JButton("Cancel Ordrer");
        btn.setEnabled(false);
        gc.gridx=0;
        gc.gridy=6;
        spa.add(btn,gc);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.put("update orders set status ='Delivered' where order_id ="+ord.get(selrow)+";");
                String query;
				try
				{
				 query = "Delete from orders WHERE user_name = '"+userName+"'and order_id='"+ord.get(selrow)+"';";
				 db.put(query);
				updateTable();                      
				}
				catch(Exception ex)
				{
				  System.out.println(ex.getMessage());
				}  
				JOptionPane.showMessageDialog(frame,"Order canceled");
			     }
			 });
        
        spc=new JScrollPane(spa,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return spc;
    }
    
    @Override
	public String setMyOrdersButton() {
		return "images/CDashboard/My orders btn(selected).png";
	}
    
    public static void main(String args[]){
        CustomerOrderHistory coh=new CustomerOrderHistory();
        coh.init("Mukhesh");
    }
}
