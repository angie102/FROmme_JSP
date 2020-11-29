package com.fromme.app.studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.app.user.dao.UserDAO;

public class StudioClassAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		System.out.println("studioClassAddAction실행");
		StudioVO s_vo = new StudioVO();
		StudioDAO s_dao = new StudioDAO();
		UserDAO u_dao = new UserDAO();
		
		//세션 아이디
		String session_id = (String) request.getSession().getAttribute("session_id");
		System.out.println("session 아이디 : "+session_id);
		
		//공방 정보
		ClassesVO c_vo = new ClassesVO();
		
		//공방번호로 공방 내용 가져오기
		int studio_num = s_dao.getStudioNum(session_id);
		s_vo = s_dao.getStudioDetail(studio_num);	
		System.out.println("s_vo확인용: "+s_vo);
		
		//스튜디오 장르 가져오기
		String genre_name =s_dao.getGenreName(s_vo.getGenre_no());
		System.out.println("genre_name : "+genre_name);
		
		//스튜디오정보
		request.setAttribute("s_vo", s_vo);
		//장르 명
		request.setAttribute("genre_name", genre_name);
		
		forward.setRedirect(false);
		forward.setPath("/app/studio/studio_addClass.jsp");
		
		return forward;
	}

}
