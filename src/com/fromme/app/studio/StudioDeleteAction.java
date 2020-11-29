package com.fromme.app.studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.studio.dao.StudioDAO;

public class StudioDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		
		StudioDAO s_dao = new StudioDAO();
		
		//세션 아이디
		String session_id = request.getParameter("session_id");
		System.out.println("session 아이디 : "+session_id);
		
		//해당 공방 번호 가져오기
		int class_no = Integer.parseInt(request.getParameter("class_no"));
		System.out.println("class_no : "+class_no);
		//인포 삭제
		s_dao.deleteClassInfo(class_no);
		//이미지 삭제
		s_dao.deleteClassImage(class_no);
		//해당 클래스 삭제 
		s_dao.deleteClass(class_no);
		
		forward.setRedirect(false);
		forward.setPath("/studio/StudioViewAction.std");
		
		return forward;
	}
}
