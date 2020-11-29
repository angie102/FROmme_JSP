package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.UserDAO;

public class UserFindPwOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		UserDAO u_dao = new UserDAO();
		
		String id = request.getParameter("users_id");
		String email = request.getParameter("users_email");
		String f_pw = u_dao.findPw(id, email);
		
		response.setContentType("text/html;charset=utf-8");
		
		if (f_pw != null) {
			session.setAttribute("users_id", id);
			session.setAttribute("find_pw", f_pw);
			session.setAttribute("num", 2);
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/app/user/find_id_pw_answer.jsp");
			return forward;
		} else {
			forward.setRedirect(false);
			forward.setPath("/app/user/find_id_pw.jsp");
		}
		
		return null;
	}
}
