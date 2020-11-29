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
import com.fromme.app.classesInfo.vo.ClassesInfoVO;
import com.fromme.app.user.dao.UserDAO;
import com.fromme.app.user.vo.UserVO;


public class PaymentViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String users_id = (String)session.getAttribute("session_id");

		boolean isDirect = (request.getParameter("isDirect")).trim().equals("true");
		String date = (String) request.getParameter("date"); //사용자가 선택한 날짜
		UserDAO u_dao = new UserDAO();
		UserVO u_vo = new UserVO();
		ClassesDAO c_dao = new ClassesDAO();
		int total_price=0;

		List<CartListVO> cartList = null;
		
		if(users_id != null) {
			u_vo = u_dao.getUserInfo(users_id);			
		}
		
		//바로 결제페이지 연동
		if(isDirect) {
			cartList = new ArrayList<CartListVO>();
			int classes_no = Integer.parseInt(request.getParameter("classes_no"));
			int calsses_price = Integer.parseInt(request.getParameter("classes_price"));
			String choice_date = c_dao.chageDateFormat(date).substring(0,11);

			ClassesInfoVO ci_vo = new ClassesInfoVO();
			ci_vo.setClasses_date(choice_date);
			ci_vo.setClasses_no(classes_no);
			ci_vo = c_dao.getClassesInfoByDateAndNo(ci_vo);
			
			CartListVO cl_vo = new CartListVO();
			cl_vo.setUsers_id(users_id);
			cl_vo.setClasses_name(request.getParameter("classes_name"));
			cl_vo.setClasses_no(classes_no);
			cl_vo.setChoice_date(choice_date);
			cl_vo.setClasses_info_no(ci_vo.getClasses_info_no());
			cl_vo.setCart_quantity(1);
			cl_vo.setClasses_price(calsses_price);
			cl_vo.setCart_sum_price(calsses_price);
			cartList.add(cl_vo);
			
			total_price=calsses_price;

		//장바구니에서 결제 페이지로 이동
		}else {
			if(users_id != null) {	
				cartList = c_dao.getCartList(users_id);

			}else {
				cartList = (List<CartListVO>) session.getAttribute("cartListForView");	
			}
			
			for (CartListVO ct_vo : cartList) {
				total_price += ct_vo.getCart_sum_price();
			}
		}
		
	
		
		request.setAttribute("isDirect", isDirect);
		request.setAttribute("total_price", total_price);
		request.setAttribute("cartList", cartList);
		request.setAttribute("user_bean", u_vo);

		forward.setRedirect(false);
		forward.setPath("/app/classes/payment.jsp");
		return forward;
	}

}
