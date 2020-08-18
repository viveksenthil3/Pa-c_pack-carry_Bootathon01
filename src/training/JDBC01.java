package training;

import java.sql.*;

public class JDBC01 {

	public static void main(String[] args) {
		Connection con = null;
		
		try {
			String url = "jdbc:sqlite:/mnt/Programs/Codes/BootCampJava/databases/db1.db";
			con = DriverManager.getConnection(url);
			
			System.out.println("Connection established");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM students");
			
			while(rs.next()) {
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("email")+"\t"+rs.getString("phone"));
			}
		}
		catch(SQLException e) {
			 System.out.println("Connection failed :" + e.getMessage());
		}

	}

}
