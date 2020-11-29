package com.fromme.app.classes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.ClassesListVO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;
import com.fromme.app.studio.dao.StudioDAO;


public class ClassesListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String users_id = (String) session.getAttribute("session_id");

		ActionForward forward = new ActionForward();
		String classes_sortName ="전체 클래스";
		
		String sort_type = request.getParameter("sort_type");
		String sort_by = request.getParameter("sort_by");
		
		ClassesDAO c_dao = new ClassesDAO();
		//사용자가 요청한 페이지 번호
		String temp = request.getParameter("page");
		int page = temp == null ? 1 : Integer.parseInt(temp);
		
		//한 페이지당 16개의 글이 보이도록 설정
		int pageSize = 16;
		//한 페이지에서 가장 마지막 글 번호
		int endRow = page * pageSize;
		//한 페이지에서 첫번째 글 번호
		int startRow = endRow - (pageSize-1);
		
		//클래스 리스트
		List<ClassesListVO> calssesList = c_dao.getAllList(startRow, endRow); 
		List<String> dateList = new ArrayList<>();
		
		//장르이름 가져오기
		List<String> genreNameList = c_dao.getGenreList();
		
		//사용자가 좋아요한 게시글 가져오기
		List<Integer> LikedClassesNoList = c_dao.getLike(users_id);

		// 클래스의 공방 이름 리스트
		for (ClassesListVO classes : calssesList) {
			dateList.add( c_dao.chageDateFormat( classes.getClasses_start() ) ); //변환된 날짜
		}
		


		//전체 게시글 개수
		int totalCount = c_dao.getClassesListCount();
		//전체 게시글의 개수를 페이지의 사이즈(10)을 나눈 값이 페이지의 개수, 여기서 나머지가 0보다 크다면 페이지가 하나 더 필요하기 때문에 페이지의 개수를 1 증가시킵니다.
		//int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
		int totalPage = (totalCount - 1)/ pageSize + 1;//이렇게 작성하면 if문을 작성하지 않아도 됩니다.
		//페이지는 5개씩 묶음으로 보여줍니다.
		int pageBlock = 10;
		//하나의 응답 페이지에서 보여질 페이지 수(1~5)까지 보여줍니다.
		int startPage = ((page-1) / pageSize) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		
		//실제 마지막 페이지 수와 연산으로 구한 마지막 페이지 수를 비교하여 일치하도록 해줍니다.
		endPage = (endPage > totalPage) ? totalPage : endPage;

		
		//응답 페이지로 전달할 결과값들
		request.setAttribute("dateList", dateList);
		request.setAttribute("classesList", calssesList);
		request.setAttribute("LikedClassesNoList", LikedClassesNoList);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("nowPage", page);
		request.setAttribute("totalPage", totalPage);
		
		request.setAttribute("classes_sortName", classes_sortName);
		session.setAttribute("genreNameList", genreNameList);
		
		request.setAttribute("sort_type", sort_type);
		request.setAttribute("sort_by", sort_by);
		
		//forward 방식으로 전송해야 request 객체에 담긴 정보를 응답 페이지에서 사용할 수 있습니다.
		forward.setRedirect(false);
		forward.setPath("/app/classes/classesList.jsp");
		return forward;
	}
}
