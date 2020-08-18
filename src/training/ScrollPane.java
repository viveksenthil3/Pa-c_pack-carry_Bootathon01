package training;

import java.awt.*;
import javax.swing.*;

public class ScrollPane {
	 public ScrollPane() {
	        JFrame f = new JFrame();
	        f.setLayout(new BorderLayout());
//	        f.setResizable(false);
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        JPanel panel = new JPanel();
	        panel.setBorder(BorderFactory.createLineBorder(Color.red));
	        panel.setLayout(new BoxLayout(panel, 1));
	        for (int i = 0; i < 100; i++) {
	            panel.add(new JButton("kjdh"));
	        }
//	        JScrollPane scrollPane = new JScrollPane(panel);
	        
	        JPanel panel1 = new JPanel();
	        
	        
	        JScrollPane scrollPane = new JScrollPane(panel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setPreferredSize(new Dimension(600, 600));
	        
	        panel1.add(scrollPane);
	        f.getContentPane().add(panel1);
//	        f.pack();
//	        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        
	        f.setVisible(true);
	    }

	    public static void main(String[] args) {
	        Runnable r = new Runnable() {
	            @Override
	            public void run() {
	                new ScrollPane();
	            }
	        };
	        SwingUtilities.invokeLater(r);
	    }
}


