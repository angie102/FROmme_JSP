package com.fromme.app.user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fromme.app.user.vo.MessageVO;
import com.fromme.mybatis.config.SqlMapConfig;
//2. BoardDAO에 Board.boardCnt을 사용하는 메소드 구현
public class MessageDAO {
	
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public MessageDAO() {
		sqlsession = sessionf.openSession(true);
	}
	
	//작성된 게시글의 총 개수를 가져온다.
	public int getMessageCnt(String session_id) {
		return (sqlsession.selectOne("Message.messageCnt", session_id));
	}
	
	//게시판에 작성된 모든 게시글을 가져오는 메소드
	public List<MessageVO> getMessageList(int startRow, int endRow, String session_id){
		HashMap<String, Object> pageMap = new HashMap<>();
		
		//DB 조회시 필요한 게시글 시작 번호와 끝 번호를 Map에 담아서 전달해준다.
		//전체 게시글을 가져온 후 연산을 하면 느리기 때문에
		//필요한 만큼의 게시글을 가져오도록 한다.
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("session_id", session_id);
		
		List<MessageVO> messageList = sqlsession.selectList("Message.listAll", pageMap);
		return messageList;
	}
	
	//게시판의 현재 게시글 번호 가져오기
	public int getMessageSeq() {
		return sqlsession.selectOne("Message.getSeq");
	}
	
	//게시글 상세보기를 사용자가 요청했을 때 해당 게시글의 정보를 리턴해주는 메소드
	//사용자가 요청한 게시글 번호를 전달받는다.
	public MessageVO getDetail(int message_num) {
		return sqlsession.selectOne("Message.getDetail", message_num);
	}
	
	//사용자가 작성한 게시글 내용을 BoardVO객체로 전달받는다.
	public boolean insertMessage(MessageVO message) {
		if(sqlsession.insert("Message.insertMessage", message) == 1) {
			return true;
		}
		return false;
	}
	
	//게시글 상세보기 시, 해당 게시글의 조회수를 증가시켜준다.
	//상세보기를 요청할 때 마다 증가하도록 구현한다.
	public void updateMessageCount(int message_num) {
		sqlsession.update("Message.updateReadCount", message_num);
	}
	
	public void deleteMessage(int message_num) {
		sqlsession.delete("Message.deleteMessage", message_num);
	}
	
}

