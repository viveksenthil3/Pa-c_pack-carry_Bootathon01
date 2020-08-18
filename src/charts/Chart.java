package charts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.jfree.chart.*;


public abstract class Chart {
	
	String userName;
	String chartName;
	
	public Chart(String userName, String chartName) {
		this.userName=userName;
		this.chartName=chartName;
	}
	
	public JPanel createChartPanel() {
		JFreeChart chart = createChart();
		
		int width = 800;
		int height = 500;
		
		JPanel panel = new JPanel(new BorderLayout());		
		panel.add(new ChartPanel(chart,width,height,width,height,width,height,false,false,false,false,false,false), BorderLayout.CENTER);
		
		TitledBorder titledBorder = new TitledBorder(this.chartName);
		titledBorder.setTitleFont(new Font(this.chartName, Font.BOLD, 24));
		
		panel.setBorder(new CompoundBorder(titledBorder, new EmptyBorder(20, 0, 20, 0)));

		
		JPanel innerBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		innerBottomPanel.setBorder(new EmptyBorder(15,0,5,20));

		
		JButton saveBtn = new JButton("Save As PNG");
		saveBtn.addActionListener((event)->{
			saveChartAsPNG(chart, width, height);
		});
		innerBottomPanel.add(saveBtn);
	
		
		panel.add(innerBottomPanel, BorderLayout.SOUTH);
		
		
		return panel;
	}
	
	private void saveChartAsPNG(JFreeChart chart, int width, int height) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.png","png");
		fileChooser.setFileFilter(filter);
		int i = fileChooser.showSaveDialog(null);
		
		if(i==JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			try {
				ChartUtilities.saveChartAsPNG(file, chart, width, height);
				JOptionPane.showMessageDialog(null, "Your chart has been saved Successful"); 
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

			
		}
	}
	
	public abstract JFreeChart createChart();	
	
	public abstract Object createDataset();

}
