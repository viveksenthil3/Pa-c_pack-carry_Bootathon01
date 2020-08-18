package restaurant;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import restaurantGUI.Style;


public class CDashboard extends Dashboard{
	
	public JScrollPane setSidePanelContent() {
		JPanel panel0 = new JPanel(new GridBagLayout());
		panel0=(JPanel)Style.setBackground(panel0, Style.PRIMARY_COLOR);
		GridBagConstraints gcp0 = new GridBagConstraints();
		gcp0.fill=GridBagConstraints.BOTH;
		gcp0.gridx=gcp0.gridy=0;
		gcp0.weightx=gcp0.weighty=.2;
		
		JPanel panel1 = new JPanel(new GridBagLayout());
		panel1.setOpaque(false);

		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill=GridBagConstraints.HORIZONTAL;
		gc.anchor=GridBagConstraints.FIRST_LINE_START;
		gc.insets=new Insets(5, 0, 5, 0);

		gc.gridwidth=gc.gridheight=1;
		
		
		gc.weightx=0;
		gc.gridx=gc.gridy=0;
		JButton home;
		if(setHomeButton()==null) {
			home =  Style.getJButton("images/CDashboard/Home btn.png", false);
		}
		else {
			home = Style.getJButton(setHomeButton(), true);
			home.setBackground(Style.SECONDARY_COLOR_OBJ);
			gc.weightx=1;
		}
		home.addActionListener((event)->{
			new CustomerHotel().init(this.userName);
			terminate();
		});
		panel1.add(home, gc);
		
		
		gc.gridy=1;gc.weightx=0;

		JButton myOrders;
		if(setMyOrdersButton()==null) {
			myOrders =  Style.getJButton("images/CDashboard/My orders btn.png", false, 140,27);
		}
		else {
			myOrders = Style.getJButton(setMyOrdersButton(), true, 140,27);
			myOrders.setBackground(Style.SECONDARY_COLOR_OBJ);
			myOrders.setBorder(BorderFactory.createEmptyBorder(5, 45, 5, 0));
			gc.weightx=1;
		}
		myOrders.addActionListener((event)->{
			new CustomerOrderHistory().init(this.userName);
			terminate();
		});
		panel1.add(myOrders, gc);
		

		
		panel0.add(panel1, gcp0);
		
		gcp0.gridy=1;
		gcp0.weightx=gcp0.weighty=.8;

		JScrollPane sidePanel = setSidePanelAction();
		
		if(sidePanel!=null) 
			panel0.add(sidePanel, gcp0);
		else {
			JPanel panelPanelAction = new JPanel();
			panelPanelAction=(JPanel)Style.setBackground(panelPanelAction, Style.PRIMARY_COLOR);
			panel0.add(panelPanelAction, gcp0);
		}
		
		JScrollPane scrollPanel = new JScrollPane(panel0, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		return scrollPanel; 
	}
	
	public String setHomeButton() {
//		return "images/CDashboard/Home btn(selected).png";
		return null;
	}
	
	public String setMyOrdersButton() {
//		return "images/CDashboard/My orders btn(selected).png";
		return null;
	}

	public JScrollPane setSidePanelAction() {	
		
		return null;
//		return new JScrollPane();
	}
	
	public void userSettings() {
		new CustomerSettings().init(this.userName);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CDashboard hd = new CDashboard();
		hd.init("Nivitha");
	}

}
