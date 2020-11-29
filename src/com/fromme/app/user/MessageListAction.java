package com.fromme.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.user.dao.MessageDAO;

public class MessageListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MessageDAO m_dao = new MessageDAO();
		
		//사용자가 요청한 페이지 번호
		String temp = request.getParameter("page");
		
		//요청한 페이지가 없다면 default로 1페이지를 응답해주고,
		//요청한 페이지가 있다면 해당 페이지로 응답해준다.
		int page = temp == null ? 1 : Integer.parseInt(temp);
		
		//한 페이지당 10개의 게시글이 보이도록 설정
		int pageSize = 10;
		
		//한 페이지에서 가장 마지막 글 번호
		int endRow = page * pageSize;
		
		//한 페이지에서 첫번째 글 번호
		int startRow = endRow - (pageSize - 1);
		
		/*
		 * 1. board.xml에 전체 게시글 개수를 가져오는 쿼리문 작성(완료)
		 * 2. BoardDAO에 위에서 작성한 쿼리문을 사용하는 메소드 구현
		 * 3. Controller(현재 페이지)에서 DAO메소드 사용 후 totalCnt에 대입
		 */
		String session_id = (String)session.getAttribute("session_id");
		
		int totalCnt = m_dao.getMessageCnt(session_id);
		
		//하나의 응답 페이지에서 보여질 페이지 수(1~10까지 보여준다)
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		
		//게시글의 총 개수로 페이지 수를 구분하여 총 페이지 수를 totalPage에 담아준다.
		int totalPage = (totalCnt - 1) / pageSize + 1;
		
		//실제 마지막 페이지와 연산으로 구한 마지막 페이지를 비교하여 일치하도록 해준다.
		endPage = endPage > totalPage ? totalPage : endPage;
		
		//응답페이지로 전달할 결과값들
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("nowPage", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("messageList", m_dao.getMessageList(startRow, endRow, session_id));
		
		/*System.out.println(request.getParameter("totalPage"));
		System.out.println(request.getParameter("totalCnt"));
		System.out.println(request.getParameter("nowPage"));
		System.out.println(request.getParameter("startPage"));
		System.out.println(request.getParameter("endPage"));
		System.out.println(request.getParameter("messagePage"));*/
		/*forward 방식으로 전송해야 request 객체에 담긴 정보를 응답 페이지에서 사용할 수 있다.*/
		forward.setRedirect(false);
		forward.setPath("/app/user/mailbox_list.jsp");
		
		return forward;
		
	}
}