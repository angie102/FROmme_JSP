package com.fromme.app.classes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.ClassesListVO;

public class ClassesSortAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		ClassesDAO c_dao = new ClassesDAO();		
		List<ClassesListVO> calssesList =new ArrayList<>();
		String class_sortTitle="";
		
		String classes_sortName = request.getParameter("classes_sortName");
		String sort_type = request.getParameter("sort_type");
		String sort_by = request.getParameter("sort_by");
		String keyword = request.getParameter("keyword");


		String temp = request.getParameter("page");
		int page = temp == null ? 1 : Integer.parseInt(temp);
		int pageSize = 16;
		int endRow = page * pageSize;
		int startRow = endRow - (pageSize-1);
		int totalCount = c_dao.getClassesListCount();

		//정렬 기준에 따라 처리		
		if(keyword != null) {
		//검색인 경우
			calssesList = c_dao.listSearchByKeyword(startRow, endRow, keyword); 		
			totalCount = c_dao.CountSearchByKeyword(keyword);
			class_sortTitle = "검색: "+keyword;
		}else if(sort_type.equals("genre")) {			
		//장르별 정렬
			System.out.println(sort_by);
			int genre_no = c_dao.getGenreNoForSort(sort_by.trim());
			calssesList = c_dao.sortListByGenre(startRow, endRow, genre_no); 		
			class_sortTitle = sort_by;
			totalCount = c_dao.getListCountWithGenre(genre_no);
			
		//하위정렬
		}else if(sort_type.equals("subSort")) {
			String sort_order ="ASC";
			String sort_standard ="";

				switch(sort_by) {
				case "new": sort_standard="classes_no"; break;
				case "name": sort_standard="classes_name"; break;
				case "lowPrice": sort_standard="classes_price";  break;
				case "highPrice": sort_standard="classes_price"; sort_order ="DESC"; break;
				case "like": sort_standard="classes_like"; sort_order ="DESC"; break;
				}
				
			//전체 클래스에서 하위정렬
			if(classes_sortName.equals("전체 클래스")) {				
				calssesList = c_dao.sortList(startRow, endRow, sort_standard, sort_order);
				class_sortTitle = "전체 클래스";
			//검색범위에서 하위정렬
			}else if(classes_sortName.contains("검색")) {
				calssesList = c_dao.sortListSearchByKeyword(startRow, endRow, keyword, sort_standard, sort_order); 		
				totalCount = c_dao.CountSearchByKeyword(keyword);
				class_sortTitle = classes_sortName;
			}else {
			// 장르별 정렬 상태에서 다시 하위정렬
				int genre_no = c_dao.getGenreNoForSort(classes_sortName);
				calssesList = c_dao.sortListAfterGenreSort(startRow, endRow, genre_no, sort_standard, sort_order);
				totalCount = c_dao.getTotalSortGenre(genre_no);
				class_sortTitle = classes_sortName;
			}
			
		//전체정렬
		}else if(sort_type.equals("all")) {
			if(sort_by.equals("new")) {				
				calssesList= c_dao.getAllList(startRow, endRow);
			}else if(sort_by.equals("best")){
				calssesList= c_dao.sortBest(startRow, endRow);
			}else if(sort_by.equals("recommend")){
				//calssesList= c_dao.sortRecommend(startRow, endRow);
			}
			totalCount = c_dao.getClassesListCount();
			class_sortTitle = "전체 클래스";
		}
		
		
		if(class_sortTitle ==null) {				
			class_sortTitle = classes_sortName;
		}
		
		//페이지 처리
		int totalPage = (totalCount - 1)/ pageSize + 1;
		int pageBlock = 10;
		int startPage = ((page-1) / pageSize) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;

		endPage = (endPage > totalPage) ? totalPage : endPage;

		List<String> dateList = new ArrayList<>();
		for (ClassesListVO classes : calssesList) {
			dateList.add( c_dao.chageDateFormat( classes.getClasses_start() ) ); //변환된 날짜
		}

		//응답 페이지로 전달할 결과값들
		request.setAttribute("classesList", calssesList);
		request.setAttribute("dateList", dateList);

		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("nowPage", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("classes_sortName", class_sortTitle);
		
		request.setAttribute("sort_type", sort_type);
		request.setAttribute("sort_by", sort_by);

		forward.setRedirect(false);
		forward.setPath("/app/classes/classesList.jsp");
		return forward;

	}

}
