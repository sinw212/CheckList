<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="ConnectDB.*" %>
<%@ page import="ConnectJSP.*" %>

<%
	request.setCharacterEncoding("UTF-8");
	String save_date = request.getParameter("date");
	String goal = request.getParameter("goal");
	String type = request.getParameter("type"); // 사용자가 무슨요청을 했는지 구분하는 변수
	
	GoalCnt goalCnt = new GoalCnt(type, save_date, goal);
	
	String returns = goalCnt.getResult();
	out.clear();
	out.print(returns);
	out.flush();
%>