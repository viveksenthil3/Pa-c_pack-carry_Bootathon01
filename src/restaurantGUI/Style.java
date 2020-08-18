package restaurantGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Style {
	public static int PRIMARY_COLOR=1;
	public static int SECONDARY_COLOR=2;
	public static Color SECONDARY_COLOR_OBJ=Color.white;
	public static Color SECONDARY_FONT_COLOR_OBJ=Color.white;
	
	public static JButton getButton() {
		JButton button = new JButton();
		
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		return button;
	}
	
	public static JButton getButton(String imgURL) {
		JButton button = new JButton(new ImageIcon(imgURL));
		
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		return button;
	}
	
	public static JButton getJButton(String imgURL, boolean isSelected) {
		int width=100,height=27;
		ImageIcon settingsImg = new ImageIcon(imgURL);
		JButton button = new JButton(new ImageIcon(settingsImg.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		
		if(!isSelected) {
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
		}		
		
		return button;
	}
	
	public static JButton getJButton(String imgURL, boolean isSelected, int width, int height) {
//		int width=100,height=27;
		ImageIcon settingsImg = new ImageIcon(imgURL);
		JButton button = new JButton(new ImageIcon(settingsImg.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
//		JButton button = new JButton(new ImageIcon(imgURL));
		
		if(!isSelected) {
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
		}		
		
		return button;
	}
	
	public static JTextField getTextField() {
		JTextField textField = new JTextField();
		
		textField.setOpaque(false);
		textField.setBorder(BorderFactory.createEmptyBorder());
	    
	    return textField;
	}
	
	public static JPasswordField getPasswordField() {
		JPasswordField passwordField = new JPasswordField();
		
		passwordField.setOpaque(false);
		passwordField.setBorder(BorderFactory.createEmptyBorder());
	    
	    return passwordField;
	}
	
	public static JLabel getLabel(String label) {
		JLabel Jlabel = new JLabel(label);
		Jlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		
		return Jlabel;
	}
	
	public static JLabel setLabelStyle(JLabel label) {
		label.setForeground(Style.SECONDARY_FONT_COLOR_OBJ);
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		
		return label;
	}
	
	public static JComponent setBackground(JComponent component, int colorType) {
		
		if(colorType==1) {
			component.setBackground(new Color(23,105,170));
			return component;
		}
		return null;
	}
}
