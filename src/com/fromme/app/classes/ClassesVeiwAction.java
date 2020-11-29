package com.fromme.app.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;
import com.fromme.app.likeit.LikeitVO;

public class ClassesVeiwAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		String gen =request.getParameter("gen");
		int classes_no = Integer.parseInt(request.getParameter("seq"));
		
		ClassesVO c_vo = new ClassesVO();
		ClassesDAO c_dao = new ClassesDAO();
		
		c_vo = c_dao.getDetail(classes_no);
		List<ClassesInfoVO> classesInfoList = c_dao.getClassesInfo(classes_no);
		
		// 페이지에서 날짜 형식 변환해서 출력하기 위한 코드
		// 기간 안의 년도가 같을 경우 뒤에 오는 년도는 생략. ( 예: 2020. 00. 00 (요일) ~ 00. 00 (요일) )
		String start_date = c_dao.chageDateFormat(c_vo.getClasses_start()) ;
		String end_date =c_dao.chageDateFormat(c_vo.getClasses_end()) ;
		String result_date="";
		String year = start_date.substring(0, 4);
		
		if( end_date.startsWith(year) ) result_date = start_date +" ~ " + end_date.substring(5);
		else result_date = start_date +" ~ "+ end_date;
		
		// 선택가능 날짜를 담아줄 배열
		List<String> classes_dateList = new ArrayList<>();

		for (ClassesInfoVO classesInfoVO : classesInfoList) {
			// 예약가능 날짜만 가져오기
			if(classesInfoVO.getClasses_individual_state() == 1) {				
				classes_dateList.add(classesInfoVO.getClasses_date().substring(0,11));
			}

		}

		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("session_id");
		int liked =0;
		if(user_id !=null) {			
			LikeitVO l_vo = new LikeitVO();
			l_vo.setClasses_no(classes_no);
			l_vo.setUsers_id(user_id);
			liked = c_dao.selectResultOfLike(l_vo);
		}

		
		request.setAttribute("liked", liked);
		request.setAttribute("classes_dateList", classes_dateList);
		request.setAttribute("classesInfoList", classesInfoList);
		request.setAttribute("classes_bean", c_vo);
		request.setAttribute("gen", gen);
		
		request.setAttribute("result_date", result_date);
		// datepicker 사용을 위해 넘기는 값
		request.setAttribute("start_date", start_date);
		request.setAttribute("end_date",  end_date);
		
		forward.setRedirect(false);
		forward.setPath("/app/classes/classesDetail.jsp");
		return forward;
	}
}
