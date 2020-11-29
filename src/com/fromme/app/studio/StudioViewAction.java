package com.fromme.app.studio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;

public class StudioViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		System.out.println("studioViewAction 실행");
		
		//세션아이디
		String session_id = (String) request.getSession().getAttribute("session_id");
		System.out.println("session 아이디 : "+session_id);

		// 메인페이지에서 가져올 유저 정보
		UserVO u_vo = new UserVO();
		UserDAO u_dao = new UserDAO();
		
		// 유저정보로 해당 공방정보 가져오기
		StudioVO s_vo = new StudioVO();
		StudioDAO s_dao = new StudioDAO();
		
		//스튜디오 번호
		int studio_num = s_dao.getStudioNum(session_id);
		
		// 공방정보로 클래스 정보 가져오기
		List<ClassesVO> c_list = new ArrayList<ClassesVO>();
		ClassesVO c_vo = new ClassesVO();
		ClassesDAO c_dao = new ClassesDAO();
		
		//공방번호로 가져온 공방번호
		s_vo = s_dao.getStudioDetail(studio_num);
		System.out.println(s_vo);
		
		//반복문 돌릴 클래스 개수
		int classCnt = s_dao.getClassCount(studio_num);
		
		//스튜디오 장르 가져오기
		String genre_name =s_dao.getGenreName(s_vo.getGenre_no());
		
		//해당 공방번호에 해당하는 클래스를 가져온다. 
		c_list = s_dao.getClassContents(studio_num);
		System.out.println("리스트 컨텐츠 : "+c_list.toString());	
		
		//해당 클래스 이미지 불러오기
		
		//스튜디오정보
		request.setAttribute("s_vo", s_vo);
		//클래스 정보
		request.setAttribute("c_list", c_list);
		//반복문 돌릴개수
		request.setAttribute("classCnt", classCnt);
		//장르 명
		request.setAttribute("genre_name", genre_name);
		
		forward.setRedirect(false);
		forward.setPath("/app/studio/studio.jsp");
	
		return forward;
	}
}


