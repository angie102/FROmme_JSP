package com.fromme.app.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.admin.dao.AdminDAO;
import com.fromme.app.admin.vo.AdminVO;
import com.fromme.app.admin.vo.ChartVO;


public class AdminIndexAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		AdminDAO a_dao = new AdminDAO();
		AdminVO a_vo = new AdminVO();
		
		a_vo.setIndexView(a_dao.getIndexView());
		a_vo.setIndexTotalReserve(a_dao.getIndexTotalReserve());
		a_vo.setIndexReserve(a_dao.getIndexReserve());
		a_vo.setClassList(a_vo.getClassList());
		a_vo.setTax(a_vo.getIndexReserve() * 0.1);
		
		List<ChartVO> todayChart = a_dao.getClassChartData("hh24", a_dao.getToday());
		List<ChartVO> weekChart = a_dao.getClassChartData("DD", a_dao.getToday());
		
//		System.out.println(weekChart.get(0).getCriteriaDate());
//		
		request.setAttribute("today", todayChart);
		request.setAttribute("week", weekChart);
		request.setAttribute("views", a_vo.getIndexView());
		request.setAttribute("classList", a_vo.getClassList());
		request.setAttribute("tax", a_vo.getTax());
		request.setAttribute("reserve", a_vo.getIndexReserve());
		request.setAttribute("totalReserve", a_vo.getIndexTotalReserve());
		

		forward.setRedirect(false);
		forward.setPath("/app/admin/index.jsp");
		return forward;
	}
}
