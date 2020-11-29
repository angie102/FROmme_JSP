package com.fromme.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.MessageDAO;
import com.fromme.app.user.vo.MessageVO;

public class MessageWriteOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		MessageDAO m_dao = new MessageDAO();
		MessageVO m_vo = new MessageVO();
		
		ActionForward forward = new ActionForward();
		
		m_vo.setSend_id(request.getParameter("send_id"));
		m_vo.setReceive_id(request.getParameter("receive_id"));
		
		// 제목에 값이 없으면 "제목없음" 기입됨.
		if (request.getParameter("message_title") == null) {
			m_vo.setMessage_title("제목없음");
		} else {
			m_vo.setMessage_title(request.getParameter("message_title"));
		}
		// 내용없으면 "" 기입.
		if (request.getParameter("message_content") == null) {
			m_vo.setMessage_content("");
		} else {
			m_vo.setMessage_content(request.getParameter("message_content"));
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		
		if(m_dao.insertMessage(m_vo)) {
				forward.setRedirect(true);
				forward.setPath(request.getContextPath() + "/message/MessageList.msg");
				return forward;
		}
		
		out.println("<script>");
		out.println("alert('게시글 등록 실패. 다시 시도해주세요');");
		out.println("</script>");
		out.close();
		
		return null;
	}
}






















