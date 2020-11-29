<%@page import="com.fromme.app.admin.dao.AdminDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.fromme.app.admin.vo.ChartVO"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	AdminDAO a_dao = new AdminDAO();
	
	String dateData = request.getParameter("date");
	String chart = request.getParameter("chart");
	
	//11/10 수정완료
		List<ChartVO> todayData;
		List<ChartVO> weekData;
		List<ChartVO> monthData;
		List<ChartVO> yearData;
	
	if(chart.equals("view")) {
		todayData = a_dao.getViewChartData("hh24", dateData);
		weekData = a_dao.getViewChartData("DD", dateData);
		monthData = a_dao.getViewChartData("DD2", dateData);
		yearData = a_dao.getViewChartData("MM", dateData);	
	} else {//11/10 수정완료
		todayData = a_dao.getClassChartData("hh24", dateData);
		weekData = a_dao.getClassChartData("DD", dateData);
		monthData = a_dao.getClassChartData("DD2", dateData);
		yearData = a_dao.getClassChartData("MM", dateData);
	}
	

	JsonArray json = new JsonArray();
	json.add(a_dao.makeChartLabel(todayData));
	json.add(a_dao.makeChartData(todayData));
	json.add(a_dao.makeChartLabel(weekData));
	json.add(a_dao.makeChartData(weekData));
	json.add(a_dao.makeChartLabel(monthData));
	json.add(a_dao.makeChartData(monthData));
	json.add(a_dao.makeChartLabel(yearData));
	json.add(a_dao.makeChartData(yearData));

	JsonObject result = new JsonObject();
	result.add("item", json);
	System.out.print(result);
	out.print(result);
%>