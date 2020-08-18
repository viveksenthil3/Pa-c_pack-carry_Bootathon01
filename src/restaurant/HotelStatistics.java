package restaurant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import charts.*;
import restaurantGUI.Style;


public class HotelStatistics extends HDashboard{
	
	@Override
	public JScrollPane setMainPanel() {
		JPanel mainPanel = new JPanel(new GridBagLayout());		
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill=GridBagConstraints.BOTH;
		gc.insets = new Insets(10, 0, 10, 0);
		gc.gridwidth=800;
		
	      gc.gridx=gc.gridy=0;
	      mainPanel.add(new MonthlyProfitChart(this.userName).createChartPanel(), gc);
	      
	      gc.gridy=1;
	      mainPanel.add(new BestSellerChart(this.userName).createChartPanel(), gc);   
	      
	      gc.gridy=2;
	      mainPanel.add(new EachDayOrdersChart(this.userName).createChartPanel(), gc);
	      
	      
	      JPanel dividerPanel = new JPanel(new FlowLayout());
	      JLabel sideHeading = new JLabel("Dish wise Statistics");
	      sideHeading.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
	      dividerPanel.add(sideHeading);
	      
	      gc.gridy=3;
	      mainPanel.add(dividerPanel, gc);
	      
	      ArrayList<JPanel> dishReports = DishesReportChart.generateDishReportCharts(this.userName);
	      for(JPanel dishReport: dishReports) {
	    	  gc.gridy++;
		      mainPanel.add(dishReport, gc);
	      }
	     
	    
	      
	      JScrollPane scrollPane = new JScrollPane(mainPanel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      scrollPane.setPreferredSize(new Dimension(800, 1000));
	      scrollPane.getVerticalScrollBar().setUnitIncrement(15);
	      
	      JPanel panel = new JPanel();
	      panel.add(scrollPane);
	
		
		return scrollPane;
	}
	
	@Override
	public JScrollPane setSidePanelAction() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel=(JPanel)Style.setBackground(panel, Style.PRIMARY_COLOR);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(10,10,10,10);
		
		Database db = new Database();
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = dateFormat.format(cal.getTime());
		cal.add(Calendar.DATE, -30);
		String lastMonthDate = dateFormat.format(cal.getTime());
		
		try {
			
			ResultSet rs = db.get(String.format("select sum(d.price*o.quantity) as sum from dishes d inner join orders o on d.dish_id=o.dish_id where o.huser_name='%s' and date(time_of_order) between \"%s\" and \"%s\";", this.userName, lastMonthDate, todayDate));
			rs.next();
			
			gc.gridx=gc.gridy=0;
			JLabel monthlyProfit = new JLabel("Last 30 days Sales (Rs): "+ rs.getString("sum"));
			monthlyProfit=Style.setLabelStyle(monthlyProfit);
			panel.add(monthlyProfit, gc);
			
			rs = db.get(String.format("select sum(d.price*o.quantity) as sum from dishes d inner join orders o on d.dish_id=o.dish_id where o.huser_name='%s' and date(time_of_order)=\"%s\";", this.userName, todayDate));
			rs.next();
			gc.gridy=1;
			JLabel todaysProfit = new JLabel("Today's Sales (Rs): "+ rs.getString("sum"));
			todaysProfit=Style.setLabelStyle(todaysProfit);
			panel.add(todaysProfit, gc);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		gc.gridy=2;
		gc.insets = new Insets(50,0,10,0);
		JLabel lastMonthSalesReport = new JLabel("Last month sales Data ");
		lastMonthSalesReport=Style.setLabelStyle(lastMonthSalesReport);
		panel.add(lastMonthSalesReport, gc);
		
		gc.gridy=3;
		gc.insets = new Insets(10,0,10,10);
		JButton export = new JButton("Export Data");
        panel.add(export, gc);
        
        
        export.addActionListener((event)->{
        	Database db1= new Database();
    		
        	JFileChooser fileChooser = new JFileChooser();
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv","csv");
    		fileChooser.setFileFilter(filter);
    		int i = fileChooser.showDialog(frame, "Export");
    		
    		if(i==JFileChooser.APPROVE_OPTION) {
    			File file = fileChooser.getSelectedFile();
//    			System.out.println(file.getAbsolutePath());
    			
    			try(CSV csv = new CSV();) {
        			ResultSet rs1=db1.get(String.format("select order_id, user_name, name, type, price, time_of_order, status, quantity, price*quantity as order_price from dishes d inner join orders o on d.dish_id=o.dish_id where o.huser_name='%s' and date(time_of_order) between \"%s\" and \"%s\";", this.userName, lastMonthDate, todayDate));
        			csv.writeCSV(file.getAbsolutePath(), "Order ID", "Customer Name", "Dish Name","Type", "Price", "Order Date", "Order Status", "Quantity", "Bill Amount");
        			
        			
        			while(rs1.next())
        	        {
        		        csv.writeRow(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6), rs1.getString(7), rs1.getString(8), rs1.getString(9));        		        
        	        }
        			
        			rs1 = db1.get(String.format("select sum(price*quantity) as total_sum,  sum(quantity) as total_qnt from dishes d inner join orders o on d.dish_id=o.dish_id where o.huser_name='%s' and date(time_of_order) between \"%s\" and \"%s\";", this.userName, lastMonthDate, todayDate));
        			rs1.next();
        			csv.writeRow("", "", "", "", "", "", "Total", rs1.getString(2), rs1.getString(1));
        			JOptionPane.showMessageDialog(null, "Your Data has been Exported \n Successful");

        		}
        		catch(Exception e) {
        			System.out.println(e.getMessage());
        		}

    			
    		}
        });
		
		
		return new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	@Override
	public String setStatisticsButton() {
		return "images/HDashboard/Statistics btn(selected).png";

	}
	
	public static void main(String[] args) {

		new HotelStatistics().init("mukhesh");
	}

}
