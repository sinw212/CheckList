package ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Daily {
private static Daily instance = new Daily();

	public static Daily getInstance() {
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
	
	public String todoShow(String date) {	//오늘 할일 목록 보여주기
			try {
				conn = cDB.getConn();
				sql = "select * from Daily where save_date=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();		
				
				JSONArray jary = new JSONArray();
				boolean flag = true;
				while(rs.next()) {
					JSONObject jobj = new JSONObject();
					jobj.put("save_todo", rs.getString("save_todo"));
					jary.add(jobj);
					
					flag = false;
				}
				returns = jary.toJSONString();
				if(flag) {	//	할일이 존재하지 않을 때
					returns = "todoNotExist";
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Daily todoShow SQLException error");
				returns = "error";
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoShow SQLException error");
						returns = "error";
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoShow SQLException error");
						returns = "error";
					}
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoShow SQLException error");
						returns = "error";
					}
			}		
					
		return returns;
	}
	
	
	public String todoAdd(String date, String check, String todo) {	// 오늘 할일 등록
			try {
				conn = cDB.getConn();
				sql = "select * from Daily where save_todo=? and save_date=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {	//	해당 날짜에 같은 할일이 존재할 때
					returns = "todoAlreadyExist";
				}
				else {	//	해당 날짜의 할일이 존재하지 않을 때 - 새로생성
					sql2 = "insert into Daily (save_date, save_check, save_todo) values (?, ?, ?)";
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, date);
					pstmt2.setString(2, check);
					pstmt2.setString(3, todo);
					pstmt2.executeUpdate();	//	db에 쿼리문 입력
					returns = "todoAddSuccess";
				}
				returns = "todoAdded";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Daily todoAdd SQLException error");
				returns = "error";
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoAdd SQLException error");
						returns = "error";
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoAdd SQLException error");
						returns = "error";
					}
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoAdd SQLException error");
						returns = "error";
					}
			}
			
		return returns;
	}	
	
	
	public String checkModify(String date, String check) {	//할일 체크박스 수정
			try {
				conn = cDB.getConn();
				sql = "select * from Daily where save_date=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();
				if(rs.next()) {	//오늘 할 일이 존재할 때
					sql2 = "update calander set save_check=? where save_date=?";
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, check);
					pstmt2.setString(2, date);
					pstmt2.executeUpdate();	//	db에 쿼리문 입력
					returns = "todoModify";				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Daily todoModify SQLException error");
				returns = "error";
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoModify SQLException error");
						returns = "error";
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoModify SQLException error");
						returns = "error";
					}
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoModify SQLException error");
						returns = "error";
					}				
				if (pstmt2 != null)
					try {
						pstmt2.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoModify SQLException error");
						returns = "error";
					}
			}	
			
		return returns;
	}
	
	
	public String todoDelete (String date, String check, String todo) {	//오늘 할일 삭제
			try {
				conn = cDB.getConn();
				sql = "select * from Daily where save_date=? and save_check=? and save_todo=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, date);
				pstmt.setString(2, check);
				pstmt.setString(3, todo);
				rs = pstmt.executeQuery();
				if(rs.next()) {	//오늘 할일이 존재할 때
					sql2 = "delete from calander where save_date=? and save_check=? and save_date=?";
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, date);
					pstmt2.setString(2, check);
					pstmt2.setString(3, todo);
					pstmt2.executeUpdate();	//	db에 쿼리문 입력
					returns = "todoDelete";				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Daily todoDelete SQLException error");
				returns = "error";
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoDelete SQLException error");
						returns = "error";
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoDelete SQLException error");
						returns = "error";
					}
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoDelete SQLException error");
						returns = "error";
					}				
				if (pstmt2 != null)
					try {
						pstmt2.close();
					} catch (SQLException ex) {
						System.err.println("Daily todoDelete SQLException error");
						returns = "error";
					}
			}	
			
		return returns;
	}
}