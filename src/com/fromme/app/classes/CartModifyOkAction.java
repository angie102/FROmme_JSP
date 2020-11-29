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
import com.fromme.app.classes.vo.CartVO;


public class CartModifyOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("session_id");

		ClassesDAO c_dao = new ClassesDAO();

		int cart_no = Integer.parseInt(request.getParameter("cart_no"));
		int cart_quantity = Integer.parseInt(request.getParameter("quantity"));

		if(user_id != null) {			
			c_dao.chageQuantity(cart_no, cart_quantity);
		}else {
			List<CartVO> cartList = (List<CartVO>)session.getAttribute("cartList");
			int cartForRemove = 0;
			CartVO c_vo =null;

			for (CartVO cartVO : cartList) {
				if(cartVO.getCart_no() == cart_no) {
					cartForRemove = cartList.indexOf(cartVO);
					c_vo = cartVO;
					c_vo.setCart_quantity(cart_quantity);
				}
			}
			
			cartList.remove(cartForRemove);				
			cartList.add(c_vo);
			session.setAttribute("cartList", cartList);
		}

	return null;	
}
}
