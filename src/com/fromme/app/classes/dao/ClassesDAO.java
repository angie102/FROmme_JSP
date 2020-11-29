package com.fromme.app.classes.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fromme.app.classes.vo.CartListVO;
import com.fromme.app.classes.vo.CartVO;
import com.fromme.app.classes.vo.ClassesListVO;
import com.fromme.app.classes.vo.ClassesVO;
import com.fromme.app.classesInfo.vo.ClassesInfoVO;
import com.fromme.app.likeit.LikeitVO;
import com.fromme.app.order.OrderVO;
import com.fromme.app.order.OrdererInfoVO;
import com.fromme.mybatis.config.SqlMapConfig;

public class ClassesDAO {	
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public ClassesDAO() {
		try {
			sqlsession = sessionFactory.openSession(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 클래스 목록
	 */
	//장르 이름 리스트를 가져온다
	public List<String> getGenreList() {
		return sqlsession.selectList("Classes.getGenreList");
	}

	//모든 클래스 목록을 가져온다
	public List<ClassesListVO> getAllList(int startRow, int endRow){
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		return sqlsession.selectList("Classes.listAll",pageMap);
	}
	//전체 클래스 개수를 가져온다
	public int getClassesListCount() {
		return sqlsession.selectOne("Classes.listCount");
	}
	//현재 게시글 시퀀스 번호를 가져온다
	public int getCurrentBoardSeq() {
		return sqlsession.selectOne("Classes.getCurrentSeq");
	}
	// 전체 클래스 리스트의 장르 이름을 가져온다
	public String getGenreName(int genre_no) {
		return sqlsession.selectOne("Classes.getGenreName",genre_no);
	}
	//날짜 포맷 변환
	//fmt 함수 사용이 불가능해서 대체로 사용
	public String chageDateFormat(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d=new Date();
		try {
			d = sdf.parse(date);
			
		} catch (ParseException pe) {
			System.out.println("ClassesDAO.chageDateFormat()"+pe);
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd (E)");
		return sdf.format(d);
	}
	//클래스 인원정보 가져오기
	public List<ClassesInfoVO> getClassesInfo(int classes_no) {
		return sqlsession.selectList("Classes.getClassesInfo", classes_no);
	}
	
	
	/**
	 * 좋아요
	 */
	//좋아요 증가
	public void increaseLike(int classes_no, int classes_like) {
		Map<String, Integer> map = new HashMap<>();
		map.put("classes_no", classes_no);
		map.put("classes_like", classes_like);
		sqlsession.update("Classes.increaseLike",map);
	}
	//좋아요 눌렀던 이력있는지 확인
	public int checkLike(LikeitVO l_vo) {
		return sqlsession.selectOne("Classes.checkLike", l_vo);
	}
	//좋아요 업데이트
	public void updateLike(LikeitVO l_vo) {
		sqlsession.update("Classes.updateLike", l_vo);
	}
	//좋아요 새로 등록
	public void insertLike(LikeitVO l_vo) {
		sqlsession.insert("Classes.insertLike", l_vo);
	}
	//좋아요 결과 가져오기
	public int selectResultOfLike(LikeitVO l_vo) {
		return sqlsession.selectOne("Classes.resultLike", l_vo);
	}
	//좋아요 결과 가져오기
	public List<Integer> getLike(String users_id) {
		return sqlsession.selectList("Classes.getLike", users_id);
	}
	
	
	
	/**
	 * 정렬
	 */
	//장르 정렬을 위한 장르번호 호출
	public int getGenreNoForSort(String genre_name ){
		return sqlsession.selectOne("Classes.getGenre",genre_name);
	}
	//장르에 따른 클래스 목록 정렬
	public List<ClassesListVO> sortListByGenre(int startRow, int endRow, int genre_no){
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("genre_no", genre_no);
		return sqlsession.selectList("Classes.sortByGenre",pageMap);
	}
	//클래스 목록 하위 정렬
	public List<ClassesListVO> sortList(int startRow, int endRow, String sort_standard, String sort_order){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("sort_standard", sort_standard);
		map.put("ascOrDesc", sort_order);
		return sqlsession.selectList("Classes.sortList",map);
	}
	//장르 선택 후 클래스 목록 하위정렬
	public List<ClassesListVO> sortListAfterGenreSort(int startRow, int endRow, int genre_no, String sort_standard, String sort_order){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("genre_no", genre_no);
		map.put("sort_standard", sort_standard);
		map.put("ascOrDesc", sort_order);
		return sqlsession.selectList("Classes.sortListAfterGenreSort",map);
	}
	//장르별 클래스 개수를 가져온다 
	public int getTotalSortGenre(int genre_no) {
		return sqlsession.selectOne("Classes.getTotalSortGenre",genre_no);
	}
	
	//전체에서 좋아요 순 정렬
	public List<ClassesListVO> sortBest(int startRow, int endRow){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlsession.selectList("Classes.sortAllbyLike",map);
	}
	//클래스 키워드 검색
	public List<ClassesListVO> listSearchByKeyword(int startRow, int endRow, String word){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("word", word);
		return sqlsession.selectList("Classes.searchByKeyword",map);
	}
	//클래스 키워드 검색 개수
	public int CountSearchByKeyword(String word){
		return sqlsession.selectOne("Classes.CountSearchByKeyword", word);
	}
	//클래스 키워드 검색 후 정렬
	public List<ClassesListVO> sortListSearchByKeyword(int startRow, int endRow, String keyword, String sort_standard, String sort_order){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("sort_standard", sort_standard);
		map.put("ascOrDesc", sort_order);
		map.put("word", keyword);
		return sqlsession.selectList("Classes.sortAfterSearchByKeyword",map);
	}

	
	
	/**
	 * 클래스 상세정보
	 */
	//선택한 클래스의 번호를 받아서 상세정보를 가져온다 
	public ClassesVO getDetail(int classes_no) {
		return sqlsession.selectOne("Classes.getDetail",classes_no);
	}
	//선택한 클래스의 번호를 받아서 상세정보를 가져온다 
	public ClassesInfoVO getClassesAvailableNumBySelectedDate(ClassesInfoVO ci_vo) {
		return sqlsession.selectOne("Classes.getClassesAvailableNumBySelectedDate",ci_vo);
	}

	/**
	 * 장르별 클래스 목록
	 */
	//장르별 클래스 목록을 가져온다
	public List<ClassesVO> getListWithGenre(int startRow, int endRow,int genre_no){
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("genre_no", genre_no);
		
		List<ClassesVO> classesList = sqlsession.selectList("Classes.listGenre",pageMap);
		return classesList;
	}
	//사용자가 선택한 장르에 해당하는 클래스 목록을 불러온다
	public int getListCountWithGenre(int genre_no) {
		return sqlsession.selectOne("Classes.listCountWithGenre",genre_no);
	}

	/**
	 * 장바구니
	 */
	//장바구니 리스트 검색
	public List<CartListVO> getCartList(String user_id) {		
		return sqlsession.selectList("Cart.listCart", user_id);
	}
	
	//장바구니 추가
	public boolean insertItemToCart(CartVO ct_vo) {		
		if(sqlsession.insert("Cart.insertItem", ct_vo)==1) {			
			return true;
		}return false;
	}
	//장바구니 삭제
	public void deleteCartItem(int cart_no) {
		sqlsession.delete("Cart.deleteItem", cart_no);
	}
	//장바구니 수량변경
	public void chageQuantity(int cart_no, int cart_quantity) {
		Map<String, Integer> map = new HashMap<>();
		map.put("cart_no", cart_no);
		map.put("cart_quantity", cart_quantity);
		sqlsession.update("Cart.chageQuantity", map);
	}
	//해당 아이디에 장바구니 존재여부 검색
	public int searchCartExsit(CartVO ct_vo) {
		return sqlsession.selectOne("Cart.countCart", ct_vo);
	}
	//이미 존재하는 장바구니에 추가
	public void updateCart(CartVO ct_vo) {
		sqlsession.selectList("Cart.updateQuantity", ct_vo);
	}
	//클래스 인원정보 가져오기
	public ClassesInfoVO getClassesInfoByDateAndNo(ClassesInfoVO ci_vo) {
		return sqlsession.selectOne("Classes.getClassesInfoByDateAndNo", ci_vo);
	}
	//공방번호 가져오기
	public int getStudioNo(int classes_no) {
		return sqlsession.selectOne("Classes.getStudioNo", classes_no);
	}

	
	/**
	 * 결제
	 */
	//장바구니 정보를 가져온다
	public List<OrderVO> getCartForOrder(String users_id) {
		return sqlsession.selectList("Cart.getCart", users_id);
	}
	//클래스 인원정보 가져오기
	public ClassesInfoVO getClassesInfoByClassesInfoNo(int classes_info_no) {
		return sqlsession.selectOne("Classes.getClassesInfoByClassesInfoNo", classes_info_no);
	}
	//주문상태를 가져온다
	public String getOrderState(int state) {
		return sqlsession.selectOne("Cart.getOrderStateNo", state);
	}
	//주문내역 추가
	public int insertOrderlist(OrderVO o_vo) {
		return sqlsession.insert("Cart.insertOrderlist", o_vo);
	}
	//주문상태를 가져온다
	public int getLastOrderInfoNo() {
		return sqlsession.selectOne("Cart.getLastOrderInfoNo");
	}
	//주문자 상세정보 추가
	public int insertOrderInfo(OrdererInfoVO oi_vo) {
		return sqlsession.insert("Cart.insertOrderInfo", oi_vo);
	}
	//주문 후 장바구니 삭제 
	public void deleteCartAfterPurchase(OrderVO o_vo) {
		sqlsession.delete("Cart.deleteCartAll", o_vo);
	}
	//주문 후 클래스 클래스 상태 변경
	public void updateClassesChangeState(ClassesVO c_vo) {
		sqlsession.update("Classes.updateClassesStatus", c_vo);
	}
	//개별 주문상태를 가져온다
	public int getIndividualState(int classes_no, int classes_individual_state) {
		Map<String, Integer> map = new HashMap<>();
		map.put("classes_no", classes_no);
		map.put("classes_individual_state", classes_individual_state);
		return sqlsession.selectOne("Classes.getIndividualState", map);
	}
	//주문 후 개별 클래스 상태 변경
	public void updateClassesApplyNumAndState(ClassesInfoVO ci_vo) {
		sqlsession.update("Classes.updateClassesApplyNumAndState", ci_vo);
	}


	
	
	
	
}


