package com.fromme.app.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;

public class AdminListDeleteOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		AdminDAO a_dao = new AdminDAO();
		
		//리스트 삭제를 통합하여 이쪽에서 분기함
		String reqData = request.getParameter("num");
		String reqListAttr = request.getParameter("attr");
		String reqPage = request.getParameter("page");
		String column_name = "";
		String path = "";
		switch(reqListAttr) {
		case "apply":
			column_name = "studio_no";
			path = request.getContextPath()+"/admin/studioApplyList.adm?page="+reqPage;
		case "class":
			column_name = "classes_no";
			path = request.getContextPath()+"/admin//admin/classList.adm?page="+reqPage;
		case "user":
			column_name = "users_no";
			path = request.getContextPath()+"/admin/userList.adm?page="+reqPage;
		case "board":
			column_name = "post_no";
			path = request.getContextPath()+"/admin/board.adm?page="+reqPage;
		}
		int result = a_dao.deleteList(reqListAttr, column_name, reqData);
		
		forward.setRedirect(true);
		forward.setPath(path);
		return forward;
	}
}
