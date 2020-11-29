package com.fromme.app.studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.studio.dao.StudioDAO;

public class UserStudioCreateOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		//세션아이디
		String session_id = (String) request.getSession().getAttribute("session_id");
		System.out.println("session 아이디 : "+session_id);
		
		StudioDAO s_dao = new StudioDAO();
		
		int studio_no =s_dao.getStudioNum(session_id);
		
		String std_no = Integer.toString(studio_no);
		
		// 확인용
		System.out.println(std_no);
	
		//s_dao.insertStudioNum(session_id, std_no);
		
		return forward;
	}

}
