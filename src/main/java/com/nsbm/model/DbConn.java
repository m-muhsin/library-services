package com.nsbm.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class DbConn {

	public Connection conn;
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "dblibrary";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "user";
	private String password = "pass";
	
	public void connectDB(){
	
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url+dbName,userName,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeDB(){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		DbConn dbConn = new DbConn();
		
		dbConn.connectDB();
		String sql = "SELECT * FROM tblbooks";
		Statement stmt = dbConn.conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println(rs.next());
		System.out.println(rs.getString(2));
	}
	
}
