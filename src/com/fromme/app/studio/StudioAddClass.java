package com.fromme.app.studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;

public class StudioAddClass implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("studioAddClass실행");
		ActionForward forward = new ActionForward();
		ClassesDAO c_dao = new ClassesDAO();
		ClassesVO c_vo = new ClassesVO();
		
		StudioDAO s_dao = new StudioDAO();
		StudioVO s_vo = new StudioVO();
		System.out.println("StudioAddClass 데이터");
		//입력한 데이터
		String class_name = request.getParameter("class_name");
		String class_teacher = request.getParameter("class_president");
		String class_price = request.getParameter("class_price");
		String class_member_limit = request.getParameter("class_member_limit");
		String class_start_date = request.getParameter("class_start_date");
		String class_finish_date = request.getParameter("class_finish_date");
		String class_date = request.getParameter("class_date");
		String class_contents = request.getParameter("class_contents");
		
		
		
		int class_price_p = Integer.parseInt(class_price);
		int class_limit = Integer.parseInt(class_member_limit);
		
		//check
		System.out.println("class_name : "+class_name);
	
		// 클래스 타입으로 받아온 데이터 생성
		c_vo.setClasses_name(class_name);
		c_vo.setClasses_teacher(class_teacher);
		c_vo.setClasses_price(class_price_p);
		c_vo.setClasses_limit(class_limit);
		c_vo.setClasses_start(class_start_date);
		c_vo.setClasses_end(class_finish_date);
		c_vo.setClasses_registerdate(class_date);
		c_vo.setClasses_content(class_contents);
		c_vo.setStudio_no(3);
		
		//새로운 클래스 만들기
		s_dao.createNewClass(c_vo);
		
		forward.setRedirect(false);
		forward.setPath("/app/studio/studio.jsp");
		
		

		return forward;
	}

}
