package com.fromme.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.PostVO;

public class BoardModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		int post_no = Integer.parseInt(request.getParameter("seq"));
		PostVO post = b_dao.getDetail(post_no);
		
		request.setAttribute("boardBean", post);
		
		forward.setRedirect(false);
		forward.setPath("/app/board/boardModify.jsp");
		return forward;
	}
}
