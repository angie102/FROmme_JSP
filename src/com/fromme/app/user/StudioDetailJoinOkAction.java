package com.fromme.app.user;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;

public class StudioDetailJoinOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		UserDAO u_dao = new UserDAO();
		StudioVO s_vo = new StudioVO();
		
		String users_id = (String)session.getAttribute("session_id");
		int genre_no = u_dao.selectUserGenre(users_id);
		
		s_vo.setStudio_name(request.getParameter("studio_name"));
		s_vo.setStudio_president(request.getParameter("studio_president"));
		s_vo.setStudio_phone(request.getParameter("studio_phone"));
		s_vo.setGenre_no(genre_no);
		s_vo.setStudio_address(request.getParameter("studio_address"));
		s_vo.setStudio_address2(request.getParameter("studio_address2"));
		
		String studio_name = s_vo.getStudio_name();
		
		if (!u_dao.checkStudioName(studio_name)) {
			if (u_dao.studioDetailJoin(s_vo)) {
				int studio_seq = u_dao.selectStudio_seq(studio_name);
				u_dao.insertStudio_seq(studio_name, studio_seq);
				out.println("<script>");
				out.println("alert('승인 요청이 완료되었습니다.')");
				out.println("</script>");
				System.out.println("stu_seq : "+studio_seq);
				forward.setRedirect(true);
				forward.setPath(request.getContextPath() + "/app/main/index.jsp");
				return forward;
			}
		} else {
			out.println("<script>");
			out.println("alert('중복된 이름의 스튜디오가 있습니다. 잠시 후 다시 시도해 주세요.')");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("/user/StudioDetailJoin.use");
			return forward;
		}
		return null;
	}
}
