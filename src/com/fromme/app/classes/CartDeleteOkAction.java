package com.fromme.app.classes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.CartVO;

public class CartDeleteOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ClassesDAO c_dao = new ClassesDAO();
		int cart_no = Integer.parseInt(request.getParameter("cart_no"));
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("session_id");
		
		if(user_id != null) {		
			c_dao.deleteCartItem(cart_no);			
		}
		else {
			int cartForRemove = 0;
			List<CartVO> cartList = (List<CartVO>)session.getAttribute("cartList");
			for (CartVO cartVO : cartList) {
				if(cartVO.getCart_no() == cart_no) {
					cartForRemove = cartList.indexOf(cartVO);
					break;
				}
			}
			cartList.remove(cartForRemove);
		}
		return null;
	}
}
