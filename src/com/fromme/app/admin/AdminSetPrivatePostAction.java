package com.fromme.app.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;

public class AdminSetPrivatePostAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminDAO a_dao = new AdminDAO();
		
		String noTemp = request.getParameter("num");
		String pubTemp = request.getParameter("set");

		int post_no = Integer.parseInt(noTemp);
		int post_pub = Integer.parseInt(pubTemp);

		int result = a_dao.setPrivate(post_no, post_pub);
		
		return null;
	}
}
