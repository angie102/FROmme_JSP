package com.fromme.app.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;

public class FileDownloadAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String filename = request.getParameter("image_path");
		//String savePath = "C:\\Users\\soonho\\Desktop\\it\\JSP\\workspace\\Fromme\\WebContent\\app\\upload\\files";
		String savePath = request.getSession().getServletContext().getRealPath("/") + "app/upload/files";
		PrintWriter out = response.getWriter();
		//파일 불러오기
		InputStream in = null;
		//다운로드 구현하기
		OutputStream os = null;
		//해당 파일의 정보를 담는 객체
		File file = null;
		//사용자가 요청한 브라우저 정보
		String client = "";
		boolean check = true;
		//파일을 읽어서 스트림에 담기
		try {
			try {
				//해당 경로에 있는 사용자가 요청한 파일 정보
				file = new File(savePath, filename);
				//해당 파일을  byte로 읽어옵니다.
				in = new FileInputStream(file);
			} catch (FileNotFoundException fnfe) {
				System.out.println(fnfe + "\nFileDownloadAction.java 오류");
				//해당 경로에 파일이 존재하지 않으면 check에 false 저장
				check = false;
			}
			//"User-Agent" KEY값을 헤더에 전달하여 브라우저 정보 가져오기
			client = request.getHeader("User-Agent");
			
			//파일 다운로드 헤더 지정
			response.reset();//응답객체 초기화
			response.setContentType("application/octet-stream");//응답할 컨텐트 타입 설정
			response.setHeader("Content-Description", "JSP Generated Data");//컨텐트 설명 추가
			
			if(check) {
				//해당 파일의 인코딩 설정
				filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
				//Microsoft
				//IE(Internet Explorer)
				if(client.indexOf("MSIE") != -1) {
					response.setHeader("Content-Disposition", "attachment;filename="+filename);
				}else {//그 외 브라우저에서 사용자가 요청했을 때
					//파일 이름 앞뒤로 큰 따옴표를 작성해줍니다.
					response.setHeader("Content-Disposition", "attachment;filename=\""+filename + "\"");
					//그 외 브라우저일 경우 컨텐트 타입을 다시 한 번 설정해줍니다.
					response.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
				}
			}
			out.flush();
			//컨텐트의 길이 설정
			response.setHeader("Content-Length", "" + file.length());
			//다운로드 준비과정
			os = response.getOutputStream();
			//해당 파일의 길이만큼 byte배열을 선언합니다.
			byte b[] = new byte[(int)file.length()];
			
			int len = 0;
			//파일의 내용을 반복하여 읽어 온 후 더 이상 읽어올 내용이 없을 때까지 반복합니다.
			while((len = in.read(b)) > 0) {
				//읽어온 내용 출력
				os.write(b, 0, len);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {in.close();}
				if(os != null) {os.close();}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return null;
	}
}
