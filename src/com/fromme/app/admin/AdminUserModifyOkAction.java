package com.fromme.app.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;

public class AdminUserModifyOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AdminDAO a_dao = new AdminDAO();
		String tmp = request.getParameter("reqPage");
		String user_id = request.getParameter("id");
		String user_email = request.getParameter("email");
		String user_name = request.getParameter("name");
		String user_phone = request.getParameter("phone");
		String user_authority = request.getParameter("authority");
		int reqPage = Integer.parseInt(tmp);
		
		int result = a_dao.setUserInfo(user_id, user_name, user_email, user_phone, user_authority);
		
		forward.setRedirect(false);
		forward.setPath("/admin/userList.adm?page="+reqPage);
		return forward;
	}
}
