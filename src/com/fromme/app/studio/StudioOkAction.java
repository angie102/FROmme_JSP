package com.fromme.app.studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.files.FilesDAO;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class StudioOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("studioOKAction 실행");
		StudioDAO s_dao = new StudioDAO();
		StudioVO s_vo = new StudioVO();
		FilesDAO f_dao = new FilesDAO();
		ActionForward forward = new ActionForward();
		
		//임시경로와 받은 대표이미지 파일 받아오기(?)
		String uploadPath = "";
		int fileSize= 5 * 1024 * 1024;
		MultipartRequest multi= null;
		multi = new MultipartRequest(request, uploadPath, fileSize,"UTF-8", new DefaultFileRenamePolicy());
		
		//받은데이터
		s_vo.setStudio_name("");
		s_vo.setStudio_president("");
		s_vo.setStudio_phone("");
		
		
		
		return forward;
	}
}
