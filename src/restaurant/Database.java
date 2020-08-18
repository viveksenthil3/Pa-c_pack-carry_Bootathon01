package restaurant;

import java.sql.*;

public class Database {
	static String URI="jdbc:mysql://localhost:3306/restaurant";
	static String PASSWORD="root";
	static String USER="root";
	public Statement statement;
	
	public Database() {
		this.statement = Database.connect();
	}
	
	public static Statement connect() {
		Statement stmt = null;
		try {
			Connection con = DriverManager.getConnection(Database.URI, Database.USER, Database.PASSWORD);
			stmt = con.createStatement();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return stmt;
	}
	
	public ResultSet get(String query) {
		ResultSet rs =null;
		try{
			rs = this.statement.executeQuery(query);
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	public boolean put(String query) {
		try {
			this.statement.executeUpdate(query);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static void main(String[] a) {
		Database db = new Database();
		try(ResultSet rs = db.get("select * from hotel ;");){
			while(rs.next()) {
				System.out.println(rs.getInt("user_id")+"\t"+rs.getString("name")+"\t"+rs.getString("type")+"\t"+rs.getString("start_time")+"\t");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		
		System.out.println(db.put("insert into hotel values(4,4,'nivitha',1,'08:00:00','08:00:00',7);"));
		
	}
}
