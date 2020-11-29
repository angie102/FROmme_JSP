package com.fromme.app.studio;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.studio.dao.StudioDAO;

public class StudioClassViewAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		//유저 정보
		
		//공방 정보
		
		//클래스정보 (배열)
		List<ClassesVO> c_vo_list = new ArrayList<ClassesVO>();
		ClassesVO c_vo = new ClassesVO();
		
		//해당정보 가져오기 DAO
		StudioDAO s_dao = new StudioDAO();
		
		c_vo_list =  s_dao.getClassContents(1);
		
		//c_vo 추가 
		request.setAttribute("c_vo_list", c_vo_list);
		
		
		forward.setRedirect(false);
		forward.setPath("app/studio/studio.jsp");
		
		return forward;
	}
}
