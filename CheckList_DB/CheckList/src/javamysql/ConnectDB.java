package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConnectDB {
    // 싱글톤 패턴으로 사용 하기위 한 코드들
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public ConnectDB() {

    }

    String jdbcUrl = "jdbc:mysql://192.168.35.69:3306/checklist_db"; // MySQL 계정
    String dbId = "root"; // MySQL 계정
    String dbPw = "ghdaodtlq"; // 비밀번호
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "";
    String returns = "a";

    // 데이터베이스와 통신하기 위한 코드가 들어있는 메서드
    public String checklistAdd(String date, String check, String todo) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 디비 연결
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

            sql = "insert into daily (save_date, save_check, save_todo) values(?,?,?)"; // 삽입
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            pstmt.setString(2, check);
            pstmt.setString(3, todo);
            sql = "insert into goal (save_date, goal) values(?,?)";
            pstmt.setString(1, date);
            pstmt.setString(2, "0");
            pstmt.executeUpdate();
            returns = "삽입 완료!";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();	} catch (SQLException ex) {	}
        }
        return returns;
    }

    public String checklistDelete(String date, String todo) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 디비 연결
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

            sql = "delete from daily where save_date = '"+date+"' and save_todo = '"+todo+"';"; // 삭제
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            returns = "삭제 완료!";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();	} catch (SQLException ex) {	}
        }
        return returns;
    }

    public String checklistCheck(String date, String check, String todo) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 디비 연결
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

            sql = "update daily set save_check ='"+check+"' where save_date = '"+date+"' and save_todo = '"+todo+"';"; // 체크 값 변경
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            returns = "변경 완료!";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();	} catch (SQLException ex) {	}
        }
        return returns;
    }
    
    public String checklistLoad(String date) {
    	try {
            Class.forName("com.mysql.jdbc.Driver");
            // 디비 연결
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

            sql = "select save_check, save_todo from daily where save_date ='"+date+"';"; // 체크 값 변경
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            
            //JSON 형태로 출력 원하는 값 출력
        	JSONObject jsonMain = new JSONObject(); // 객체
        	JSONArray jArray = new JSONArray(); // 배열
        	
        	
        	int i = 0;

            while(rs.next()) {
            	JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체.
            	// 안드로이드로 보낼 메시지를 만듦
            	jObject.put("save_check",rs.getString("save_check"));
            	jObject.put("save_todo",rs.getString("save_todo"));
            	System.out.println("save_todo : "+rs.getString("save_todo")+" save_check : "+rs.getString("save_check"));
            	// 위에서 만든 각각의 객체를 하나의 배열 형태로 만듦
            	jArray.add(i,jObject);
            	i++;
            }
            // 최종적으로 배열을 하나로 묶음
        	jsonMain.put("save_data",jArray);
        	rs.close();
           
            returns = jsonMain.toJSONString();
          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();	} catch (SQLException ex) {	}
        }
        return returns;
    	
    }
    
    public void checklistGoal(String date) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 디비 연결
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

            sql = "update goal set goal ="
            		+ "( select count(*) from daily "
            		+ "where save_date = '"+date+"' and save_check = '1') /"
            				+ "( select count(*) from daily"
            				+ "where save_date ='"+date+"') *100;"; // 달성률
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            returns = "달성률 설정!";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();	} catch (SQLException ex) {	}
        }
    }

}

