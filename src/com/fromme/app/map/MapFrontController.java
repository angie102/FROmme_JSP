package com.fromme.app.map;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.ActionForward;

public class MapFrontController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;

		switch(command) {
		
		case "/map/FindGongBangList.map" :	//지도 페이지로 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/map/findGongBangList.jsp");
			break;
		case "/map/FindGongBangListOk.map":	//검색한 값으로 공방 리스트 찾기
			try {
				forward = new FindGongBangListOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			forward = new com.fromme.action.ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/main/error/404.jsp");
		}

		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
