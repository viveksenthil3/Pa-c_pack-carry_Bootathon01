package charts;

import java.sql.ResultSet;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import restaurant.Database;

public class EachDayOrdersChart extends Chart{

	public EachDayOrdersChart(String userName) {
		super(userName, "Day wise Sales Report");
		
	}

	@Override
	public JFreeChart createChart() {
		JFreeChart chart = ChartFactory.createBarChart("", "Days", "No. of orders", (CategoryDataset)createDataset());
		return chart;
	}

	@Override
	public Object createDataset() {		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

		try {
			ResultSet rs = new Database().get(String.format("select d.huser_name, dayname(o.time_of_order) as day, count(o.order_id) as count from orders o inner join dishes d on o.dish_id=d.dish_id where d.huser_name='%s'  group by dayname(o.time_of_order),d.huser_name;",this.userName));
			
			while(rs.next())
				dataset.addValue(rs.getInt("count"), rs.getString("day"), "");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
			                     
		return dataset; 
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.add(new EachDayOrdersChart("mukhesh").createChartPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
