package restaurant;

import java.awt.*;

import javax.swing.*;

import charts.BestSellerChart;
import charts.MonthlyProfitChart;

import restaurantGUI.Style;

public class HDashboard extends Dashboard{
	
	@Override
	public JScrollPane setSidePanelContent() {
		JPanel panel0 = new JPanel(new GridBagLayout());
		panel0=(JPanel)Style.setBackground(panel0, Style.PRIMARY_COLOR);
		GridBagConstraints gcp0 = new GridBagConstraints();
		gcp0.fill=GridBagConstraints.BOTH;
		gcp0.gridx=gcp0.gridy=0;
		gcp0.weightx=gcp0.weighty=.2;
		
		JPanel panel1 = new JPanel(new GridBagLayout());
		panel1.setOpaque(false);
		panel1.setBackground(Color.green);

		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill=GridBagConstraints.HORIZONTAL;
		gc.anchor=GridBagConstraints.FIRST_LINE_START;
		gc.insets=new Insets(5, 0, 5, 0);
		gc.weightx=0;
		gc.gridwidth=gc.gridheight=1;
		
		
		gc.gridx=gc.gridy=0;
		JButton order;		
		if(setOrdersButton()==null) {
			order = Style.getJButton("images/HDashboard/orders btn.png", false);
		}
		else {
			order = Style.getJButton(setOrdersButton(), true);
			order.setBackground(Style.SECONDARY_COLOR_OBJ);
			gc.weightx=1;
		}
			
		order.addActionListener((event)->{
			new HotelOrder().init(this.userName);
			terminate();
		});
		panel1.add(order, gc);
		
		
		gc.gridy=1;gc.weightx=0;
		JButton menu;
		if(setMenuButton()==null) {
			menu =  Style.getJButton("images/HDashboard/Menu btn.png", false);
		}
		else {
			menu = Style.getJButton(setMenuButton(), true);
			menu.setBackground(Style.SECONDARY_COLOR_OBJ);
			gc.weightx=1;
		}
		menu.addActionListener((event)->{
			new HotelMenu().init(this.userName);
			terminate();
		});
		panel1.add(menu, gc);
		
		
		gc.gridy=2;gc.weightx=0;
		JButton statistics;
		if(setStatisticsButton()==null) {
			statistics =  Style.getJButton("images/HDashboard/Statistics btn.png", false);
		}
		else {
			statistics = Style.getJButton(setStatisticsButton(), true);
			statistics.setBackground(Style.SECONDARY_COLOR_OBJ);
			gc.weightx=1;
		}
		statistics.addActionListener((event)->{
			new HotelStatistics().init(this.userName);
			terminate();
		});
		panel1.add(statistics, gc);
		
		
		gc.gridy=3;gc.weightx=0;
		JButton orderHistory;
		if(setOrderHistoryButton()==null) {
			orderHistory =  Style.getJButton("images/HDashboard/OrderHistory btn.png", false, 150, 25);
		}
		else {
			orderHistory = Style.getJButton(setOrderHistoryButton(), true, 150, 25);
			orderHistory.setBackground(Style.SECONDARY_COLOR_OBJ);
			gc.weightx=1;
		}
		orderHistory.setBorder(BorderFactory.createEmptyBorder(10, 55, 10, 10));
		orderHistory.addActionListener((event)->{
			new HotelOrderHistory().init(this.userName);
			terminate();
		});
		panel1.add(orderHistory, gc);
		
		
		panel0.add(panel1, gcp0);
		
		gcp0.gridy=1;
		gcp0.weightx=gcp0.weighty=.8;

		JScrollPane sidePanel = setSidePanelAction();
		
		
		if(sidePanel!=null) {

			panel0.add(sidePanel, gcp0);
		}
		else {
			JPanel panelPanelAction = new JPanel();
			panelPanelAction=(JPanel)Style.setBackground(panelPanelAction, Style.PRIMARY_COLOR);
			panel0.add(panelPanelAction, gcp0);
		}
		
		JScrollPane scrollPanel = new JScrollPane(panel0, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		return scrollPanel; 
	}
	
	public String setOrdersButton() {
//		return "images/HDashboard/orders btn(selected).png";
		return null;
	}
	
	public String setMenuButton() {
//		return "images/HDashboard/Menu btn(selected).png";
		return null;
	}
	
	public String setStatisticsButton() {
//		return "images/HDashboard/Statistics btn(selected).png";
		return null;
	}
	
	public String setOrderHistoryButton() {
//		return "images/HDashboard/Statistics btn(selected).png";
		return null;
	}
	
	public JScrollPane setSidePanelAction() {	
		
		return null;
//		return new JScrollPane();
	}
	
	@Override
	public void userSettings() {
		new HotelSettings().init(this.userName);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HDashboard hd = new HDashboard();
		hd.init("Nivitha");
	}

}
