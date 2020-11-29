package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.MessageDAO;
import com.fromme.app.user.vo.MessageVO;

public class MessageReplyAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MessageDAO m_dao = new MessageDAO();
		MessageVO m_vo = new MessageVO();
		
		ActionForward forward = new ActionForward();
		
		//첨부한 파일이 업로드 될 서버 경로 설정
		//request.getServletContext().getRealPath("/") + "\\upload"
		String reply_id = request.getParameter("reply_id");
		
		request.setAttribute("reply_id", reply_id);	
		forward.setRedirect(false);
		forward.setPath("/app/user/mailbox_send.jsp");
		
		return forward;
		
	}
}






















