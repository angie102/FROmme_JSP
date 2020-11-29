package com.fromme.app.classes;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.ActionForward;
import com.fromme.app.board.BoardDeleteOkAction;
import com.fromme.app.board.BoardListAction;
import com.fromme.app.board.BoardModifyAction;
import com.fromme.app.board.BoardModifyOkAction;
import com.fromme.app.board.BoardReplyDeleteOkAction;
import com.fromme.app.board.BoardReplyModifyOkAction;
import com.fromme.app.board.BoardReplyOkAction;
import com.fromme.app.board.BoardViewAction;
import com.fromme.app.board.BoardWriteOkAction;
import com.fromme.app.board.FileDownloadAction;

public class ClassesFrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;

		switch(command) {
		case "/classes/ClassesList.cls":		//클래스 리스트 
			try {
				forward = new ClassesListAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/ClassesSortList.cls":		//장바구니 리스트 페이지 
			try {
				forward = new ClassesSortAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "/classes/ClassesLikeIncreaseOk.cls":		//클래스 상세페이지
			try {
				forward = new ClassesLikeIncreaseOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/ClassesView.cls":		//클래스 상세페이지
			try {
				forward = new ClassesVeiwAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/ClassesCheckDateAvailable.cls":		//클래스 상세페이지
			try {
				forward = new ClassesCheckDateAvailableAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/CartInsertOk.cls":		//장바구니 담기
			try {
				forward = new CartInsertOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/CartList.cls":		//장바구니 리스트 페이지 
			try {
				forward = new CartListAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "/classes/CartDeleteOk.cls":		//장바구니 목록 페이지에서 삭제(ajax) 
			try {
				forward = new CartDeleteOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/CartModifyOk.cls":		//장바구니 목록 페이지에서 수량 수정(ajax)
			try {
				forward = new CartModifyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/PaymentView.cls":		//주문 리스트 페이지 
			try {
				forward = new PaymentViewAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/PaymentOk.cls":		//결제 진행 페이지 
			try {
				forward = new PaymentOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/classes/PaymentSuccess.cls":		//주문완료 페이지
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/classes/orderSuccess.jsp");
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


