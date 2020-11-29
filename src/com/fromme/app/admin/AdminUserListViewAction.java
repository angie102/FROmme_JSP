package com.fromme.app.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;
import com.fromme.app.admin.vo.UserListVO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.user.dao.UserDAO;

public class AdminUserListViewAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AdminDAO a_dao = new AdminDAO();
		
		String tmp = request.getParameter("page");
		int pageSize = 10;
		int reqPage = 0;

		if(tmp == null) tmp = "1";
		reqPage = Integer.parseInt(tmp);
		int endRow = pageSize * reqPage;
		int startRow = endRow - (pageSize - 1);

		List<UserListVO> userList = a_dao.getUserList(startRow, endRow);
		int totalCnt = a_dao.getUserCnt();

		//페이지에서 보여질 페이지의 숫자
		int startPage = ((reqPage - 1) / pageSize) * pageSize * 1;
		int endPage = startPage + 9;

		//게시글 숫자로 구분하여 총 페이지 수 구하기
		int totalPage = (totalCnt - 1) / pageSize + 1;

		if(endPage > totalPage) endPage = totalPage;

		//데이터 던지기
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("reqPage", reqPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("userList", userList);

		forward.setRedirect(false);
		forward.setPath("/app/admin/user_list.jsp");
		return forward;
	}
}
