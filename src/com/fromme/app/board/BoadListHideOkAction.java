package com.fromme.app.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;

public class BoadListHideOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		
		int category_no = Integer.parseInt(request.getParameter("cat"));
		int page = Integer.parseInt(request.getParameter("page"));
		String[] pub_checkedList = request.getParameterValues("pub_check");				//비공개로 변경할 게시글 번호 목록(String)
		List<Integer> pupCheckListPost_no = new ArrayList<Integer>();					//비공개로 변경할 게시글 번호 목록(int)
		String[] temp_nowPagesPost_no = request.getParameterValues("nowPages_post_no");	//현재 페이지의 게시글 번호 목록(String)
		int[] nowPagesPost_no = new int[temp_nowPagesPost_no.length];					//현재 페이지의 게시글 번호 목록(int)
		
		//현재 게시판 페이지의 게시글 번호 int 변경
		for(int i=0;i<temp_nowPagesPost_no.length;i++) {			
			int post_no = Integer.parseInt(temp_nowPagesPost_no[i]);			
			nowPagesPost_no[i] = post_no;
		}
		
		//현재 게시글의 시작 번호와 끝 번호 구하기
		int end_no = IntStream.of(nowPagesPost_no).max().getAsInt();
		//System.out.println("end_no : " + end_no);
		int start_no = IntStream.of(nowPagesPost_no).min().getAsInt();
		//System.out.println("start_no : " + start_no);
		
		//현재 페이지의 모든 게시글 공개로 변환
		b_dao.updateShowBoardAll(start_no, end_no);
		
		//선택한 항목이 있을 경우
		if(pub_checkedList != null) {
			//선택한 게시글 비공개로 변경
			for(int i=0;i<pub_checkedList.length;i++) {
				int post_no = Integer.parseInt(pub_checkedList[i]);
				pupCheckListPost_no.add(post_no);
				b_dao.updateHideBoard(post_no);
			}
		}
			
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/board/BoardList.bo?cat="+category_no+"&page="+page);
		return forward;
	}
}
