package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;

public class StudioUpdateOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		UserDAO u_dao = new UserDAO();
		StudioVO s_vo = new StudioVO();
		
		s_vo.setStudio_name(request.getParameter("studio_name"));
		s_vo.setStudio_president(request.getParameter("studio_president"));
		s_vo.setStudio_phone(request.getParameter("studio_phone"));
		s_vo.setStudio_address(request.getParameter("studio_address"));
		s_vo.setStudio_address2(request.getParameter("studio_address2"));
		
		response.setContentType("text/html;charset=utf-8");
		if (!u_dao.updateStudio(s_vo)) {
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
