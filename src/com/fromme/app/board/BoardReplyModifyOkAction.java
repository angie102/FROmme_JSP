package com.fromme.app.board;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;

public class BoardReplyModifyOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		Map<String, Object> editReply = new HashMap<String, Object>();
		
		int replys_no = Integer.parseInt(request.getParameter("replys_no"));
		int post_no = Integer.parseInt(request.getParameter("seq"));
		int page = Integer.parseInt(request.getParameter("page"));
		int category_no = Integer.parseInt(request.getParameter("cat"));
		String field = request.getParameter("field");
		String sort = request.getParameter("sort");
		String listtype = request.getParameter("listtype");
		String search = request.getParameter("search");
		String catname = URLEncoder.encode(request.getParameter("catname"), "UTF-8");
		
		String replys_content = request.getParameter("board_contents"+request.getParameter("num"));
		
		editReply.put("replys_no",replys_no);
		editReply.put("replys_content",replys_content);
		
		String query = "seq=" + post_no + "&cat=" + category_no + "&field=" + field + "&search=" + search + 
				"&listtype=" + listtype + "&sort=" + sort + "&page=" + page + "&catname=" + catname;
		
		if(b_dao.updateBoardReply(editReply)) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/board/BoardView.bo?" + query);
			return forward;
		}
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('댓글 수정 실패. 다시 시도해 주세요.');");
		out.println("</script>");
		out.close();		
		return null;
	}
}
