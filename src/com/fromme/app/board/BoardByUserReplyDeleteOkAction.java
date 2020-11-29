package com.fromme.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;

public class BoardByUserReplyDeleteOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		
		int replys_no = Integer.parseInt(request.getParameter("replys_no"));
		int post_no = Integer.parseInt(request.getParameter("seq"));
		int category_no = Integer.parseInt(request.getParameter("cat"));
		int page = Integer.parseInt(request.getParameter("page"));
		String filter = request.getParameter("filter");
		String users_id = request.getParameter("users_id");
		
		b_dao.deleteBoardReply(replys_no);
		
		String query = "&cat=" + category_no + "&seq=" + post_no + "&page=" + page + "&filter=" + filter + "&users_id=" + users_id;
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/board/BoardViewByUser.bo?" + query);
		return forward;
	}
}
