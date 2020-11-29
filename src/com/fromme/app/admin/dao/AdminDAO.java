package com.fromme.app.admin.dao;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fromme.app.admin.vo.ChartVO;
import com.fromme.app.admin.vo.IndexViewVO;
import com.fromme.app.admin.vo.UserListVO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.mybatis.config.SqlMapConfig;
import com.google.gson.JsonObject;

public class AdminDAO {
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public AdminDAO() {
		sqlsession = sessionFactory.openSession(true);
	}
	
	//메인 페이지 총 예약 수, 한달 간 수익 구하는 쿼리 
	public int getIndexReserve() {
		Map<String, String> datas = new HashMap<String, String>();
		String [] dateData = getToday().split("-");
		datas.put("startTime", dateData[0]+dateData[1]+"01");
		datas.put("endTime", dateData[0]+dateData[1]+getLastDay(getToday()));
		System.out.println(dateData[0]+dateData[1]+"01");
		return sqlsession.selectOne("Admin.getIndexReserve", datas);
	}
	
	public int getIndexTotalReserve() { 
		return sqlsession.selectOne("Admin.getIndexTotalReserve");
	}
	
	//차트 데이터 가져오기
	//일주일 데이터를 제외한 것은 해당 메서드를 사용
	public List<ChartVO> getClassChartData(String classify, String dateData) {
		//classify : hh24, DD(일주일), DD2(한달) ,MM (연도별) 
		//classify_dateFormat : YYYY-MM-DD
		String dateFormat = "YYYYMMDD";
		String [] arDate = dateData.split("-");
		String date = "";
		for (String str : arDate) { date += str; }
		Map<String, String> datas;
		List<ChartVO> list;
		switch (classify) {
		case "hh24":
			datas = new HashMap<>();
			datas.put("classify", classify);
			datas.put("row", "24");
			datas.put("date_format", dateFormat);
			datas.put("startTime", date);
			datas.put("endTime", date);
			list = sqlsession.selectList("Admin.getClassChartTodayData", datas);
			break;
		case "DD" :
			datas = new HashMap<>();
			datas.put("classify", classify);
			datas.put("classify_dateFormat", dateFormat);
			datas.put("startTime", arDate[0]+arDate[1]+isLtTen(Integer.parseInt(arDate[2])-7));
			datas.put("endTime", date);
			list = sqlsession.selectList("Admin.getClassChartWeekData", datas);
			break;
		case "DD2":
			//월 구하는 쿼리, 로우 갯수와 해당 월 마지막 일을 함수로 구해와야 함
			datas = new HashMap<>();
			int lastDay = getLastDay(getToday());
			datas.put("classify", classify.substring(0, 2));
			datas.put("row", lastDay+"");
			datas.put("date_format", dateFormat);
			datas.put("startTime", arDate[0]+arDate[1]+lastDay);
			datas.put("endTime", arDate[0]+arDate[1]+"01");
			list = sqlsession.selectList("Admin.getClassChartData", datas);
			break;
		case "MM" :
			datas = new HashMap<>();
			datas.put("classify", classify);
			datas.put("row", "12");
			datas.put("date_format", "YYYY-MM");
			datas.put("startTime", arDate[0]+"12");
			datas.put("endTime", arDate[0]+"01");
			list = sqlsession.selectList("Admin.getClassChartData", datas);
			
			break;
		default:
			datas = new HashMap<>();
			list = new ArrayList<>();
			break;
		}
		
		if(list.isEmpty()) {
			list.add(new ChartVO("x", 999));
		}
		return list;
	}
	
