package com.fromme.app.studio.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;
import com.fromme.app.studio.vo.StudioVO;
import com.fromme.mybatis.config.SqlMapConfig;

import oracle.net.aso.s;

public class StudioDAO {
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public StudioDAO() {
		sqlsession = sessionFactory.openSession(true);
	}
	//전체 클래스 개수를 가져온다
	public String getStudioName(int studio_no) {
		return sqlsession.selectOne("Studio.getStudioName", studio_no);
	}
	//검색한 값과 일치하는 공방 리스트 가져오기
	public List<StudioVO> getFindGongBangList(int StartRow, int endRow, String word){
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("startRow", StartRow);
	      map.put("endRow", endRow);
	      map.put("word", word);
	      return sqlsession.selectList("Studio.getFindGongBangList", map);
	}
	//검색한 값과 일치하는 공방 리스트 개수
	   public int getGongbangListCountBySearchWord(String word) {
	      return sqlsession.selectOne("Studio.getGongbangListCountBySearchWord", word);
	}
	// 해당 공방 정보 가져오기
	public StudioVO getStudioDetail(int studio_num) {
		return sqlsession.selectOne("Studio.getStudioDetail", studio_num);
	}
	//공방 번호에 맞는 클래스 데이터 전체가져오기
	public List<ClassesVO> getClassContents(int studio_num){
		return sqlsession.selectList("Studio.getClassContents", studio_num);
	}
	//새로운 클래스 생성
	public boolean createNewClass(ClassesVO c_vo){
		System.out.println("클래스 생성");
		return (sqlsession.insert("Studio.createNewClass", c_vo) == 1) ? true : false;
	}
	//해당 공방이 갖고있는 클래스 개수 가져오기
	public int getClassCount(int studio_no) {
		return sqlsession.selectOne("Studio.getClassCount",studio_no);
	}
	//클래스 내용 수정
	public boolean updateClassDetail(ClassesVO c_vo) {
		System.out.println("클래스 내용 업데이트");
		return (sqlsession.update("Studio.updateClassDetail", c_vo)==1) ? true :false;
	}
	//시퀀스 아이디를 참조해서 스튜디오num 가져오기
	public int getStudioNum(String session_id) {
		return sqlsession.selectOne("Studio.getStudioNum",session_id);
	}
	// 장르 가져오기
	public String getGenreName(int genre_no) {
		return sqlsession.selectOne("Studio.getGenreName",genre_no);
	}
	// 클래스 정보 가져오기
	public ClassesVO getClassData(int classes_no) {
		return sqlsession.selectOne("Studio.getClassData", classes_no);
	}
	//클래스 삭제하기
	public boolean deleteClass(int classes_no) {
		return (sqlsession.delete("Studio.deleteClass", classes_no) == 1) ? true :false;
	}
	//클래스  인포 삭제하기
	public boolean deleteClassInfo(int classes_no) {
		return (sqlsession.delete("Studio.deleteClassInfo", classes_no) == 1) ? true :false;
	}
	//클래스 이미지 삭제하기
	public boolean deleteClassImage(int classes_no) {
		return (sqlsession.delete("Studio.deleteClassImage", classes_no) == 1) ? true :false;
	}
	//클래스 정원 가져오기
	public int getClassLimit(int classes_no) {
		return sqlsession.selectOne("Studio.getClassLimit", classes_no);
	}
	//클래스 현재원 가져오기
	public int getClassApplyNum(int classes_no) {
		return sqlsession.selectOne("Studio.getClassApplyNum", classes_no);
	}
	//생성된 공방 내용에 유저 공방정보 삽입
	public void insertStudioNum(int studio_no, String session_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("studio_no", studio_no);
		map.put("session_id", session_id);
		sqlsession.update("Studio.insertStudioNum", map);
	}
	//클래스 총인원 변경
	public void updatelimit(ClassesVO c_vo) {
		sqlsession.update("Studio.updatelimit", c_vo);
	}
	//클래스 파일경로 변경
	public void updateFilePath(ClassesVO c_vo) {
		sqlsession.update("Studio.updateFilePath",c_vo);
	}
	//클래스 인포 생성
	public boolean createClassInfo(ClassesInfoVO ci_vo) {
		return (sqlsession.insert("Studio.createClassInfo",ci_vo)==1)? true :false;
	}
	//클래스 이미지 생성
	public boolean createClassImage(int classes_no, String image_path) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classes_no", classes_no);
		map.put("image_path", image_path);
		return (sqlsession.insert("Studio.createClassImage",map)==1)? true:false;
	}
	//클래스 생성후 클래스 번호 가져오기
	public int getClassNo(String classes_name) {
		return sqlsession.selectOne("Studio.getClassNo",classes_name);
	}
	
}


