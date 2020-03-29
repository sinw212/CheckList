<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javamysql.ConnectDB"%>
<%
	// 자바파일이 필요하므로 위 코드처럼 임포트합니다.
%>
<%
	request.setCharacterEncoding("UTF-8");
	String save_date = request.getParameter("save_date");
	String save_check = request.getParameter("save_check");
	String save_todo = request.getParameter("save_todo");
	String type = request.getParameter("type");
	
	//싱글톤 방식으로 자바 클래스를 불러옵니다.
	ConnectDB connectDB = ConnectDB.getInstance();
	if(type == null){
		out.print("실패");
	}
	else{
		if(type.equals("insert")) {
			String returns = connectDB.checklistAdd(save_date, save_check, save_todo);
			out.print(returns);
		} else if(type.equals("delete")) {
			String returns = connectDB.checklistDelete(save_date, save_todo);
			out.print(returns);
		} else if(type.equals("check")) {
			String returns = connectDB.checklistCheck(save_date, save_check ,save_todo);
			connectDB.checklistGoal(save_date);
			out.print(returns);
		} else if(type.equals("load")) {
			String returns = connectDB.checklistLoad(save_date);
			out.print(returns);
		}
	}
%>


