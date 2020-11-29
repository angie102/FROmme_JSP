package com.fromme.app.user.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fromme.app.studio.vo.StudioVO;
import com.fromme.app.user.vo.UserVO;
import com.fromme.mybatis.config.SqlMapConfig;


public class UserDAO {
	SqlSessionFactory session_f = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public UserDAO(){
		sqlsession = session_f.openSession(true);
	}
	
	// 아이디 중복검사
	public boolean checkId(String id) {
		if ((Integer)sqlsession.selectOne("User.checkId", id) == 1) {
			return true;
		};
		return false;
	}

	// 회원정보 수정 비밀번호 확인
	public String checkPw(String id) {
		return sqlsession.selectOne("User. checkPw", id);
	}
	
	// 로그인
	public String login(String id, String pw) {
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("users_id", id);
		dataMap.put("users_pw", pw);
		
		return (String)sqlsession.selectOne("User.login", dataMap);
	}
	
	// 회원가입
	public boolean join(UserVO user) {
		if (sqlsession.insert("User.join", user) == 1) {
			return true;
		}
		return false;
	}
	
	// 공방주 회원가입
	public boolean studioJoin(UserVO studio) {
		if (sqlsession.insert("User.studioJoin", studio) == 1) {
			return true;
		}
		return false;
	}
	
	// id찾기
	public String findId(String name, String phone) {
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("users_name", name);
		dataMap.put("users_phone", phone);

		return (String)sqlsession.selectOne("User.findId", dataMap);
	}
	// pw찾기
	public String findPw(String id, String email) {
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("users_id", id);
		dataMap.put("users_email", email);
		
		return (String)sqlsession.selectOne("User.findPw", dataMap);
	}
	//회원 주소 가져오기
	public String getUserAddress(String users_id) {
		return sqlsession.selectOne("User.getUserAddress", users_id);
	}
	//회원 권한 가져오기
	public int getUsersAuthority(String users_id) {
		return sqlsession.selectOne("User.getUsersAuthority", users_id);
	}
	/*//회원 정보 가져오기
	public UserVO getUserInfo(String users_id) {
		return sqlsession.selectOne("User.getUserInfo", users_id);
	}*/
	
	// 회원 정보 가져오기
	public UserVO getUserInfo(String users_id) {
		return sqlsession.selectOne("User.getUserInfo", users_id);
	}
	// 스튜디오 정보 가져오기
	public StudioVO getStudioInfo(int studio_no) {
		return sqlsession.selectOne("User.getStudioInfo", studio_no);
	}
	// 본인 확인
	public boolean checkIdPw(String id, String pw) {
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("users_id", id);
		dataMap.put("users_pw", pw);
		
		if ((Integer)sqlsession.selectOne("User.userCheckIdPw", dataMap) == 1) {
			return true;
		};
		return false;
	}
	// 스튜디오 가입
	public boolean studioDetailJoin(StudioVO studio) {
		if (sqlsession.insert("User.studioDetailJoin", studio) == 1) {
			return true;
		}
		return false;
	}
	// 유저테이블에 seq 넣기
	public void insertStudio_seq(String uesrs_name, int studio_seq) {
		HashMap<String, Object> dataMap = new HashMap<>();
		dataMap.put("users_id", uesrs_name);
		dataMap.put("studio_seq", studio_seq);
		
		sqlsession.insert("User.updateStudio_seq", dataMap);
	}
	// seq 가져오기
	public int selectStudio_seq(String studio_name) {
		return sqlsession.selectOne("User.selectStudio_seq", studio_name);
	}
	
	// genre 가져오기
	public int selectUserGenre(String users_id) {
		return sqlsession.selectOne("User.selectUserGenre", users_id);
	}
   
   // 스튜디오 이름 중복체크
   public boolean checkStudioName(String studio_name) {
		if ((Integer)sqlsession.selectOne("User.checkStudioName", studio_name)  == 1) {
			return true;
		};
		return false;
	}
   
   // 유저 정보 변경
   public boolean updateUser(UserVO user) {
	   if (sqlsession.update("User.updateUserInfo", user) == 1) {
		return true;
	   }
	  return false;
   }
   
   // 스튜디오 정보 변경
   public boolean updateStudio(StudioVO studio) {
	   if (sqlsession.update("User.updateStudioInfo", studio) == 1) {
		   return true;
	   }
	   return false;
   }
   
}


