package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;

public class UserUpdateOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		UserDAO u_dao = new UserDAO();
		UserVO u_vo = new UserVO();
		
		
		u_vo.setUsers_id(request.getParameter("users_id"));
		u_vo.setUsers_pw(request.getParameter("users_pw"));
		u_vo.setUsers_name(request.getParameter("users_name"));
		u_vo.setUsers_phone(request.getParameter("users_phone"));
		u_vo.setUsers_email(request.getParameter("users_email"));
		u_vo.setUsers_authority(request.getParameter("users_authority"));
		u_vo.setGenre_no(Integer.parseInt(request.getParameter("genre_no")));
		u_vo.setUsers_address(request.getParameter("users_address"));
		u_vo.setUsers_address_detail(request.getParameter("users_address_detail"));
		u_vo.setUsers_zipcode(request.getParameter("users_zipcode"));
		
		response.setContentType("text/html;charset=utf-8");
		if (!u_dao.updateUser(u_vo)) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정보 변경 실패, 잠시 후 다시 시도해 주세요.');");
			out.println("</script>");
			out.close();
			return null;
		}
		
		forward.setRedirect(false);
		forward.setPath("/app/user/myPage.jsp?cat=5");
		return forward;
	}
}
