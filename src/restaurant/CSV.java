package restaurant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.*;

//import restaurant.Database;


public class CSV implements AutoCloseable{
	private BufferedReader reader=null;
	private BufferedWriter writer=null;
	private FileReader FReader=null;
	private FileWriter FWriter=null;
	
	public boolean writeCSV(String filePath, String ... columnNames) {
		
		try {
			this.FWriter = new FileWriter(filePath);
			this.writer = new BufferedWriter(this.FWriter);
		
			for(String columnName: columnNames) {
				this.writer.append(columnName);
				if(columnNames[columnNames.length-1]!=columnName)
					this.writer.append(",");
			}
			
			this.writer.append("\n");
			this.writer.flush();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean writeRow(String ... rowItems) {
		try {
			
			for(String rowItem: rowItems) {
				this.writer.append(rowItem);
				if(rowItems[rowItems.length-1]!=rowItem)
					this.writer.append(",");	
			}
			this.writer.append("\n");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
public boolean readCSV(String filePath, String ... columnNames) {
		
		try {
			this.FReader = new FileReader(filePath);
			this.reader = new BufferedReader(this.FReader);

			
			if(Arrays.equals(this.reader.readLine().split(","), columnNames)) {
				return true;
			}

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}
	
	public List<List<String>> readRow() {

		try {
			String row;
			List<List<String>> rows = new ArrayList<>();
			
			while((row = this.reader.readLine())!=null) {

				rows.add(Arrays.asList(row.split(",")));
			}

			return rows;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	@Override
	public void close() throws Exception {		
		if(this.reader!=null) {
			this.reader.close();
			this.FReader.close();
		}
		
		if(this.writer!=null) {
			this.writer.close();
			this.FWriter.close();
		}
	}

	public static void main(String[] args) {

		Database db= new Database();
		

		try(CSV csv = new CSV();) {
			ResultSet rs=db.get("select * from dishes where huser_name='"+"mukhesh"+"'");
			csv.writeCSV("dishes.csv", "name", "type", "description", "price");
			
			
			while(rs.next())
	        {

	    		
//		        String name= rs.getString("name");
//		        String type= rs.getString("type");
//		        String price = rs.getString("price");
		        csv.writeRow(rs.getString("name"), rs.getString("type"), rs.getString("description"), rs.getString("price"));

	        }
			



		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
			

	}

	

}
