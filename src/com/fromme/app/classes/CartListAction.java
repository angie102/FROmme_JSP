package com.fromme.app.classes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.CartListVO;
import com.fromme.app.classes.vo.CartVO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;

public class CartListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		ClassesDAO c_dao = new ClassesDAO();
		
		List<CartListVO> cartList = null;
		CartListVO cl_vo = null;
		int total_price=0;

		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("session_id");

		
		//회원 비회원 구분
		if(user_id !=null) {
			cartList = c_dao.getCartList(user_id);		
			for (CartListVO ct_vo : cartList) {
				total_price = ct_vo.getCart_sum_price();
			}
		}else {
			cartList = new ArrayList<CartListVO>();
			List<CartVO> cart_temp= (List<CartVO>) session.getAttribute("cartList");
			
			for (CartVO ct_vo : cart_temp) {
				cl_vo = new CartListVO();
				ClassesVO c_vo = c_dao.getDetail(ct_vo.getClasses_no());
				cl_vo.setUsers_id(ct_vo.getUsers_id());
				cl_vo.setCart_no(ct_vo.getCart_no());
				cl_vo.setClasses_no(ct_vo.getClasses_no());
				cl_vo.setCart_quantity(ct_vo.getCart_quantity());
				cl_vo.setChoice_date(ct_vo.getChoice_date());
				cl_vo.setClasses_name(c_vo.getClasses_name());
				cl_vo.setClasses_info_no(ct_vo.getClasses_info_no());
				cl_vo.setClasses_price(c_vo.getClasses_price());
				cl_vo.setCart_sum_price(ct_vo.getCart_quantity() * c_vo.getClasses_price());
				total_price += cl_vo.getCart_sum_price();
				
				cartList.add(cl_vo);
			}
			session.setAttribute("cartListForView", cartList);
		}
		request.setAttribute("cartList", cartList);
		request.setAttribute("totalPrice", total_price);
		
		forward.setRedirect(false);
		forward.setPath("/app/classes/cartList.jsp");
		return forward;
	}
}
