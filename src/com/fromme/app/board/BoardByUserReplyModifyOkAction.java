package com.fromme.app.board;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;

public class BoardByUserReplyModifyOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		Map<String, Object> editReply = new HashMap<String, Object>();
		
		int replys_no = Integer.parseInt(request.getParameter("replys_no"));
		int post_no = Integer.parseInt(request.getParameter("seq"));
		int page = Integer.parseInt(request.getParameter("page"));
		int category_no = Integer.parseInt(request.getParameter("cat"));
		String filter = request.getParameter("filter");
		String users_id = request.getParameter("users_id");
		
		String replys_content = request.getParameter("board_contents"+request.getParameter("num"));
		
		editReply.put("replys_no",replys_no);
		editReply.put("replys_content",replys_content);
		
		String query = "&cat=" + category_no + "&seq=" + post_no + "&page=" + page + "&filter=" + filter + "&users_id=" + users_id;
		
		if(b_dao.updateBoardReply(editReply)) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/board/BoardViewByUser.bo?" + query);
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
