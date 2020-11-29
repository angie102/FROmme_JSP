package com.fromme.app.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.CartVO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;


public class CartInsertOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ClassesDAO c_dao = new ClassesDAO();
		CartVO ct_vo = new CartVO();
		
		
		int classes_no = Integer.parseInt(request.getParameter("seq")); // 선택 클래스
		String date = (String) request.getParameter("date"); //사용자가 선택한 날짜
		
		HttpSession session = request.getSession();
		String users_id = (String) session.getAttribute("session_id"); //세션아이디
		
		ClassesInfoVO ci_vo= new ClassesInfoVO();
		ci_vo.setClasses_no(classes_no);
		ci_vo.setClasses_date(date);
		ci_vo = c_dao.getClassesInfoByDateAndNo(ci_vo);
		
		
		//장바구니 모델에 데이터 저장
		ct_vo.setClasses_no(classes_no);
		ct_vo.setUsers_id(users_id);
		ct_vo.setChoice_date(c_dao.chageDateFormat(date).substring(0,11));
		ct_vo.setClasses_info_no(ci_vo.getClasses_info_no());
		
		//아이디에 저장된 장바구니 있는지 조회
		//로그인된 상태인 경우,
		if(users_id != null) {
			// 장바구니에 이미 데이터가 있는 경우,
			if(c_dao.searchCartExsit(ct_vo) >0) {
				c_dao.updateCart(ct_vo); //장바구니 수량변경
			
			//장바구니에 데이터가 없는 경우 장바구니에 추가
			}else {
				ct_vo.setCart_quantity(1);
				c_dao.insertItemToCart(ct_vo);
			}
		//비회원인 경우
		}else {
			ct_vo.setUsers_id("temp");
			ct_vo.setCart_date(new Date()+"");
			boolean check = false;
			List<CartVO> cartList = (List<CartVO>)session.getAttribute("cartList");
			int cartForRemove = 0;
			if(cartList != null && cartList.size() > 0) {				

				for (CartVO cartVO : cartList) {
					if(cartVO.getClasses_no() == ct_vo.getClasses_no() && cartVO.getChoice_date().equals(ct_vo.getChoice_date())) {
						ct_vo.setCart_quantity(cartVO.getCart_quantity()+1);
						cartForRemove = cartList.indexOf(cartVO);
						check = true;
					}
				}
				//장바구니에 없는 아이템 추가시
			}else {
				cartList = new ArrayList<CartVO>();
			}
			
			if(!check) {
				ct_vo.setCart_quantity(1);
			}else {
				cartList.remove(cartForRemove);				
			}

			cartList.add(ct_vo);
			session.setAttribute("cartList", cartList);
		}
		return null;
	}
}
