package com.fromme.app.studio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.ActionForward;

public class StudioFrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet실행확인");
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost실행확인");
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;
		
		System.out.println("FrontController 실행");
		switch(command) {
		case "/app/studio/StudioEditAction.std":
			try {
				System.out.println("studioEditAction 실행");
				forward = new StudioEditAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/app/studio/studioAddAction.std":
			System.out.println("studioAddAction 실행");
			try {
				forward = new StudioClassAddAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/studio/StudioAddClassOkAction.std":	
			System.out.println("studioAddClassOkAction 실행");
			try {
				forward = new StudioAddClassOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/studio/StudioEditOkAction.std":
			System.out.println("studioEditOkAction 실행");
			try {
				forward = new StudioEditOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/studio/StudioViewAction.std" :
			System.out.println("studio/StudioViewAction 실행");
			try {
				forward = new StudioViewAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/studio/StudioDelete.std" :
			System.out.println("클래스 삭제실행");
			try {
				forward = new StudioDeleteAction().execute(req, resp);
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
				resp.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
			}
		}
	}
	
}


