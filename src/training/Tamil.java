package training;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class Tamil {
	static boolean temp = false;

public static void main(String[] args) {

    JFrame frame = new JFrame();

    List<Component> components = new ArrayList<Component>();

    components.add(new JButton("G"));
    
    JButton bt1 = new JButton("test3");
    components.add(bt1);
    bt1.addActionListener((event)->{
    	Tamil.temp=true;
    	
    	System.out.println("in");
    	frame.getContentPane().validate();
    	frame.getContentPane().repaint();
    });
    
    components.add(Tamil.add());//new JButton("test3"));

    frame.setLayout(new FlowLayout());
    components.get(1).setBackground(Color.orange);

//    for(Component component: components) {
    for(int i=0; i<3; i++) {
    	if(components.get(i).getBackground().equals(Color.orange))
    		System.out.println(i);
        frame.getContentPane().add(components.get(i));}
    
  

    frame.setSize(500,500);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

static JButton add() {
	if(Tamil.temp) 
		return new JButton("true");
	return new JButton("false");
	
}

}