	//차트 데이터 가져오기
		//일주일 데이터를 제외한 것은 해당 메서드를 사용
		public List<ChartVO> getViewChartData(String classify, String dateData) {
			//classify : hh24, DD(일주일), DD2(한달) ,MM (연도별) 
			//classify_dateFormat : YYYY-MM-DD
			String dateFormat = "YYYYMMDD";
			String [] arDate = dateData.split("-");
			String date = "";
			for (String str : arDate) { date += str; }
			Map<String, String> datas;
			List<ChartVO> list;
			switch (classify) {
			case "hh24":
				datas = new HashMap<>();
				datas.put("classify", classify);
				datas.put("row", "24");
				datas.put("date_format", dateFormat);
				datas.put("startTime", date);
				datas.put("endTime", date);
				list = sqlsession.selectList("Admin.getViewChartTodayData", datas);
				
				break;
			case "DD" :
				datas = new HashMap<>();
				datas.put("classify", classify);
				datas.put("classify_dateFormat", dateFormat);
				datas.put("startTime", arDate[0]+arDate[1]+ isLtTen(Integer.parseInt(arDate[2])-7));
				datas.put("endTime", date);
				list = sqlsession.selectList("Admin.getViewChartWeekData", datas);
				break;
			case "DD2":
				//월 구하는 쿼리, 로우 갯수와 해당 월 마지막 일을 함수로 구해와야 함
				datas = new HashMap<>();
				int lastDay = getLastDay(getToday());
				datas.put("classify", classify.substring(0, 2));
				datas.put("row", lastDay+"");
				datas.put("date_format", dateFormat);
				datas.put("startTime", arDate[0]+arDate[1]+lastDay);
				datas.put("endTime", arDate[0]+arDate[1]+"01");
				list = sqlsession.selectList("Admin.getViewChartData", datas);
				
				break;
			case "MM" :
				datas = new HashMap<>();
				datas.put("classify", classify);
				datas.put("row", "12");
				datas.put("date_format", "YYYY-MM");
				datas.put("startTime", arDate[0]+"12");
				datas.put("endTime", arDate[0]+"01");
				list = sqlsession.selectList("Admin.getViewChartData", datas);
				
				break;
			default:
				datas = new HashMap<>();
				list = new ArrayList<>();
				break;
			}
			
			if(list.isEmpty()) {
				list.add(new ChartVO("x", 999));
			}
			return list;
		}
	
		//파라미터로 받은 변수가 10보다 작은 경우 앞에 0을 붙여 리턴해준다!
	public String isLtTen(int number) {
		String result = "";
		if(number < 10 && number > -1) {
			return result = "0"+number;
		} else {
			result = number+"";
		}
		return result;
	}
	
	//오늘 날짜를 구하는 메서드
	public String getToday() {
		String [] time = new String[3];
		Calendar cal = Calendar.getInstance();
		time[0] = cal.get(Calendar.YEAR)+"";
		time[1] = cal.get(Calendar.MONTH)+1+"";
		time[2] = cal.get(Calendar.DATE)+"";
		String result = time[0]+"-"+time[1]+"-"+time[2];
		return result;
	}
	
