package com.fromme.app.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;

public class AdminUserModifyAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		UserDAO u_dao = new UserDAO();
		UserVO u_vo = new UserVO();
		String user_id = request.getParameter("id");
		String tmp = request.getParameter("reqPage");
		int reqPage= Integer.parseInt(tmp);
		
		u_vo = u_dao.getUserInfo(user_id);
		
		request.setAttribute("userInfo", u_vo);
		request.setAttribute("reqPage", reqPage);
		
		forward.setRedirect(false);
		forward.setPath("/app/admin/user_edit.jsp");
		return forward;
	}
}
