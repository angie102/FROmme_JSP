package com.fromme.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.MessageDAO;

public class MessageDeleteOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		MessageDAO m_dao = new MessageDAO();
		
		ActionForward forward = new ActionForward();
		
		int message_num = Integer.parseInt(request.getParameter("seq"));
		
		m_dao.deleteMessage(message_num);
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/message/MessageList.msg");
		
		return forward;
	}
}