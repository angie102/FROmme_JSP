package com.fromme.app.studio;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.app.user.dao.UserDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class StudioEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();

		System.out.println("studioEditAction 실행");
		//세션 아이디
		String session_id = (String) request.getSession().getAttribute("session_id");
		System.out.println("session 아이디 : "+session_id);
		
		//공방 정보
		StudioVO s_vo = new StudioVO();
		StudioDAO s_dao = new StudioDAO();
		ClassesVO c_vo = new ClassesVO();		
		
		//공방번호로 공방 내용 가져오기
		int studio_num = s_dao.getStudioNum(session_id);
		s_vo = s_dao.getStudioDetail(studio_num);	
		System.out.println("s_vo확인용: "+s_vo);
		
		//스튜디오 장르 가져오기
		String genre_name =s_dao.getGenreName(s_vo.getGenre_no());
		System.out.println("genre_name : "+genre_name);
		
		//해당 클래스 정보 가져오기
		int classes_teacher = Integer.parseInt(request.getParameter("seq"));
		System.out.println("해당 클래스 선생 : "+classes_teacher);
		c_vo = s_dao.getClassData(classes_teacher);
		System.out.println(c_vo);
		System.out.println("class_name : "+c_vo.getClasses_name());
		int classes_no =c_vo.getClasses_no();
		int classes_limit = s_dao.getClassLimit(classes_no);
		System.out.println("클래스 정원 : "+classes_limit);
		//스튜디오정보
		request.setAttribute("s_vo", s_vo);
		//장르 명
		request.setAttribute("genre_name", genre_name);
		//해당 클래스 정보 넘기기
		request.setAttribute("c_vo", c_vo);
		//클래스 총원 정보
		request.setAttribute("class_limit", classes_limit);
		
		forward.setRedirect(false);
		forward.setPath("/app/studio/studio_edit.jsp");
	
		return forward;
	}
}


