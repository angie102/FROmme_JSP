package com.fromme.app.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.classes.dao.ClassesDAO;
import com.fromme.app.classes.vo.CartListVO;
import com.fromme.app.classes.vo.CartVO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;
import com.fromme.app.order.OrderVO;
import com.fromme.app.order.OrdererInfoVO;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class PaymentOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		ClassesDAO c_dao = new ClassesDAO();
		OrdererInfoVO oi_vo = new OrdererInfoVO();
		ClassesInfoVO ci_vo = null;
		ClassesVO c_vo = new ClassesVO();
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		List<CartVO> cartListForOrder =null;
		OrderVO o_vo= null;
		boolean check = true;
		
		HttpSession session = request.getSession();
		String users_id = (String)session.getAttribute("session_id");

		boolean isDirect = (request.getParameter("isDirect")).trim().equals("true");
		int total_price = Integer.parseInt(request.getParameter("total_price"));
		String orderer_name = request.getParameter("orderer-name");  
		String orderer_email = request.getParameter("orderer-email");  
		String orderer_phone = request.getParameter("orderer-phone"); 
		String cash_idx = request.getParameter("cash_idx");
		String pay_type = request.getParameter("pay_type"); // cash
		String depositor_name = request.getParameter("depositor_name");//주문자
		List<CartListVO> cartList =null;
		
		// 입금자명이 공란인 경우 주문자명으로 채우기
		if(depositor_name == null) {
			depositor_name = orderer_name;
		}
		

		
		//주문자 정보 저장
		oi_vo.setOrderer_name(orderer_name);
		oi_vo.setOrderer_email(orderer_email);
		oi_vo.setOrderer_phone(orderer_phone);
		oi_vo.setTotal_price(total_price);
		c_dao.insertOrderInfo(oi_vo);
		
		//주문정보 검사해서 저장할 리스트로 옮기기
		int orderer_info_no =c_dao.getLastOrderInfoNo();
		int order_state_no = 0;
		int reservation_no = 0;
		int after_apply_num =0;
		String path = "";
		

		// 바로 결제해서 장바구니가 없는 경우
		if(isDirect) {
			cartList = (List<CartListVO> )request.getAttribute("cartList");
			o_vo = new OrderVO();
			System.out.println(cartList);
			o_vo.setChoice_date(cartList.get(0).getChoice_date());
			o_vo.setClasses_info_no(cartList.get(0).getClasses_info_no());
			o_vo.setClasses_no(cartList.get(0).getClasses_no());
			o_vo.setQuantity(cartList.get(0).getCart_quantity());
			o_vo.setUsers_id(users_id);
			o_vo.setStudio_no(c_dao.getStudioNo(cartList.get(0).getClasses_no()));
			orderList.add(o_vo);
		}else {
			//회원인 경우
			if(users_id != null) {
				//장바구니 데이터와 클래스정보 조인해서 저장할 리스트에 추가
				orderList = c_dao.getCartForOrder(users_id);


			//비회원인 경우 세션에서 가져와서 저장할 리스트에 저장
			}else {
					cartListForOrder = (List<CartVO>)session.getAttribute("cartList");
					for (CartVO ct_vo : cartListForOrder) {					
						o_vo = new OrderVO();
						o_vo.setChoice_date(ct_vo.getChoice_date());
						o_vo.setClasses_info_no(ct_vo.getClasses_info_no());
						o_vo.setClasses_no(ct_vo.getClasses_no());
						o_vo.setQuantity(ct_vo.getCart_quantity());
						o_vo.setUsers_id("temp");
						o_vo.setStudio_no(c_dao.getStudioNo(ct_vo.getClasses_no()));
						orderList.add(o_vo);
					}
			}

		}


		
		// 현재 신청가능한 상태인지 주문내역 하나씩 검사
		for (OrderVO order : orderList) {
			//예약가능한 상태인지 확인하기 위해 클래스 상세정보를 가져온다
			ci_vo = c_dao.getClassesInfoByClassesInfoNo(order.getClasses_info_no());
			// 현재 사용자가 신청 후 예상 신청인원 저장
			after_apply_num = ci_vo.getClasses_apply_num() + order.getQuantity();
			
			if(after_apply_num > ci_vo.getClasses_limit()) {
				//신청불가 상태 체크해서 하나라도 불가하면 탈출	
				check = false;
				break;
			}			
		}
		
		
		// 장바구니의 모든 클래스가 신청가능한 상태인 경우
		if(check) {
			//저장한 주문정보 추가
			for (OrderVO order : orderList) {
				ci_vo = c_dao.getClassesInfoByClassesInfoNo(order.getClasses_info_no());
				
				// 주문정보 저장
				order.setChoice_date(ci_vo.getClasses_date().substring(0, 11).trim());
				order.setOrderer_info_no(orderer_info_no);
				/** 
				 * 예약 미구현 
				 */
				order.setReservation_no(reservation_no);
				
				//사용자가 선택한 결제수단 체크
				if( pay_type.equals("cash") ) {
					order_state_no = 0; //결제대기
				}else if(pay_type.equals("toss")){
					order_state_no = 2;
				}
				order.setOrderlist_state_no(order_state_no);//결제 타입 저장
				
				//클래스 정보에 신청인원 갱신
				ci_vo.setClasses_apply_num(after_apply_num);				
				// 사용자가 신청함으로서 신청인원이 정원에 도달할 경우 개별 클래스 상태 변경
				if( after_apply_num == ci_vo.getClasses_limit()) {
					ci_vo.setClasses_individual_state(3);
					
				}
				c_dao.updateClassesApplyNumAndState(ci_vo);
				
				// 업데이트 후 상태 가져오기
				int classes_available_cnt = c_dao.getIndividualState(order.getClasses_no(), 1);
				System.out.println("클래스 상태 확인"+classes_available_cnt);
				// 모든 클래스 날짜 인원이 찬 경우 클래스 상태 업데이트
				if(classes_available_cnt == 0) {
					c_vo.setClasses_no(ci_vo.getClasses_no());
					c_vo.setClasses_state(3);
					c_dao.updateClassesChangeState(c_vo); 			
				}
				
				// 주문내역에 추가하고 주문한 물건 장바구니에서 지우기
				c_dao.insertOrderlist(order);
				if(users_id != null) {
					c_dao.deleteCartAfterPurchase(order);					
				}else {
					session.removeAttribute("cartList");
					session.removeAttribute("cartListForView");
				}
			}
		}	

			
		if(check) {		
			// 문자 보낼 내용작성
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, +7);

			String msg = "[FROmme]\n상품금액 : " + total_price + "원";

			// 결제 대기 상태
			if(order_state_no ==0) {
				msg += "\n계좌번호 : "+ cash_idx 
						+ "\n(~" + (cal.get(cal.MONDAY)+1)+"월 "+cal.get(cal.DAY_OF_MONTH)+"일" +"까지)";
				
			//결제 완료상태
			}else if(order_state_no ==2) {
				msg += "\n주문번호 : " + orderer_info_no 
						+ "\n클래스 상세 정보는 마이페이지에서 확인해주세요.";
			}

			//coolsms API
			String api_key = "NCSNZGVRPJKCUP5G";
			String api_secret = "SRATVFIGWWZLQR4VSR1EEDEKNAOKS15W";
			Message coolsms = new Message(api_key, api_secret);

			// 4 params(to, from, type, text) are mandatory. must be filled
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("to", orderer_phone); // 수신번호
			params.put("from", "01024285455"); // 발신번호
			params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
			params.put("text", msg); // 문자내용    
			params.put("app_version", "JAVA SDK v1.2"); // application name and version

			// Optional parameters for your own needs
			// params.put("image", "desert.jpg"); // image for MMS. type must be set as "MMS"
			// params.put("image_encoding", "binary"); // image encoding binary(default), base64 
			// params.put("mode", "test"); // 'test' 모드. 실제로 발송되지 않으며 전송내역에 60 오류코드로 뜹니다. 차감된 캐쉬는 다음날 새벽에 충전 됩니다.
			// params.put("delay", "10"); // 0~20사이의 값으로 전송지연 시간을 줄 수 있습니다.
			// params.put("force_sms", "true"); // 푸시 및 알림톡 이용시에도 강제로 SMS로 발송되도록 할 수 있습니다.
			// params.put("refname", ""); // Reference name
			// params.put("country", "KR"); // Korea(KR) Japan(JP) America(USA) China(CN) Default is Korea
			// params.put("sender_key", "5554025sa8e61072frrrd5d4cc2rrrr65e15bb64"); // 알림톡 사용을 위해 필요합니다. 신청방법 : http://www.coolsms.co.kr/AboutAlimTalk
			// params.put("template_code", "C004"); // 알림톡 template code 입니다. 자세한 설명은 http://www.coolsms.co.kr/AboutAlimTalk을 참조해주세요. 
			// params.put("datetime", "20140106153000"); // Format must be(YYYYMMDDHHMISS) 2014 01 06 15 30 00 (2014 Jan 06th 3pm 30 00)
			// params.put("mid", "mymsgid01"); // set message id. Server creates automatically if empty
			// params.put("gid", "mymsg_group_id01"); // set group id. Server creates automatically if empty
			// params.put("subject", "Message Title"); // set msg title for LMS and MMS
			// params.put("charset", "euckr"); // For Korean language, set euckr or utf-8
			// params.put("app_version", "Purplebook 4.1") // 어플리케이션 버전

			try {
				JSONObject obj = (JSONObject) coolsms.send(params);
				System.out.println(obj.toString());
			} catch (CoolsmsException e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCode());
			}
		
			
			// 주문이 성공한 경우 주문완료 페이지로
			path = "/classes/PaymentSuccess.cls";
		}else {
			path = "/app/classes/orderFailed.jsp";
		}
			
			
			
		request.setAttribute("total_price", total_price); 
		request.setAttribute("orderer-name", orderer_name); 
		request.setAttribute("orderer-email", orderer_email); 
		request.setAttribute("orderer_phone", orderer_phone); 
		request.setAttribute("cash_idx", cash_idx); 
		request.setAttribute("pay_type", pay_type); 
		request.setAttribute("depositor_name", depositor_name);
		request.setAttribute("orderList", orderList);

		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+path);
		return forward;
	}
}
