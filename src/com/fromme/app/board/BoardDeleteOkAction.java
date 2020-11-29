package com.fromme.app.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.files.FilesDAO;
import com.fromme.app.files.FilesVO;

public class BoardDeleteOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		
		int category_no = Integer.parseInt(request.getParameter("cat"));
		
		//게시글 하나만 삭제할 경우 post_no로 저장
		if(request.getParameter("seq") != null) {
			int post_no = Integer.parseInt(request.getParameter("seq"));
			deleteFile(post_no, request);
			b_dao.deleteBoardReplyAll(post_no);	//댓글
			b_dao.deleteBoard(post_no);	//게시글
		}else {
			//관리자 권한, 삭제할 게시글들이 여러 개일 경우
			String[] temp_checkPost_no = request.getParameterValues("del_check");
			//관리자 권한, 선택한 게시글이 있다면
			if(temp_checkPost_no != null) {
				int[] checkedPost_no = new int[temp_checkPost_no.length];
				for(int i=0;i<temp_checkPost_no.length;i++) {
					checkedPost_no[i] = Integer.parseInt(temp_checkPost_no[i]);
					deleteFile(checkedPost_no[i], request);
					b_dao.deleteBoardReplyAll(checkedPost_no[i]);	//댓글
					b_dao.deleteBoard(checkedPost_no[i]);	//게시글
				}
			}
		}
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/board/BoardList.bo?cat="+category_no);
		
		return forward;
		
	}
	private static void deleteFile(int post_no, HttpServletRequest request) {
		FilesDAO f_dao = new FilesDAO();
		//String saveFolder = "C:\\Users\\soonho\\Desktop\\it\\JSP\\workspace\\Fromme\\WebContent\\app\\upload\\files";
		String saveFolder = request.getSession().getServletContext().getRealPath("/") + "app/upload/files";
		//경로에 있는 파일 삭제
		for(FilesVO file : f_dao.getDetail(post_no)) {
			File f = new File(saveFolder + "\\" + file.getImage_path()); 
			if(f.exists()) {
				f.delete();
			}
		}
		//DB삭제
		f_dao.deleteFiles(post_no);	//파일
		f_dao.deleteFilesFromUploadThatDoNoExistInTheDatabase(request);	//DB와 비교하여 upload 폴더에 없는 파일 삭제	
	}
	
}
