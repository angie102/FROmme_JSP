package com.fromme.app.map;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fromme.action.Action;
import com.fromme.action.ActionForward;
import com.fromme.app.studio.dao.StudioDAO;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.app.user.dao.UserDAO;

public class FindGongBangListOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StudioDAO s_dao = new StudioDAO();
		UserDAO u_dao = new UserDAO();
		PrintWriter out = response.getWriter();
		JSONObject users_address = new JSONObject();
		
		String session_id = (String) request.getSession().getAttribute("session_id");
		//사용자가 요청한 페이지 번호
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = 8;
		int endRow = page * pageSize;
		int startRow = endRow - (pageSize-1);
		
		System.out.println("session_id : " + session_id);
		if(session_id == null) {
			users_address.put("name", "출발지를 입력해주세요.");
			users_address.put("address", "");
		}else {
			String address = u_dao.getUserAddress(session_id);
			users_address.put("name", "회원님의 주소");
			users_address.put("address",  address);
		}
		String word = request.getParameter("search");
		List<StudioVO> gongbangLists = s_dao.getFindGongBangList(startRow, endRow, word);
		int totalCount = s_dao.getGongbangListCountBySearchWord(word);
		int totalPage = (totalCount - 1)/ pageSize + 1;
		JSONObject pages = new JSONObject();
		pages.put("nowPage", page);
		pages.put("totalPage", totalPage);
		
		JSONArray arr = new JSONArray();
		
        for(int i=0;i<gongbangLists.size();i++) {
        	JSONObject gongbangInfo = new JSONObject();
        	gongbangInfo.put("name", gongbangLists.get(i).getStudio_name());
        	gongbangInfo.put("address", gongbangLists.get(i).getStudio_address());
        	arr.add(gongbangInfo);
        }
        JSONObject list = new JSONObject();
        list.put("page", pages);
        list.put("user", users_address);
        list.put("gongbang", arr);
        
        //테스트
        //System.out.println(list.toJSONString());
        out.println(list);
        return null;
	}
}
