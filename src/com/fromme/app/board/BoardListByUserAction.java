package com.fromme.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.PostViewVO;
import com.fromme.app.user.dao.UserDAO;

public class BoardListByUserAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		UserDAO u_dao = new UserDAO();
		
		String users_id = request.getParameter("users_id");
		String filter = request.getParameter("filter");
		
		if(filter == null || filter.equals("")) 
			filter = "board";
		
		
		String temp = request.getParameter("page");
		int page = (temp == null) || (temp.equals("")) ? 1 : Integer.parseInt(temp);

		int pageSize = 15;
		int endRow = page * pageSize;
		int startRow = endRow - (pageSize-1);
		
		//세션 아이디 가져오기, 비회원일 경우 세션아이디 ""
		String session_id = (String) request.getSession().getAttribute("session_id");
		int users_authority = 1;
		if(session_id != null) 
			users_authority = u_dao.getUsersAuthority(session_id);
		
		//회원이 작성한 게시글 또는 댓글이 있는 게시글의 개수
		int totalCount = b_dao.getBoardListCountByUser(users_id, filter , users_authority);
		List<PostViewVO> boardList = b_dao.getBoardListByUser(startRow, endRow, users_id, filter, users_authority);
		//총 게시글의 개수
		int totalBoardCount = b_dao.getBoardListCountByUser(users_id, "board", users_authority);
		//총 댓글의 개수
		int totalReplyCount = b_dao.getReplyCountByUser(users_id);
		
		int totalPage = (totalCount - 1)/ pageSize + 1;
		int pageBlock = 10;
		int startPage = ((page-1) / pageSize) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		
		endPage = (endPage > totalPage) ? totalPage : endPage;
		
		request.setAttribute("users_id", users_id);
		request.setAttribute("boardList", boardList);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("nowPage", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalBoardCount", totalBoardCount);
		request.setAttribute("totalReplyCount", totalReplyCount);
		request.setAttribute("filter", filter);
		
		forward.setRedirect(false);
		forward.setPath("/app/board/boardListByUser.jsp");
		return forward;
	}
}
