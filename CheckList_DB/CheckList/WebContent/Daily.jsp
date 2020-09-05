<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="ConnectDB.*" %>
<%@ page import="ConnectJSP.*" %>

<%
	request.setCharacterEncoding("UTF-8");
	String save_date = request.getParameter("date");
	String save_check = request.getParameter("check");
	String save_todo = request.getParameter("todo");
	String type = request.getParameter("type"); // 사용자가 무슨요청을 했는지 구분하는 변수
	

	DailyCnt dailyCnt = new DailyCnt(type, save_date, save_check, save_todo);
	
	String returns = dailyCnt.getResult();
	out.clear();
	out.print(returns);
	out.flush();
	
%>