package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.UserDAO;

public class UserFindIdOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		UserDAO u_dao = new UserDAO();
		
		String name = request.getParameter("users_name");
		String phone = request.getParameter("users_phone");
		String f_id = u_dao.findId(name, phone);
		
		response.setContentType("text/html;charset=utf-8");
		
		if (f_id != null) {
			session.setAttribute("users_name", name);
			session.setAttribute("find_id", f_id);
			session.setAttribute("num", 1);
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
