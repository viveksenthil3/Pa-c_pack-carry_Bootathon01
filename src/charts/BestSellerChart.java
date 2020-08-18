package charts;

import java.sql.ResultSet;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.*;

import restaurant.Database;

public class BestSellerChart extends Chart{
	

	public BestSellerChart(String userName) {
		super(userName, "Your trending Dishes");
		
	}

	@Override
	public JFreeChart createChart() {
		JFreeChart chart = ChartFactory.createPieChart("", (PieDataset)createDataset(), true, true, false);
		return chart;
	}

	@Override
	public Object createDataset() {
		 DefaultPieDataset dataset = new DefaultPieDataset( );
		 Database db = new Database();
		 String query = String.format("select d.name, count(o.dish_id) as count from orders o inner join dishes d on o.dish_id=d.dish_id where o.huser_name=\"%s\"  group by o.dish_id;", this.userName); 
		 String[] date =null;
		
		 try(ResultSet rs = db.get(query)){
			
		 	 while(rs.next()){
			 	 dataset.setValue(rs.getString("name"), rs.getInt("count"));
			 }
			
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 }

	      return dataset; 
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.add(new BestSellerChart("mukhesh").createChartPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);


	}

}
