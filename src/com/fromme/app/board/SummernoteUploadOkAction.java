package com.fromme.app.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class SummernoteUploadOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 실제 이미지를 업로드할 경로	
		//String uploadPath = "C:\\Users\\soonho\\Desktop\\it\\JSP\\workspace\\Fromme\\WebContent\\app\\upload\\images";
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "app/upload/images";
		
		int size = 10 * 1024 * 1024;  // 업로드 사이즈 제한 10M 이하
		String fileName = ""; // 파일명

		try{
			// 파일업로드 및 업로드 후 파일명 가져옴
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement(); 
			fileName = multi.getFilesystemName(file);
		}catch(Exception e){
			e.printStackTrace();
		}

		int count = 0;
		String newFileName = "board_" + fileName;

		//업로드 후 파일명 수정 작업 (썸머노트 에디터에 첨부된 파일들이므로 구별하기 위해 접두사 board_ 추가)
		//실제 이미지는 images 폴더에 저장
		File oldFile = new File(uploadPath + '/' + fileName);
		File newFile = new File(uploadPath + '/' + newFileName);

		int dot = newFileName.lastIndexOf(".");
		String body = newFileName.substring(0, dot);
		String ext = newFileName.substring(dot);

		//변경된 파일명으로 기존 파일명 수정 DefaultFileRenamePolicy와 동일하게 작동
		while(!oldFile.renameTo(newFile)) {
			count++;
			newFileName = body + count + ext;
			newFile = new File(uploadPath + '/' + newFileName);
		}

		// 업로드된 경로와 파일명을 통해 이미지의 경로를 생성
		//uploadPath = request.getContextPath() +"/app/upload/images/"+newFileName;
		uploadPath = "/app/upload/images/" + newFileName;
		PrintWriter out = response.getWriter();
		out.println(uploadPath);
		//테스트
		System.out.println("uploadPath : " + uploadPath);
		out.close();
		return null;
	}
}
