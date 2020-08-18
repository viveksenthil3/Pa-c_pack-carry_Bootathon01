package charts;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;

import org.jfree.chart.*;
import org.jfree.data.category.*;

import restaurant.Database;


public class MonthlyProfitChart extends Chart{

	public MonthlyProfitChart(String userName) {
		super(userName, "Last Month's Rate of Order");
		
	}

	@Override
	public JFreeChart createChart() {
		JFreeChart chart = ChartFactory.createLineChart("", "Date", "No. of orders", (CategoryDataset)createDataset());
		return chart;
	}

	@Override
	public Object createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Database db = new Database();
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = dateFormat.format(cal.getTime());
		cal.add(Calendar.DATE, -30);
		String lastMonthDate = dateFormat.format(cal.getTime());
		String query = String.format("select date(time_of_order) as date, count(order_id) as count from orders where huser_name=\"%s\" and date(time_of_order) between \"%s\" and \"%s\" group by date(time_of_order);", this.userName, lastMonthDate, todayDate); 
		String[] date =null;
		
		try(ResultSet rs = db.get(query)){
			
			while(rs.next()){
				date =  rs.getString("date").split("-");
				dataset.addValue(rs.getInt("count"), "Orders", date[1]+"-"+date[2]);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

      return dataset;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.add(new MonthlyProfitChart("nivitha").createChartPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
