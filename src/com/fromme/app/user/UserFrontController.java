package com.fromme.app.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.ActionForward;

public class UserFrontController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;

		switch(command) {
		case "/user/UserJoin.use":
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/user/join_user.jsp");
			break;
		case "/user/UserJoinOk.use":
			try {
				forward = new UserJoinOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/StudioOpen.use":
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/user/open_studio.jsp");
			break;
		case "/user/StudioOpenOk.use":
			try {
				forward = new StudioOpenOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/StudioNamCheckOk.use":
			try {
				forward = new StudioNameCheckOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/UserCheckIdOk.use":
			try {
				forward = new UserCheckIdOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/UserLogin.use":
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/user/login.jsp");
			break;
		case "/user/UserLoginOk.use":
			try {
				forward = new UserLoginOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/UserLogout.use":
			try {
				forward = new UserLogoutOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/UserFindIdOk.use":
			try {
				forward = new UserFindIdOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			case "/user/UserFindPwOk.use":
			try {
				forward = new UserFindPwOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/UserCheckIdPw.use":
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/user/checkIdPw.jsp");
			break;
		case "/user/UserCheckIdPwOk.use":
			try {
				forward = new UserCheckIdPwOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/UserUpdateOk.use":
			try {
				forward = new UserUpdateOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/StudioUpdateOk.use":
			System.out.println("ㄷㅇㅇㄹ");
			try {
				forward = new StudioUpdateOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/user/UserCheckPwOk.use":
			try {
				forward = new UserCheckPwOkAction().execute(req, resp);
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


