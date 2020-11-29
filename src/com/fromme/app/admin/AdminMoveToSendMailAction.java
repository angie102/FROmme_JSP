package com.fromme.app.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;

public class AdminMoveToSendMailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		String id = request.getParameter("id");
		String tmp = request.getParameter("reqPage");
		int reqPage = Integer.parseInt(tmp);
	
		
		request.setAttribute("receive", id);
		request.setAttribute("reqPage", reqPage);
		
		forward.setRedirect(false);
		forward.setPath("/app/admin/write_mail.jsp");
		return forward;
	}
}
