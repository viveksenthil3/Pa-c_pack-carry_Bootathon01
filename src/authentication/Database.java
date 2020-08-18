package authentication;

import java.sql.*;

public class Database {
	
	static String uri = "jdbc:sqlite:/mnt/Programs/Codes/BootCampJava/databases/db1.db";
	static String userName = "";
	static String password = "";
	
	
	static Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(Database.uri);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

}

//public static void main(String[] args) {
//	// TODO Auto-generated method stub
//	try(Connection con = Database.connect();
//		Statement stm = con.createStatement();
//		ResultSet rs = stm.executeQuery("select * from students");){
//		
//		while(rs.next()) {
//			System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("email")+"\t"+rs.getString("phone"));
//		}
//	}
//	catch(SQLException e) {
//		System.out.println(e.getMessage());
//	}
//}