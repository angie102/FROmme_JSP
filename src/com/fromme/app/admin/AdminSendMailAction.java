package com.fromme.app.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;
import com.fromme.app.user.dao.MessageDAO;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.MessageVO;
import com.fromme.app.user.vo.UserVO;

public class AdminSendMailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		MessageDAO m_dao = new MessageDAO();
		MessageVO m_vo = new MessageVO();
		
		String tmp = request.getParameter("reqPage");
		String auto = request.getParameter("auto");
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String receive = request.getParameter("receive");
		String send_id = request.getParameter("send_id");
		

		
		if(auto.equals("auto")) {
			PrintWriter out = response.getWriter();
			
			m_vo.setMessage_content("요청이 처리되었습니다.");
			m_vo.setMessage_title("요청이 처리되었습니다.");
			m_vo.setReceive_id(receive);
			m_vo.setSend_id(send_id);
			
			if(!m_dao.insertMessage(m_vo)) {
				response.setStatus(404);
			} 
			
			
			return null;
		}
		int reqPage = Integer.parseInt(tmp);
		m_vo.setMessage_content(content);
		m_vo.setMessage_title(title);
		m_vo.setReceive_id(receive);
		m_vo.setSend_id(send_id);
		
		m_dao.insertMessage(m_vo);
		
		forward.setRedirect(false);
		forward.setPath("/admin/mailBox.adm?page="+reqPage);
		return forward;
	}
}
