package com.fromme.app.studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.board.dao.BoardDAO;
import com.fromme.app.board.vo.PostVO;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class StudioEditOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("studioEditOKACtion 실행");
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		StudioDAO s_dao = new StudioDAO();
		ClassesVO c_vo = new ClassesVO();
		
		//이미지 파일 저장 경로
		String uploadPath = request.getSession().getServletContext().getRealPath("/")+"app/upload/iamges"; // 리눅스
		//String uploadPath = "C:\\Users\\james\\OneDrive\\바탕 화면\\국비\\workspace\\fromme_project\\WebContent\\app\\upload\\images"; // 로컬
		int fileSize = 5 * 1024 * 1024;
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, uploadPath, fileSize,"UTF-8", new DefaultFileRenamePolicy());
		
		
		
		
		//클래스 번호 가져오기
		int class_no = Integer.parseInt(request.getParameter("class_no"));
		System.out.println(class_no);
		
		// form태그 데이터 값 불러오기
		String class_name = multi.getParameter("class_name");
		System.out.println("class_name"+class_name);
		String class_teacher = multi.getParameter("class_teacher");
		int class_price = Integer.parseInt(multi.getParameter("class_price"));
		String class_start =multi.getParameter("class_start_date");
		String class_finish =multi.getParameter("class_finish_date");
		String class_date =multi.getParameter("class_date");
		String class_content =multi.getParameter("class_content");
		int class_limit = Integer.parseInt(multi.getParameter("class_limit"));
		System.out.println(class_limit);
		System.out.println("총원"+class_limit);
		
		//파일 이름가져오기
		String fileName = multi.getFilesystemName("represent_image");
		System.out.println("fileName : "+fileName);

		// 장르가져오기
		String genre = multi.getParameter("genre");
		System.out.println("장르값"+genre);
		int genre_num = Integer.parseInt(genre);
		System.out.println("장르넘버"+genre_num);
		
		//ClassVO에 내용 넣어주기
		c_vo.setClasses_name(class_name);
		c_vo.setClasses_teacher(class_teacher);
		c_vo.setClasses_price(class_price);
		c_vo.setClasses_start(class_start);
		c_vo.setClasses_end(class_finish);
		c_vo.setClasses_day(class_date);
		c_vo.setClasses_content(class_content);
		c_vo.setClasses_no(class_no);
		c_vo.setClasses_limit(class_limit);
		c_vo.setGenre_no(genre_num);
		if(fileName == "") {
			c_vo.setImage_path("alm.png");
		}else {
			c_vo.setImage_path(fileName);
		}
		//파일 패스 업데이트
		s_dao.updateFilePath(c_vo);
		//세션 아이디
		String session_id = (String) request.getSession().getAttribute("session_id");
		System.out.println("session 아이디 : "+session_id);
		//공방 정보
		StudioVO s_vo = new StudioVO();
		//스튜디오 번호
		int studio_num = s_dao.getStudioNum(session_id);
		// 공방정보로 클래스 정보 가져오기
		List<ClassesVO> c_list = new ArrayList<ClassesVO>();
		ClassesDAO c_dao = new ClassesDAO();
		//공방번호로 가져온 공방번호
		s_vo = s_dao.getStudioDetail(studio_num);
		System.out.println(s_vo);
		
		//반복문 돌릴 클래스 개수
		int classCnt = s_dao.getClassCount(studio_num);
		
		//스튜디오 장르 가져오기
		String genre_name =s_dao.getGenreName(s_vo.getGenre_no());
		
		//해당 공방번호에 해당하는 클래스를 가져온다. 
		c_list = s_dao.getClassContents(studio_num);
		System.out.println(c_list);		
		//스튜디오정보
		request.setAttribute("s_vo", s_vo);
		//클래스 정보
		request.setAttribute("c_list", c_list);
		//반복문 돌릴개수
		request.setAttribute("classCnt", classCnt);
		//장르 명
		request.setAttribute("genre_name", genre_name);
		
		
		//DB업데이트    참일때만 성공메세지
		if(s_dao.updateClassDetail(c_vo)) {
			//limit 업데이트
			s_dao.updatelimit(c_vo);
			System.out.println("데이터 업데이트 성공");	
			//DB에 등록후 다시 스튜디오 페이지로 돌아온다.
			forward.setRedirect(false);
			forward.setPath("/studio/StudioViewAction.std");
			return forward;
		}
		System.out.println("데이터 업데이트 실패");
		
		
		return null;
	}

}
