package com.fromme.app.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;
import com.fromme.app.user.dao.MessageDAO;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.MessageVO;
import com.fromme.app.user.vo.UserVO;

public class AdminMailViewAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		MessageDAO m_dao = new MessageDAO();
		MessageVO m_vo = new MessageVO();
		
		String temp = request.getParameter("num");
		String tmp = request.getParameter("reqPage");
		
		int message_no = Integer.parseInt(temp);
		int reqPage = Integer.parseInt(tmp);
		
		
		m_vo = m_dao.getDetail(message_no);
		
		request.setAttribute("mail", m_vo);
		request.setAttribute("reqPage", reqPage);
		
		forward.setRedirect(false);
		forward.setPath("/app/admin/mail_view.jsp");
		return forward;
	}
}
