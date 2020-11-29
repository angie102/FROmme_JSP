package com.fromme.app.classes;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.likeit.LikeitVO;


public class ClassesLikeIncreaseOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ClassesDAO c_dao = new ClassesDAO();
		LikeitVO l_vo = new LikeitVO();
		
		HttpSession session = request.getSession();
		String users_id = (String) session.getAttribute("session_id");
		int classes_like = Integer.parseInt(request.getParameter("like").trim());
		int classes_no = Integer.parseInt(request.getParameter("seq").trim());
		String return_ajax="";
		PrintWriter out = response.getWriter();

		l_vo.setClasses_no(classes_no);
		l_vo.setUsers_id(users_id);
		
		if(users_id == null) {
			return_ajax = "access denied";
		}else {
			//해당 아이디로 선택 클래스의 좋아요 누른 이력이 있는지 확인	
			if(c_dao.checkLike(l_vo)== 1) {
				//이력이 존재
				c_dao.updateLike(l_vo);
			}else {
				//새로 좋아요를 등록해야하는 상태
				c_dao.insertLike(l_vo);
			}
			
			// 쿼리 실행 결과에 따라 클래스에 표시할 숫자 증감값 설정
			int result = c_dao.selectResultOfLike(l_vo);

			if(result ==1) {
				result =1;
				return_ajax = "increase";
			}
			else {
				result = -1;
				return_ajax = "decrease";
			}
			
			//클래스 테이블 좋아요값 바꾸기
			c_dao.increaseLike(classes_no, classes_like+result);	
			
		}
		//결과값 보내기
		out.println(return_ajax);
		out.close();
		
		return null;	
		}
}
