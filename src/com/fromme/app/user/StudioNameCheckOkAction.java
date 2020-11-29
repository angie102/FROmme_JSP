package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.UserDAO;

public class StudioNameCheckOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String studio = request.getParameter("studio_name");
		UserDAO u_dao = new UserDAO();
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html;charset=utf-8");
		if (u_dao.checkStudioName(studio)) {
			out.println("not-ok");
		}else {
			out.println("ok");
		}
		out.close();
		
		return null;
	}
}
