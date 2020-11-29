package com.fromme.app.board;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.PostVO;
import com.fromme.app.files.FilesDAO;
import com.fromme.app.files.FilesVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardModifyOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		PostVO edit_post = new PostVO();
		FilesDAO f_dao = new FilesDAO();
		
		//String saveFolder = "C:\\Users\\soonho\\Desktop\\it\\JSP\\workspace\\Fromme\\WebContent\\app\\upload\\files";
		String saveFolder = request.getSession().getServletContext().getRealPath("/") + "app/upload/files";
		int fileSize = 5 * 1024 * 1024;
		
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			int post_no = Integer.parseInt(multi.getParameter("seq"));
			int category_no = Integer.parseInt(multi.getParameter("cat"));
			//경로에 있는 파일 삭제
			for(FilesVO file : f_dao.getDetail(post_no)) {
				File f = new File(saveFolder + "\\" + file.getImage_path()); 
				if(f.exists()) {
					f.delete();
				}
			}
			String post_contents = multi.getParameter("editordata");
			//본문에 첨부된 이미지 태그의 파일명 추출
			List<String> imageFileNamesFromBoardContents = b_dao.getImagesFileNameFromBoardContents(post_contents);
			
			//기존 DB의 파일 삭제 후 다시 등록(파일, 이미지)
			f_dao.deleteFiles(post_no);
			
			//파일 태그로 첨부한 파일 DB 저장
			f_dao.insertFiles(post_no, multi);
			//에디터에 첨부한 파일 DB 저장
			f_dao.insertImages(post_no, imageFileNamesFromBoardContents);
			
			edit_post.setPost_no(post_no);
			edit_post.setPost_title(multi.getParameter("board_title"));
			edit_post.setPost_content(post_contents);
			
			b_dao.updateBoard(edit_post);
			
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/board/BoardView.bo?seq=" + post_no + "&cat=" + category_no);
			
			return forward;
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>");
			out.println("alert('게시글 수정 실패. 다시 시도해주세요.);");
			out.println("</script>");
			out.close();
		}
		
		return null;
	}
}