	//파라미터로 넘겨준 월의 마지막 날을 구하는 메서드
	public int getLastDay(String date) {
		String [] dateData = date.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateData[0]), Integer.parseInt(dateData[1]), Integer.parseInt(dateData[2]));
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)-1;
		return lastDay;
	}
	
	public JsonObject makeChartLabel(List<ChartVO> data) {
		JsonObject json = new JsonObject();
		for (int i = 0; i < data.size(); i++) {
			if(data.get(i).getCriteriaDate() == null) break;
			json.addProperty("data"+(i+1), data.get(i).getCriteriaDate());
		}
		return json;
	}
	
	public JsonObject makeChartData(List<ChartVO> data) {
		JsonObject json = new JsonObject();
		for (int i = 0; i < data.size(); i++) {
			System.out.print(data.get(i).getDateData());
			json.addProperty("data"+(i+1), data.get(i).getDateData());
		}
		return json;
	}
	
	//페이지 방문 시 조회 수 인서트 //수정필요함
	public void insertView(HttpServletRequest request) {
		String ip = getUserIp(request);
		if(ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) ip = this.getUserIpInLinux();
		System.out.println(ip);
		int check = this.getSameIp(ip);
		if(check > 0) {
			return;
		}
		sqlsession.insert("Admin.insertViews", ip);
	}
	
	//오늘 접속한 아이피 가져오기
	public int getSameIp(String ip) {
		return sqlsession.selectOne("Admin.getSameIp", ip);
	}
	
	//메인 페이지 조회수 데이터 가져오기 정수형 3칸 배열 
	public IndexViewVO getIndexView() {
		return sqlsession.selectOne("Admin.getIndexView");
	}
	
	//사용자 아이피 확인 
	public String getUserIp(HttpServletRequest request) {
        String ip = null;

        ip = request.getHeader("X-Forwarded-For");
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }
		
		return ip;
	}
	
	//리눅스 서버에서 사용자 ip 확인
	public String getUserIpInLinux() {
		try {
			String ip = null;

			boolean isLoopBack = true;
			Enumeration<NetworkInterface> en;  
			en = NetworkInterface.getNetworkInterfaces();


			while(en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();
				if (ni.isLoopback())
					continue;

				Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
				while(inetAddresses.hasMoreElements()) { 
					InetAddress ia = inetAddresses.nextElement();
					if (ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
						ip = ia.getHostAddress();
						System.out.println(ip);
						isLoopBack = false;
						break;
					}
				}
				if (!isLoopBack)
					break;
			}
			return ip;
		} catch (SocketException e) {
			e.printStackTrace();
			return "0:0:0:0:0:0:0:1";
		}
	}
	//2: 승인, 5: 기각
	public int setStudioAuthority(int authority, int studio_no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("authority", authority);
		map.put("studio_no", studio_no);
		return sqlsession.update("Admin.setStudioAuthority", map);
	}
	
	//승인목록 가져오는 쿼리
	public List<StudioVO> getStudioApplyList(int startRow, int endRow) {
		Map<String, Integer> applyMap = new HashMap<>();
		applyMap.put("startRow", startRow);
		applyMap.put("endRow", endRow);
		
		List<StudioVO> applyList = sqlsession.selectList("Admin.getStudioApplyList", applyMap);
		
		return applyList;
	}
	
	//승인 목록 갯수 가져오는 쿼리
	public int getStudioApplyCount() {
		return sqlsession.selectOne("Admin.getStudioApplyCount");
	}
	
	public int deleteList(String table, String column_name, String reqData) {
		Map<String, String> datas = new HashMap<>();
		datas.put("table", table);
		datas.put("column_name", column_name);
		datas.put("num", reqData);
		return sqlsession.delete("Admin.deleteList", table);
	}
	
	public int setPrivate(int post_no, int pub) {
		Map<String, Integer> datas = new HashMap<String, Integer>();
		datas.put("post_no", post_no);
		datas.put("post_pub", pub);
		return sqlsession.update("Admin.setPrivate", datas);
	}
	
	public int setUserState(int users_no, int users_authority) {
		Map<String, Integer> datas = new HashMap<String, Integer>();
		datas.put("users_no", users_no);
		datas.put("users_authority", users_authority);
		return sqlsession.update("Admin.setUserState", datas);
	}
	
	public int setClassState(int classes_no, int classes_state) {
		Map<String, Integer> datas = new HashMap<String, Integer>();
		datas.put("classes_no", classes_no);
		datas.put("classes_state", classes_state);
		return sqlsession.update("Admin.setClassState", datas);
	}
	
	public List<ClassesVO> getClassList(int startRow, int endRow) {
		List<ClassesVO> list = null;
		Map<String, Integer> datas = new HashMap<String, Integer>();
		datas.put("startRow", startRow);
		datas.put("endRow", endRow);
		list = sqlsession.selectList("Admin.getClassList", datas);
		return list;
	}
	
	public List<UserListVO> getUserList(int startRow, int endRow) {
		List<UserListVO> list = null;
		Map<String, Integer> datas = new HashMap<String, Integer>();
		datas.put("startRow", startRow);
		datas.put("endRow", endRow);
		list = sqlsession.selectList("Admin.getUserList", datas);
		return list;
	}
	
	public int setUserInfo(String id, String name, String email, String phone, String authority) {
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("user_id", id);
		datas.put("user_email", email);
		datas.put("user_name", name);
		datas.put("user_phone", phone);
		datas.put("user_authority", authority);
		return sqlsession.update("Admin.setUserInfo", datas);
	}
	
	public int resetPassword(String id) {
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("user_id", id);
		return sqlsession.update("Admin.resetPassword", datas);
	}
	
	public int getUserCnt() {
		return sqlsession.selectOne("Admin.getUserCnt");
	}
}


