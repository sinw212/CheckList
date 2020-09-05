package ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class Goal {
private static Goal instance = new Goal();

	public static Goal getInstance() {
		return instance;
	}
	
	//	DB접근
	private ConnectDB cDB = new ConnectDB();	//	DBConnector 객체생성
	private Connection conn;    //  connecttion:db에 접근하게 해주는 객체
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;	
	private String returns;
	
	public String goalShow(String date) {	//달성률 보기
			try {
				conn = cDB.getConn();
				sql = "select * from goal where save_date=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();			
				if(rs.next()) {	//	date에 해당되는 날짜의 달성률이 존재할 때 
					returns = rs.getString("goal");
				}
				else {	//	date에 해당되는 날짜의 달성률이 존재하지 않을 때
					sql = "insert into goal (save_date, goal) values (?, ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, date);
					pstmt.setString(2, "0 %");
					pstmt.executeUpdate();
					returns = "0 %";
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Goal goalShow SQLException error");
				returns = "error";
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
						System.err.println("Goal goalShow SQLException error");
						returns = "error";
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
						System.err.println("Goal goalShow SQLException error");
						returns = "error";
					}
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
						System.err.println("Goal goalShow SQLException error");
						returns = "error";
					}
			}

			System.out.println(returns);
		return returns;
	}
	
	
	public String goalModify(String date, String goal) {	// 달성률 수정
			try {
				conn = cDB.getConn();
				
				//	해당 날짜의 달성률이 존재할때 수정
				sql = "update goal set goal=? where save_date=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, goal);
				pstmt.setString(2, date);
				pstmt.executeUpdate();	//	db에 쿼리문 입력	
				returns = "goalModify";
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Goal goalModify SQLException error");
				returns = "error";
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
						System.err.println("Goal goalModify SQLException error");
						returns = "error";
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
						System.err.println("Goal goalModify SQLException error");
						returns = "error";
					}
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
						System.err.println("Goal goalModify SQLException error");
						returns = "error";
					}
			}
			
		return returns;
	}	
}