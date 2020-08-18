package training;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class jtable {

	public static void main(String[] args) {
		DefaultTableModel tableModel = new DefaultTableModel();
	      JTable table = new JTable(tableModel);
	     
	      tableModel.addColumn("Languages");
	      tableModel.addColumn("name");
	      tableModel.addColumn("no.");

	      
	      Object[] array=new Object[4];
	      array[0]="java";
	      array[1]="vivek";
	      array[2]="12";
	      
	      
	      tableModel.addRow(array);
	      tableModel.addRow(array);
	      tableModel.addRow(array);
	      JFrame f = new JFrame();
	      f.setSize(550, 350);
	      f.add(new JScrollPane(table));
	      f.setVisible(true);

	}

}
