package com.fromme.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;

public class UserLogoutOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		session.invalidate();
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/app/main/index.jsp");
		
		return forward;
	}
}
