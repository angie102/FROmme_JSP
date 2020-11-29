package com.fromme.app.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.PostImageVO;
import com.fromme.app.board.vo.PostVO;
import com.fromme.app.board.vo.PostViewVO;
import com.fromme.app.files.FilesDAO;
import com.fromme.app.user.dao.UserDAO;

public class BoardListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		UserDAO u_dao = new UserDAO();
		Cookie[] cookies = request.getCookies();
		
		//등록되어 있는 게시판 쿠키가 있을 경우 전체 삭제
		if(cookies != null){
			for(int i=0; i < cookies.length; i++){
				if(cookies[i].getName().contains("boardCookie")) {
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		//세션 아이디 가져오기, 비회원일 경우 세션아이디 ""
		String session_id = (String) request.getSession().getAttribute("session_id");
		int users_authority = 1;
		if(session_id != null) 
			users_authority = u_dao.getUsersAuthority(session_id);
		//int users_authority = u_dao.getUsersAuthority("test1");
		
		//정렬 방법
		//정렬 방법이 선택되지 않았다면 기본값은 최신순
		String sort = request.getParameter("sort");
		if(sort == null || sort.equals("")) 
			sort = "date";
		

		//검색 기준  (제목,아이디)
		String field = request.getParameter("field");
		if(field != null && field.equals("title"))
			field = "POST_TITLE";
		
		//검색 값
		String word = request.getParameter("search");
		
		//사용자가 검색값을 요청했는지 여부 true일 경우 검색값이 있음
		boolean isSearched = !(field == null || field.equals("")) && !(word == null || word.equals(""));
		
		
		//리스트 타입(텍스트 = 10, 이미지 = 8)
		int pageSize = 8;
		String listtype = request.getParameter("listtype");
		if(listtype == null || listtype.equals("")) 
			listtype = "text";
		
		if(listtype.equals("text"))
			pageSize = 10;
		
		//사용자가 요청한 페이지 번호
		String temp = request.getParameter("page");
		int page = (temp == null) || (temp.equals("")) ? 1 : Integer.parseInt(temp);
		
		//게시판 카테고리번호
		String category_no_temp = request.getParameter("cat");
		int category_no = (category_no_temp == null) || (category_no_temp.equals("")) ? 1 : Integer.parseInt(category_no_temp);
		//게시판 카테고리 이름
		String category_name = b_dao.getBoardCategoryName(category_no);
		
		int endRow = page * pageSize;
		int startRow = endRow - (pageSize-1);
		
		int totalCount = 0;
		//listtype이 image, text에 따라서 생성할 ArrayList타입이 결정
		List<? extends PostVO> boardList = null;
		
		/*
		 * 각 메소드마다 users_authority 매개변수 추가
		 */
		if(listtype.equals("image")) {
			//게시판 이미지 리스트
			boardList = new ArrayList<PostImageVO>();			
			//검색값에 대한 게시글 이미지 리스트 가져오기
			if(isSearched) {
				boardList = b_dao.getBoardImageListSearchedAndSorted(startRow, endRow, category_no, field, word, sort, users_authority);
				//검색값에 대한 게시글 개수
				totalCount = b_dao.getBoardListCountBySearchWord(field, word, category_no, users_authority);
			}else {
				//전체 게시글 리스트 가져오기
				boardList = b_dao.getBoardImageListSorted(startRow, endRow, category_no, sort, users_authority);
				//전체 게시글 개수
				totalCount = b_dao.getBoardListCount(category_no, users_authority);			
			}
		}else {			
			//게시판 텍스트 리스트
			boardList = new ArrayList<PostViewVO>();
			//검색값에 대한 게시글 리스트 가져오기
			if(isSearched) {
				boardList = b_dao.getBoardListSearchedAndSorted(startRow, endRow, category_no, field, word, sort, users_authority);
				//검색값에 대한 게시글 개수
				totalCount = b_dao.getBoardListCountBySearchWord(field, word, category_no, users_authority);
			}else {
				//전체 게시글 리스트 가져오기
				boardList = b_dao.getBoardListSorted(startRow, endRow, category_no, sort, users_authority);
				//전체 게시글 개수
				totalCount = b_dao.getBoardListCount(category_no, users_authority);			
			}
		}

		
		int totalPage = (totalCount - 1)/ pageSize + 1;
		int pageBlock = 10;
		int startPage = ((page-1) / pageSize) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		
		endPage = (endPage > totalPage) ? totalPage : endPage;
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("nowPage", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("category_name", category_name);
		request.setAttribute("category_no", category_no);
		request.setAttribute("sort", sort);
		request.setAttribute("field", field);
		request.setAttribute("search", word);
		request.setAttribute("listtype", listtype);
		request.setAttribute("users_authority", users_authority);
		
		forward.setRedirect(false);
		forward.setPath("/app/board/boardList.jsp");
		return forward;
	}
}
