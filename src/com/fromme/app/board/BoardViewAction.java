package com.fromme.app.board;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.PostVO;
import com.fromme.app.board.vo.ReplyVO;
import com.fromme.app.files.FilesDAO;
import com.fromme.app.files.FilesVO;
import com.fromme.app.user.dao.UserDAO;

public class BoardViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		PostVO p_vo = new PostVO();
		FilesDAO f_dao = new FilesDAO();
		UserDAO u_dao = new UserDAO();

		//쿠키
		Cookie[] cookies = request.getCookies();
		
		int post_no = Integer.parseInt(request.getParameter("seq"));
		int category_no = Integer.parseInt(request.getParameter("cat"));
		String sort = request.getParameter("sort");
		
		//세션 아이디 가져오기, 비회원일 경우 세션아이디 ""
		String session_id = (String) request.getSession().getAttribute("session_id");
		int users_authority = 1;
			
		if(session_id != null) 
			users_authority = u_dao.getUsersAuthority(session_id);
				
		//정렬값이 없다면 기본값은 date로 설정
		if(sort == null || sort.equals(""))
			sort = "date";
		
		//검색 기준  (제목,아이디)
		String field = request.getParameter("field");
		if((field == null) || (field != null && field.equals("title")))
			field = "POST_TITLE";
		
		//검색 값
		String word = request.getParameter("search");
		
		//사용자가 검색값을 요청했는지 여부 true일 경우 검색값이 있음
		boolean isSearched = !(field == null || field.equals("")) && !(word == null || word.equals(""));
		
		//users_authority 가져와야 함, user 테이블에서 쿼리문 보내기
		
		Map<String,Object> post = new HashMap<>();
		
		post.put("post_no", post_no);
		post.put("category_no", category_no);
		post.put("users_authority", users_authority);
		
		//다음 게시글
		PostVO nextBoard = null;
		PostVO prevBoard = null;
		
		/*
		 * 각 메소드마다 users_authority 매개변수 추가 
		 */
		//검색값이 있을 경우
		if(isSearched) {
			//검색값 추가
			post.put("field", field);
			post.put("word", word);
			//다음 게시글 가져오기
			nextBoard = b_dao.getNextBoardSearchedAndSorted(post, sort);
			//이전 게시글 가져오기
			prevBoard = b_dao.getPrevBoardSearchedAndSorted(post, sort);
		}else {
			//검색값이 없을 경우
			nextBoard = b_dao.getNextBoardSortedBy(post, sort);
			prevBoard = b_dao.getPrevBoardSortedBy(post, sort);
		}

		
		// 비교하기 위해 새로운 쿠키
		Cookie viewCookie = null;
 
        // 쿠키가 있을 경우 
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌 
                if (cookies[i].getName().equals("boardCookie"+post_no)) { 
                    //System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
                    viewCookie = cookies[i];
                }
            }
        }
        
		//게시글 가져오기
		p_vo = b_dao.getDetail(post_no);
		
		//본인일 경우 조회수 증가 x(세션id로 비교)
		//새로고침 시 쿠키로 비교해서 해당 게시글 번호가 쿠키에 있다면 조회수 증가 x
		if(viewCookie == null && !p_vo.getUsers_id().equals(session_id)) {
			Cookie newCookie = new Cookie("boardCookie"+post_no, "" + post_no);
			response.addCookie(newCookie);
			//조회수 증가
			b_dao.updateReadCount(post_no);        	
		}
		
		request.setAttribute("nextBoard", nextBoard);
		request.setAttribute("prevBoard", prevBoard);
		
		List<FilesVO> filesList = f_dao.getDetail(post_no);
		List<ReplyVO> replyBeanList = b_dao.getReplyList(post_no);
		if(p_vo != null) {
			request.setAttribute("boardBean", p_vo);	
			request.setAttribute("replyBeanList", replyBeanList);
			if(filesList != null) {
				request.setAttribute("filesBeanList", filesList);
				forward.setRedirect(false);
				forward.setPath("/app/board/boardView.jsp");		
			}
		}
		
		return forward;
		
	}
}
