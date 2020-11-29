package com.fromme.app.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;

public class AdminUserDisableAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminDAO a_dao = new AdminDAO();
		
		String noTemp = request.getParameter("num");
		String pubTemp = request.getParameter("set");

		int users_no = Integer.parseInt(noTemp);
		int users_authority = Integer.parseInt(pubTemp);

		int result = a_dao.setUserState(users_no, users_authority);


		return null;
	}
	
}
