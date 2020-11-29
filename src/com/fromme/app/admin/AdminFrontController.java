package com.fromme.app.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.ActionForward;

public class AdminFrontController extends HttpServlet{
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
		
		System.out.println(command);
		switch(command) {
		//메인 페이지 데이터
		case "/admin/indexData.adm":
			try {
				forward = new AdminIndexAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		//공방 승인 페이지 데이터
		case "/admin/studioApplyList.adm":
			try {
				forward = new AdminApplyListViewAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		//공방 승인 액션
		case "/admin/studioApplyOk.adm":
			try {
				forward = new AdminStudioApplyOkAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		//공방 기각 액션
		case "/admin/studioRejectOk.adm":
			try {
				forward = new AdminStudioRejectAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		//클래스 목록 페이지
		case "/admin/classList.adm":
			try {
				forward = new AdminClassListViewAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("classList.adm"+e.getMessage());
			}
			break;
		//userDAO 완성 시 진행 
		case "/admin/userList.adm":
			try {
				forward = new AdminUserListViewAction().execute(req, resp);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("userList.adm"+e.getMessage());
			}
			break;
		//게시
		case "/admin/board.adm":
			try {
				forward = new AdminBoardViewAction().execute(req, resp);
			} catch(Exception e) {
				System.out.println("board.adm"+e.getMessage());
			}
			break;
		case "/admin/passwordReset.adm":
			try {
				forward = new AdminBoardViewAction().execute(req, resp);
			} catch(Exception e) {
				System.out.println("board.adm"+e.getMessage());
			}
			break;
		case "/admin/userModifyAction.adm":
			try {
				forward = new AdminUserModifyAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "/admin/userModifyOk.adm":
			try {
				forward = new AdminUserModifyOkAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		//ajax
		case "/admin/setPrivate.adm":
			try {
				forward = new AdminSetPrivatePostAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "/admin/userDisable.adm":
			try {
				forward = new AdminUserDisableAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "/admin/setPrivateClass.adm":
			try {
				forward = new AdminSetPrivateClassAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "/admin/mailBox.adm":
			try {
				forward = new AdminMailListAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;	
		case "/admin/mailView.adm":
			try {
				forward = new AdminMailViewAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;	
		case "/admin/movetosend.adm":
			try {
				forward = new AdminMoveToSendMailAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "/admin/sendMail.adm":
			try {
				forward = new AdminSendMailAction().execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			forward = new ActionForward();
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


