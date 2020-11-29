package com.fromme.app.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fromme.app.board.vo.PostImageVO;
import com.fromme.app.board.vo.PostVO;
import com.fromme.app.board.vo.PostViewVO;
import com.fromme.app.board.vo.ReplyVO;
import com.fromme.mybatis.config.SqlMapConfig;

public class BoardDAO {
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public BoardDAO() {
		sqlsession = sessionFactory.openSession(true);
	}
	//게시판에 작성된 모든 게시글을 가져오는 메소드
	public List<PostViewVO> getBoardListSorted(int startRow, int endRow, int category_no, String sort, int users_authority){
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("category_no", category_no);
		pageMap.put("sort", sort);
		pageMap.put("users_authority", users_authority);
		List<PostViewVO> boardList = sqlsession.selectList("Board.listAllSorted", pageMap);
		return boardList;
	}
	//검색된 키워드로 검색된 모든 게시글을 가져오는 메소드
	public List<PostViewVO> getBoardListSearchedAndSorted(int startRow, int endRow, int category_no, String field, String word, String sort, int users_authority){
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("category_no", category_no);
		pageMap.put("field", field);
		pageMap.put("word", word);
		pageMap.put("sort", sort);
		pageMap.put("users_authority", users_authority);
		return sqlsession.selectList("Board.getBoardListSearchedAndSorted", pageMap);
	}
	//전달된 카테고리 게시판에 작성된 게시글의 총 개수를 가져옵니다.
	public int getBoardListCount(int category_no, int users_authority) {
		Map<String, Integer> map = new HashMap<>();
		map.put("category_no", category_no);
		map.put("users_authority", users_authority);
		return sqlsession.selectOne("Board.boardListCount", map);
	}
	//전달된 검색키워드로 검색된 게시글의 개수를 가져옵니다.
	public int getBoardListCountBySearchWord(String field, String word, int category_no, int users_authority) {
		Map<String, Object> search = new HashMap<>();
		search.put("category_no", category_no);
		search.put("word", word);
		search.put("field", field);
		search.put("users_authority", users_authority);
		return sqlsession.selectOne("Board.boardListCountBy", search);
	}
	//특정 회원이 작성한 게시글 목록 가져오기
	public List<PostViewVO> getBoardListByUser(int startRow, int endRow, String users_id, String filter, int users_authority){
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("users_id", users_id);
		pageMap.put("users_authority", users_authority);
		return sqlsession.selectList("Board.getBoardListByUser" + filter, pageMap);
	}
	//특정 회원이 작성한 게시글 또는 작성한 댓글이 있는 게시글의 개수 가져오기
	public int getBoardListCountByUser(String users_id, String filter, int users_authority) {
		Map<String, Object> map = new HashMap<>();
		map.put("users_id", users_id);
		map.put("users_authority", users_authority);
		return sqlsession.selectOne("Board.getBoardListCountByUser" + filter, map);
	}
	//특정 회원이 작성한 총 댓글의 개수 가져오기
	public int getReplyCountByUser(String users_id) {
		return sqlsession.selectOne("Board.getReplyCountByUser", users_id);
	}
	//전달받은 카테고리 번호에 해당하는 카테고리 제목을 가져옵니다.
	public String getBoardCategoryName(int category_no) {
		return sqlsession.selectOne("Board.getBoardCategoryName", category_no);
	}
	//게시판 썸네일 리스트 전체 보기
	public List<PostImageVO> getBoardImageListSorted(int startRow, int endRow, int category_no, String sort, int users_authority) {
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("category_no", category_no);
		pageMap.put("sort", sort);
		pageMap.put("users_authority", users_authority);
		return sqlsession.selectList("Board.getBoardImageListSorted", pageMap);
	}
	//검색값에 대한 게시판 썸네일 리스트로 보기
	public List<PostImageVO> getBoardImageListSearchedAndSorted(int startRow, int endRow, int category_no, String field, String word, String sort, int users_authority) {
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("category_no", category_no);
		pageMap.put("field", field);
		pageMap.put("sort", sort);
		pageMap.put("word", word);
		pageMap.put("users_authority", users_authority);
		return sqlsession.selectList("Board.getBoardImageListSearchedAndSorted", pageMap);
	}
	//게시글 추가
	public boolean insertBoard(PostVO board) {
		return (sqlsession.insert("Board.insertBoard",board) == 1) ? true : false;
	}
	//현재 게시글 시퀀스 번호 가져오기
	public int getCurrentBoardSeq() {
		return sqlsession.selectOne("Board.getCurrentBoardSeq");
	}
	//현재 댓글 시퀀스 번호 가져오기
	public int getCurrentReplySeq() {
		return sqlsession.selectOne("Board.getCurrentReplySeq");
	}
	//선택한 댓글 정보 가져오기
	public ReplyVO getReplyDetail(int reply_no) {
		return sqlsession.selectOne("Board.getReplyDetail", reply_no);
	}
	//선택한 게시글의 정보 가져오기
	public PostVO getDetail(int post_no) {
		return sqlsession.selectOne("Board.getDetail", post_no);
	}
	//키워드로 검색, 정렬기준으로 정렬된 게시글 목록에서 다음 게시글 가져오기
	public PostVO getNextBoardSearchedAndSorted(Map<String, Object> post,String sort) {
		return sqlsession.selectOne("Board.getNextBoardSearchedAndSortedBy" + sort, post);
	}
	//키워드로 검색, 정렬기준으로 정렬된 게시글 목록에서 다음 게시글 가져오기
	public PostVO getPrevBoardSearchedAndSorted(Map<String, Object> post,String sort) {
		return sqlsession.selectOne("Board.getPrevBoardSearchedAndSortedBy" + sort, post);
	}
	//정렬기준으로 정렬된 게시글 목록에서 다음 게시글 가져오기
	public PostVO getPrevBoardSortedBy(Map<String, Object> post, String sort) {
		return sqlsession.selectOne("Board.getPrevBoardSortedBy"+sort, post);
	}
	//정렬기준으로 정렬된 게시글 목록에서 다음 게시글 가져오기
	public PostVO getNextBoardSortedBy(Map<String, Object> post, String sort) {
		return sqlsession.selectOne("Board.getNextBoardSortedBy"+sort, post);
	}
	//회원이 작성한 게시글 보기에서 다음 게시글 가져오기
	public PostVO getNextBoardByUser(Map<String, Object> post, String filter) {
		return sqlsession.selectOne("Board.getNextBoardByUser" + filter, post);
	}
	//회원이 작성한 게시글 보기에서 이전 게시글 가져오기
	public PostVO getPrevBoardByUser(Map<String, Object> post, String filter) {
		return sqlsession.selectOne("Board.getPrevBoardByUser" + filter, post);
	}
	//게시글 수정
	public void updateBoard(PostVO post) {
		sqlsession.update("Board.updateBoard", post);
	}
	//게시글 삭제
	public void deleteBoard(int post_no) {
		sqlsession.delete("Board.deleteBoard",post_no);
	}
	//댓글 추가
	public boolean insertBoardReply(ReplyVO reply) {
		return (sqlsession.insert("Board.insertBoardReply",reply) == 1) ? true : false;
	}
	//한 게시글의 댓글 목록 가져오기
	public List<ReplyVO> getReplyList(int post_no){
		return sqlsession.selectList("Board.getReply", post_no);
	}
	//댓글 수정
	public boolean updateBoardReply(Map<String,Object> editReply) {
		return (sqlsession.update("Board.updateBoardReply", editReply) == 1) ? true : false;
	}
	//댓글 삭제
	public void deleteBoardReply(int reply_num) {
		sqlsession.delete("Board.deleteBoardReply", reply_num);
	}
	//해당 게시글의 모든 댓글 삭제
	public void deleteBoardReplyAll(int board_num) {
		sqlsession.delete("Board.deleteBoardReplyAll", board_num);
	}
	//조회수 증가
	public void updateReadCount(int post_no) {
		sqlsession.update("Board.updateReadCount", post_no);
	}
	//전달받은 본문에서 이미지 태그의 파일명을 추출해서 반환
	public List<String> getImagesFileNameFromBoardContents(String post_contents) {
		List<String> fileNames = new ArrayList<String>();
		// 이미지 태그를 추출하기 위한 정규식.
		// 이미지 src 속성을 추출
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		// 내용 중에서 이미지 태그 찾기
		Matcher match = pattern.matcher(post_contents);
		while(match.find()) {// 이미지 태그가 있다면
			//이미지 src중 파일명만 추출 
			String imageFileName = match.group(1).substring(match.group(1).lastIndexOf("/") + 1, match.group(1).lastIndexOf("\n")-1);
			fileNames.add(imageFileName);	// 이미지 태그의 파일명을 모두 저장
		}
		return fileNames;
	}
	//전달받은 본문에서 동영상 태그의 url을 추출해서 반환
	public String getVideoUrlFromBoardContents(String post_contents) {
		String url = null;
		Pattern pattern = Pattern.compile("<iframe[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		
		// 내용 중에서 iframe 태그 찾기
		Matcher match = pattern.matcher(post_contents);
		if(match.find()) {// iframe 태그가 있다면
			//iframe src를 추출한 뒤 iframe 접두사를 붙여 저장합니다.
			url = "video_" + match.group(1);
			System.out.println("url : " + url);
		}
		return url;
	}
	//관리자 권한, 해당 게시글을 비공개로 전환
	public void updateHideBoard(int post_no) {
		sqlsession.update("Board.updateHideBoard", post_no);
	}
	//관리자 권한, 모든 게시글 공개로 전환
	public void updateShowBoardAll(int start_no, int end_no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("start_no", start_no);
		map.put("end_no", end_no);
		sqlsession.update("Board.upadteShowBoardAll", map);
	}
}


