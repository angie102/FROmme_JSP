package com.fromme.app.classes;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;

public class ClassesCheckDateAvailableAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		
		int classes_no = Integer.parseInt(request.getParameter("classes_no"));
		String classes_date =request.getParameter("pickedDate");

		ClassesDAO c_dao = new ClassesDAO();
		ClassesInfoVO ci_vo = new ClassesInfoVO();
		ci_vo.setClasses_no(classes_no);
		ci_vo.setClasses_date(classes_date);
		
		ci_vo = c_dao.getClassesAvailableNumBySelectedDate(ci_vo);
		
		out.println(ci_vo.getClasses_limit() - ci_vo.getClasses_apply_num());
		out.close();
		
		return null;	
	}
}
