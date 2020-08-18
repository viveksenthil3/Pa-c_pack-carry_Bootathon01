package training;

import java.awt.*;

import javax.swing.*;

public class PanelChange {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		frame.setSize(600,600);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx=1.0;
		gc.weighty=1.0;
		
		
		JPanel panel0 = new JPanel(new GridBagLayout());
		GridBagConstraints gcp0 = new GridBagConstraints();
		gcp0.fill = GridBagConstraints.BOTH;
		gcp0.weightx=1.0;
		gcp0.weighty=1.0;
		
		JLabel l = new JLabel("panel0");
		panel0.add(l);
		
		gc.gridx=0; gc.gridy=0;
		frame.add(panel0, gc);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.red);
		JLabel l1 = new JLabel("panel1");
		panel1.add(l1);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		panel2.setBackground(Color.green);
		JLabel l2 = new JLabel("panel2");
		panel2.add(l2);
		
		JPanel panel3 = new JPanel(new FlowLayout());
		panel3.setBackground(Color.blue);
		
		JButton b1 = new JButton("panel1");
		b1.addActionListener((event)->{			
			panel0.removeAll();

			panel0.add(panel1, gcp0);
			panel0.validate();
			panel0.repaint();
		});
		panel3.add(b1);
		
		JButton b2 = new JButton("panel2");
		b2.addActionListener((event)->{			
			panel0.removeAll();
			
			panel0.add(panel2, gcp0);
			panel0.validate();
			panel0.repaint();
		});
		panel3.add(b2);
		
		gc.gridx=0; gc.gridy=1;
		frame.add(panel3, gc);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
