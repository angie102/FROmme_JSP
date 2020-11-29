package com.fromme.app.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;
import com.fromme.app.studio.vo.StudioVO;

public class AdminApplyListViewAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		AdminDAO a_dao = new AdminDAO();
		
		// 페이지 당 승인목록 10
		int pageSize = 10;
		//사용자가 요청한 페이지 번호
		String tmp = request.getParameter("page");
		
		//처음 클릭했다면 1
		if(tmp == null) tmp = "1";
		int reqPage = Integer.parseInt(tmp);
		int endRow = pageSize * reqPage;
		int startRow = endRow - (pageSize - 1);
		
		//모든 승인목록의 갯수를 가져올 쿼리문 필요 
		int totalCnt = a_dao.getStudioApplyCount();
		
		//승인목록 데이터 쿼리 
		List<StudioVO> applyList = a_dao.getStudioApplyList(startRow, endRow);
		
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
		request.setAttribute("applyList", applyList);
		
		
		forward.setRedirect(false);
		forward.setPath("/app/admin/applyStudio.jsp");
		return forward;
	}
}
