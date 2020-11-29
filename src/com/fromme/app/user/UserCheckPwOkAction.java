package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.UserDAO;

public class UserCheckPwOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("users_id");
		UserDAO u_dao = new UserDAO();
		String pw = "";
		if (u_dao.checkPw(id) != null) {
			pw = u_dao.checkPw(id);
		}
		System.out.println(id);
		System.out.println(pw);
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html;charset=utf-8");
		if (pw != "") {
			out.println(pw);
		} else {
			out.println("");
		}
		out.close();
		
		return null;
	}
}
