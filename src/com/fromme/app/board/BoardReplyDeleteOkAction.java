package com.fromme.app.board;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;

public class BoardReplyDeleteOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		
		int replys_no = Integer.parseInt(request.getParameter("replys_no"));
		int post_no = Integer.parseInt(request.getParameter("seq"));
		int category_no = Integer.parseInt(request.getParameter("cat"));
		int page = Integer.parseInt(request.getParameter("page"));
		String field = request.getParameter("field");
		String sort = request.getParameter("sort");
		String listtype = request.getParameter("listtype");
		String search = request.getParameter("search");
		String catname = URLEncoder.encode(request.getParameter("catname"), "UTF-8");
		
		b_dao.deleteBoardReply(replys_no);
		
		String query = "seq=" + post_no + "&cat=" + category_no + "&field=" + field + "&search=" + search + 
				"&listtype=" + listtype + "&sort=" + sort + "&page=" + page + "&catname=" + catname;
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/board/BoardView.bo?" + query);
		return forward;
	}
}
