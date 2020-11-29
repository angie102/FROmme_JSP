package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;
import com.sun.xml.internal.bind.v2.runtime.Location;

public class UserCheckIdPwOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		UserDAO u_dao = new UserDAO();
		UserVO u_vo = new UserVO();
		StudioVO s_vo = new StudioVO();
		
		String id = (String)session.getAttribute("session_id");
		String pw = request.getParameter("users_pw");
		
		int no = (Integer.parseInt(request.getParameter("no")));
		PrintWriter out = response.getWriter();
		
		
		if ((u_dao.checkIdPw(id, pw))) {
			u_vo = u_dao.getUserInfo(id);
			if (no == 100) {
				request.setAttribute("userInfo", u_vo);
				forward.setRedirect(false);
				forward.setPath("/app/user/info_user.jsp");
			} else if (no == 200) {
				int studio_no = u_vo.getStudio_no();
				s_vo = u_dao.getStudioInfo(studio_no);
				
				request.setAttribute("userInfo", u_vo);
				request.setAttribute("studioInfo", s_vo);
				forward.setRedirect(false);
				forward.setPath("/app/user/info_studio.jsp");
			}
		}else {
			forward.setRedirect(false);
			forward.setPath("/app/user/checkIdPw.jsp");
		}
		
		return forward;
	}
}
