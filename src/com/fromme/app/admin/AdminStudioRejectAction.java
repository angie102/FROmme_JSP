package com.fromme.app.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;

public class AdminStudioRejectAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		AdminDAO a_dao = new AdminDAO();
		
		String tmp = request.getParameter("stdno");
		String temp = request.getParameter("page");
		
		if(temp == null) {
			temp = "1";
		}
		int reqPage = Integer.parseInt(temp);
		int studio_no = Integer.parseInt(tmp);
		int result = a_dao.setStudioAuthority(5, studio_no);
		
		//오류처리 필요할듯 
		if(result != 1) {
			
		}
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/admin/studioApplyList.adm?page="+reqPage);
		return forward;
	}
}
