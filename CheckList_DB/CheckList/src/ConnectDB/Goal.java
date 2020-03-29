package ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goal {
private static Goal instance = new Goal();

	public static Goal getInstance() {
		return instance;
	}
	
	//	DB접근
	private ConnectDB cDB = new ConnectDB();	//	DBConnector 객체생성
	private Connection conn;    //  connecttion:db에 접근하게 해주는 객체
	private String sql = "";
	private String sql2 = "";
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private ResultSet rs;	
	private String returns;
	
	public String goalShow(String date) {	//달성률 보기
			try {
				conn = cDB.getConn();
				sql = "select * from Goal where save_date=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();			
				if(rs.next()) {	//	date에 해당되는 날짜의 달성률이 존재할 때 
					returns = rs.getString("save_text") + "-goalExist";
				}
				else {	//	date에 해당되는 날짜의 달성률이 존재하지 않을 때
					returns = "goalNotExist";
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

		return returns;
	}
	
	
	public String goalModify(String date, String goal) {	// 달성률 수정
			try {
				conn = cDB.getConn();
				sql = "select * from Goal where save_date=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();
				if(rs.next()) {	//	해당 날짜의 달성률이 존재할때 수정 (초기값은 0)
					sql2 = "update Goal set goal=? where save_date=?";
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, goal);
					pstmt2.setString(2, date);
					pstmt2.executeUpdate();	//	db에 쿼리문 입력	
				}
				returns = "goalAdded";
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