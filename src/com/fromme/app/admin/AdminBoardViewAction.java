package com.fromme.app.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.PostViewVO;
import com.fromme.app.user.dao.UserDAO;

public class AdminBoardViewAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		UserDAO u_dao = new UserDAO();
		
		System.out.println(request.getLocalAddr());
		System.out.println(request.getRemoteAddr());
		
		String tmp = request.getParameter("page");
		String cTemp = request.getParameter("category");
		int category = 0;
		int reqPage = 0;
		String path = "";
		try {
			category = Integer.parseInt(cTemp);
			switch(category) {
			case 1:
				path = "/app/admin/noticeList.jsp";
				break;
			default:
				path = "/app/admin/boardList.jsp";
				break;
			}
		} catch(Exception e) {
			return null;
		}
		
		int pageSize = 10;

		if(tmp == null) tmp = "1";
		reqPage = Integer.parseInt(tmp);
		int endRow = pageSize * reqPage;
		int startRow = endRow - (pageSize - 1);
		
		List<PostViewVO> postList = b_dao.getBoardListSorted(startRow, endRow, category, "date", 3); 
		int totalCnt = b_dao.getBoardListCount(category, 3);

		//페이지에서 보여질 페이지의 숫자
		int startPage = ((reqPage - 1) / pageSize) * pageSize * 1;
		int endPage = startPage + 9;

		//게시글 숫자로 구분하여 총 페이지 수 구하기
		int totalPage = (totalCnt - 1) / pageSize + 1;

		if(endPage > totalPage) endPage = totalPage;

		//데이터 던지기
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("reqPage", reqPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("postList", postList);
		request.setAttribute("category", category);

		forward.setRedirect(false);
		forward.setPath(path);
		return forward;
	}
}
