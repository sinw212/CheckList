package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {

	String dbURL = "jdbc:mysql://192.168.35.92:3306/checklist_db"; // MySQL 계정
	String dbID = "root"; // MySQL 계정
	String dbPW = "1234";
	
	public ConnectDB() {

	}
	
	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (SQLException e) {
			System.err.println("ConnectDB Connection error");
			System.err.println(e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("ConnectDB  ClassNotFoundException error");
			return null;
		}
	}
}