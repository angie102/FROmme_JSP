package com.fromme.app.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.ActionForward;

public class BoardFrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;

		switch(command) {
		
		case "/board/BoardList.bo":		//게시판 이동 전 게시판 페이징 처리
			try {
				forward = new BoardListAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardWrite.bo":	//게시글 쓰기 페이지 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/board/boardWrite.jsp");
			break;
		case "/board/BoardWriteOk.bo":	//게시글 등록 처리
			try {
				forward = new BoardWriteOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardView.bo":		//게시글 상세 보기
			try {
				forward = new BoardViewAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/FileDownload.bo":	//파일 다운로드 처리
			try {
				forward = new FileDownloadAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardModify.bo":	//게시글 수정 페이지
			try {
				forward = new BoardModifyAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardModifyOk.bo" :	//게시글 수정 처리
			try {
				forward = new BoardModifyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardDeleteOk.bo":	//게시글 삭제 처리
			try {
				forward = new BoardDeleteOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardReplyOk.bo":	//댓글 등록 처리
			try {
				forward = new BoardReplyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardReplyModifyOk.bo":	//댓글 수정 처리
			try {
				forward = new BoardReplyModifyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardReplyDeleteOk.bo":	//댓글 삭제 처리
			try {
				forward = new BoardReplyDeleteOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/SummernoteUploadOkAction.bo":	//썸머노트 웹 에디터에 이미지 첨부 처리
			try {
				forward = new SummernoteUploadOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardListByUser.bo" :	//회원이 작성한 게시글,댓글 보기
			try {
				forward = new BoardListByUserAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardViewByUser.bo" :	//회원이 작성한 게시글 상세 보기
			try {
				forward = new BoardViewByUserAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardListHideOk.bo" :	//관리자 권한, 게시판 공개, 비공개 여부 처리
			try {
				forward = new BoadListHideOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardByUserReplyOk.bo" :	//회원이 작성한 게시글에서 댓글 등록
			try {
				forward = new BoardByUserReplyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardByUserReplyModifyOk.bo" :	//회원이 작성한 게시글에서 댓글 수정
			try {
				forward = new BoardByUserReplyModifyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardByUserReplyDeleteOk.bo" :	//회원이 작성한 게시글에서 댓글 삭제
			try {
				forward = new BoardByUserReplyDeleteOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			forward = new com.fromme.action.ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/main/error/404.jsp");
		}

		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
}


