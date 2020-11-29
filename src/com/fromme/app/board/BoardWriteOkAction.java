package com.fromme.app.board;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.PostVO;
import com.fromme.app.files.FilesDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDAO b_dao = new BoardDAO();
		PostVO p_vo = new PostVO();
		FilesDAO f_dao = new FilesDAO();
		ActionForward forward = new ActionForward();
		
		//파일 저장할 경로
		//String uploadPath = "C:\\Users\\soonho\\Desktop\\it\\JSP\\workspace\\Fromme\\WebContent\\app\\upload\\files";
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "app/upload/files";
		
		int fileSize = 5 * 1024 * 1024;
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String post_contents = multi.getParameter("editordata");
		//System.out.println("본문 : " + post_contents);
		p_vo.setPost_title(multi.getParameter("board_title"));
		p_vo.setPost_content(post_contents);
		p_vo.setUsers_id(multi.getParameter("board_id"));
		int category_no = Integer.parseInt(multi.getParameter("cat"));
		p_vo.setCategory_no(category_no);
		PrintWriter out = response.getWriter();
		
		//본문에 첨부된 이미지 태그의 파일명 추출
		List<String> imageFileNamesFromBoardContents = b_dao.getImagesFileNameFromBoardContents(post_contents);
		//본문에 첨부된 동영상 태그의 URL 한 개만 추출(썸네일용이기 때문에 여러 개가 있어도 한 개만 추출합니다.)
		String videoUrlFromBoardContents = b_dao.getVideoUrlFromBoardContents(post_contents);
		
		//게시판 먼저 DB 저장
		if(b_dao.insertBoard(p_vo)) {
			//게시판 DB 저장 성공 시 파일, 에디터의 이미지 DB 저장
			if(f_dao.insertFiles(b_dao.getCurrentBoardSeq(), multi/*, tempPath, uploadPath*/) 
						//에디터에 저장된 이미지
					&& f_dao.insertImages(b_dao.getCurrentBoardSeq(), imageFileNamesFromBoardContents)) {
					//에디터에 저장된 비디오 태그
				if(videoUrlFromBoardContents != null) 	//에디터에 저장된 비디오 태그가 있다면
						f_dao.insertVideoUrl(b_dao.getCurrentBoardSeq(), videoUrlFromBoardContents);
						
				forward.setRedirect(true);
				forward.setPath(request.getContextPath() + "/board/BoardList.bo?cat=" + category_no);
				return forward;					
			}
		}
		out.println("<script>");
		out.println("alert('게시글 등록 실패. 다시 시도해 주세요.');");
		out.println("</script>");
		out.close();
		return null;
	}
}
