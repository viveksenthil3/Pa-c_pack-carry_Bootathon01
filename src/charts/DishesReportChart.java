package charts;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import restaurant.Database;

public class DishesReportChart extends Chart{
	private int dishId;

	public DishesReportChart(String userName, String chartName) {
		super(userName, chartName);
		
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
			ResultSet rs = new Database().get(String.format("select o.dish_id, d.name,dayname(o.time_of_order) as day, count(o.order_id) as count from orders o inner join dishes d on o.dish_id=d.dish_id where o.dish_id=%s  group by dayname(o.time_of_order),o.dish_id;",this.dishId));
			
			while(rs.next())
				dataset.addValue(rs.getInt("count"), rs.getString("day"), "");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
			                     
		return dataset; 
	}
	
	public void setdishId(int dishId) {
		this.dishId = dishId;
	}
	
	public static ArrayList<JPanel> generateDishReportCharts(String userName) {
		ArrayList<JPanel> dishReports = new ArrayList<JPanel>();
		
		try {
			ResultSet rs = new Database().get(String.format("select dish_id, name from dishes where huser_name='%s';", userName));
			
			while(rs.next()) {
				DishesReportChart dishReport = new DishesReportChart(userName, rs.getString("name"));
				dishReport.setdishId(rs.getInt("dish_id"));
				dishReports.add(dishReport.createChartPanel());
			}
				
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return dishReports;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		JPanel panel = DishesReportChart.generateDishReportCharts("nivitha").get(1);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}


}
