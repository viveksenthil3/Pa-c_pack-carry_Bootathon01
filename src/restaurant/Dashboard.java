package restaurant;

import java.awt.*;

import javax.swing.*;

import restaurantGUI.Style;

public abstract class Dashboard {
	protected static JFrame frame = new JFrame();//SV
	protected String userName;
	
	public void init(String userName) {
		this.userName = userName;
		
//		frame = new JFrame();//SV
		Dashboard.frame.getContentPane().removeAll();
		
		Dashboard.frame.setLayout(new GridBagLayout());
		Dashboard.frame.setSize(800,700);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		

		gc.gridx = gc.gridy = 0;
		gc.weightx=gc.weighty=.2;		
	
		Dashboard.frame.add(setSidePanel(), gc);
		
		
		gc.gridx = 1;
		gc.weightx=gc.weighty=.8;

		Dashboard.frame.add(setMainPanel(), gc);
		
		Dashboard.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		Dashboard.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dashboard.frame.validate();
		Dashboard.frame.repaint();;
		Dashboard.frame.setVisible(true);
	}

	public JScrollPane setMainPanel() {
		
		return new JScrollPane();
	}
	
	public JPanel setSidePanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel = (JPanel)Style.setBackground(panel, Style.PRIMARY_COLOR);
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx = gc.gridy = 0;
		gc.weightx=gc.weighty=.2;

		panel.add(setUserDetails(), gc);
		
		gc.gridy = 1;
		gc.weightx=gc.weighty=.8;
		JScrollPane sidePanelContent = setSidePanelContent();
		panel.add(sidePanelContent, gc);
		
		return panel; 
	}
	
	public JPanel setUserDetails() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx=gc.gridy=0;
		gc.gridwidth=3;
		JLabel welcomeUser = new JLabel("Welcome "+this.userName);
		welcomeUser.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		welcomeUser.setForeground(Color.white);
		panel.add(welcomeUser, gc);
		
		gc.gridy=gc.gridx=1;
		gc.insets = new Insets(20, 0, 0, 0);
		

		JButton settings = Style.getJButton("images/Dashboard/settings.png", false);
		
		settings.addActionListener((event)->{
			userSettings();
			terminate();
		});
		panel.add(settings, gc);
		
		gc.gridy=2;
		gc.insets = new Insets(0, 0, 0, 0);
		JButton logout = Style.getJButton("images/Dashboard/logout btn.png", false);
		
		logout.addActionListener((event)->{
			int option =JOptionPane.showConfirmDialog(Dashboard.frame,"Are you sure?","Logout",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);  
			
			if(option==JOptionPane.YES_OPTION) {				
				new Main().init();
				terminate();
			}
		});
		panel.add(logout, gc);
		
		
		
		
		return panel; 
	}
	
	public abstract void userSettings();

	public JScrollPane setSidePanelContent() {		
		
		return new JScrollPane(); 
	}
	
	public void terminate() {
//		Dashboard.frame.dispose();
	}

	public static void main(String[] args) {

	}

}
