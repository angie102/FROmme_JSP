package com.fromme.app.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.ReplyVO;

public class BoardByUserReplyOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		ReplyVO r_vo = new ReplyVO();
		
		int post_no = Integer.parseInt(request.getParameter("seq"));
		int category_no = Integer.parseInt(request.getParameter("cat"));
		int page = Integer.parseInt(request.getParameter("page"));
		String filter = request.getParameter("filter");
		String users_id = request.getParameter("users_id");
		
		//세션 id로 가져와야함.
		String replys_id = (String)request.getSession().getAttribute("session_id");
		//String replys_id = "ee";	//test용
		String reply_contents = request.getParameter("reply_contents");
		
		r_vo.setPost_no(post_no);
		r_vo.setReplys_content(reply_contents);
		r_vo.setReplys_id(replys_id);
		
		String query = "&cat=" + category_no + "&seq=" + post_no + "&page=" + page + "&filter=" + filter + "&users_id=" + users_id;

		if(b_dao.insertBoardReply(r_vo)) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/board/BoardViewByUser.bo?" + query);
			return forward;
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('댓글 등록 실패. 다시 시도해 주세요.');");
		out.println("</script>");
		out.close();	
		return null;
		
		
	}
}
