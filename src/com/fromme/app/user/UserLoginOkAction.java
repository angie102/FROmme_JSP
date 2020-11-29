package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;

public class UserLoginOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		UserDAO u_dao = new UserDAO();
		
		String id = request.getParameter("users_id");
		String pw = request.getParameter("users_pw");
		
		String s_id = u_dao.login(id, pw);
		
		if (s_id != null) {
			// 로그인 성공
			int authority = u_dao.getUsersAuthority(id);
			if (authority == 6) {
				forward.setRedirect(false);
				forward.setPath("/app/user/login.jsp?login=suspension");
			} else {
				session.setAttribute("session_id", s_id); // 세션에 아이디 등록
				session.setAttribute("authority", authority);
				forward.setRedirect(true);
				forward.setPath(request.getContextPath() + "/app/main/index.jsp");
			}
		}else {
			// 로그인 실패
			forward.setRedirect(false);
			forward.setPath("/app/user/login.jsp?login=false");
		}
		
		return forward;
	}
}
