package com.fromme.app.studio;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableInterceptor.INACTIVE;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class StudioAddClassOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("studioAddClassOkAction실행");
		ActionForward forward = new ActionForward();
		ClassesDAO c_dao = new ClassesDAO();
		ClassesVO c_vo = new ClassesVO();
		PrintWriter out = response.getWriter();
		
		StudioDAO s_dao = new StudioDAO();
		StudioVO s_vo = new StudioVO();
		//이미지 파일 저장 경로
		String uploadPath = request.getSession().getServletContext().getRealPath("/")+"app/upload/iamges";	//리눅스
		//String uploadPath = "C:\\Users\\james\\OneDrive\\바탕 화면\\국비\\workspace\\fromme_project\\WebContent\\app\\upload\\images";	//로컬
		int fileSize = 5 * 1024 * 1024;
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, uploadPath, fileSize,"UTF-8", new DefaultFileRenamePolicy());
		System.out.println("StudioAddClass 데이터");
		int class_price = -1;
		//입력한 데이터
		String class_name = multi.getParameter("class_name");
		System.out.println("class_name : "+class_name);
		String class_teacher = multi.getParameter("class_teacher");
		System.out.println("class_teacher : "+class_teacher);
		String class_price_str = multi.getParameter("class_price");
		String class_start_date = multi.getParameter("class_start_date");
		System.out.println("class_start_date : "+class_start_date);
		String class_finish_date = multi.getParameter("class_finish_date");
		System.out.println("class_finish_date : "+class_finish_date);
		String class_date = multi.getParameter("class_date");
		System.out.println("class_date : "+class_date);
		String class_content = multi.getParameter("class_content");
		System.out.println("class_content : "+class_content);
		String class_limit = multi.getParameter("class_limit");
		class_price = Integer.parseInt(class_price_str);
		System.out.println("class_price : "+class_price);
		int classes_limit = Integer.parseInt(class_limit);
		
		String genre = multi.getParameter("genre");
		System.out.println("장르값"+genre);
		int genre_num = Integer.parseInt(genre);
		System.out.println("장르넘버"+genre_num);
		//세션 아이디
		String session_id = request.getParameter("session_id");
		System.out.println("session 아이디 : "+session_id);
		
		//해당 공방 번호 가져오기
		int studio_num = s_dao.getStudioNum(session_id);
		
		String fileName = "alm.png";
		//파일 이름가져오기
		fileName = multi.getFilesystemName("represent_image");
//		System.out.println("fileName : "+fileName);
		

		c_vo.setImage_path(fileName);
		
	
		// 클래스 타입으로 받아온 데이터 생성
		c_vo.setClasses_name(class_name);
		c_vo.setClasses_teacher(class_teacher);
		c_vo.setClasses_price(class_price);
		c_vo.setClasses_start(class_start_date);
		c_vo.setClasses_end(class_finish_date);
		c_vo.setClasses_registerdate(class_date);
		c_vo.setClasses_content(class_content);
		c_vo.setStudio_no(studio_num);
		c_vo.setGenre_no(genre_num);
		c_vo.setClasses_state(1);
		c_vo.setClasses_limit(classes_limit);
//		if(fileName == "") {
//			c_vo.setImage_path("alm.png");
//		}else {
//			c_vo.setImage_path(fileName);
//		}
		//파일 패스 등록
		//파일 인포 등록
		ClassesInfoVO ci_vo = new ClassesInfoVO();
		ci_vo.setClasses_date(class_date);
		ci_vo.setClasses_limit(classes_limit);
		ci_vo.setClasses_apply_num(0);
		
		//빈칸있을때
		if(class_name==null || class_teacher==null || class_price < 0 || class_start_date==null || class_finish_date ==null || class_content ==null) {
			System.out.println("빈칸있음");
			out.println("<script>");
			out.println("alert('빈칸없이 입력해주세요.');");
			out.println("</script>");
		}
		//새로운 클래스 만들기
		if(s_dao.createNewClass(c_vo)) {
			System.out.println("클래스 생성완료");
			//클래스 인포생성 
			// 생성후에 클래스 넘버 추출
			int class_no = s_dao.getClassNo(class_name);
			//확인용
			System.out.println(class_no);
			ci_vo.setClasses_no(class_no);
			if(s_dao.createClassInfo(ci_vo)) {
				System.out.println("클래스인포 생성완료");
				//클래스 이미지 생성
				if(s_dao.createClassImage(class_no, fileName)) {
					System.out.println("클래스 이미지 생성완료");
					forward.setRedirect(false);
					forward.setPath("/studio/StudioViewAction.std");
					return forward;
				}
			}
			
		}
		out.println("<script>");
		out.println("alert('게시글 등록 실패. 다시 시도해 주세요.');");
		out.println("</script>");
		out.close();
	
		return null;
	}

}
