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

public class BoardViewByUserAction implements Action {
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
		String users_id = request.getParameter("users_id");
		String filter = request.getParameter("filter");
		Map<String, Object> post = new HashMap<>();
		
		//세션 아이디 가져오기, 비회원일 경우 세션아이디 ""
		String session_id = (String) request.getSession().getAttribute("session_id");
		int users_authority = 1;
		if(session_id != null) 
			users_authority = u_dao.getUsersAuthority(session_id);
		
		post.put("post_no", post_no);
		post.put("users_id", users_id);
		post.put("filter", filter);
		post.put("users_authority", users_authority);
		
		//다음 게시글 가져오기
		PostVO nextBoard = b_dao.getNextBoardByUser(post, filter);
		//이전 게시글 가져오기
		PostVO prevBoard = b_dao.getPrevBoardByUser(post, filter);
		
		//나중에 세션처리 해야함. 본인일 경우 조회수 증가 x(세션id로 비교)
		
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
				forward.setPath("/app/board/boardViewByUser.jsp");		
			}
		}
		
		return forward;
	}
}
