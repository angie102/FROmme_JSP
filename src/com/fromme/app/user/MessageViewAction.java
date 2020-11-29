package com.fromme.app.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.MessageDAO;
import com.fromme.app.user.vo.MessageVO;

public class MessageViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		MessageDAO m_dao = new MessageDAO();
		MessageVO m_vo = new MessageVO();
		
		int message_num = Integer.parseInt(request.getParameter("seq"));
		
		//조회수 1 증가
		m_dao.updateMessageCount(message_num);
		
		//사용자가 요청한 게시글의 번호를 통해 게시글 정보를 모두 가져온다.
		m_vo = m_dao.getDetail(message_num);
		
		if(m_vo != null) {
			//응답 페이지로 해당 게시글의 정보를 전달해야 하므로 request객체에 저장한다.
			request.setAttribute("messageBean", m_vo);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/user/mailbox_content.jsp");
			return forward;
		}
		
		return null;
	}
}


