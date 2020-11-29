package com.fromme.app.files;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fromme.mybatis.config.SqlMapConfig;
import com.oreilly.servlet.MultipartRequest;

public class FilesDAO {
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public FilesDAO() {
		sqlsession = sessionFactory.openSession(true);
	}
	//파일 태그로 첨부된 파일들만 가져오기
	public List<FilesVO> getDetail(int post_no) {
		return sqlsession.selectList("Files.getDetail",post_no);
	}
	//파일 태그에 첨부된 파일 DB 저장
	public boolean insertFiles(int post_no, MultipartRequest multi/*,String tempPath, String uploadPath*/) {
		Enumeration<String> files = multi.getFileNames();
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("post_no", post_no);
		//File checkFile = null;
		//첨부한 파일의 파일명 저장
		while(files.hasMoreElements()) {
			String data = files.nextElement();
			if(multi.getFilesystemName(data) == null) {continue;}
			
//			checkFile = new File(tempPath + '/' + multi.getFilesystemName(data));
//			if(checkFile.exists()) {
//				checkFile = new File(uploadPath + '/' + multi.getFilesystemName(data));
//				System.out.println("들어옴2");
//				System.out.println("multi.getFilessystemName : " + multi.getFilesystemName(data));
//				//checkFile.delete();
//				continue;
//			}
			fileMap.put("image_path", multi.getFilesystemName(data));
			if(sqlsession.insert("Files.insertFile",fileMap) != 1) {
				return false;
			}
		}
		//temp 폴더에 저장된 파일 삭제
//		File[] tempFiles = new File(tempPath).listFiles();
//		for(File file : tempFiles) {
//			file.delete();
//		}
		return true;
	}
	//전달받은 게시글 번호에 저장된 파일 삭제
	public void deleteFiles(int post_no) {
		sqlsession.delete("Files.deleteFiles",post_no);
	}
	//전달받은 게시글 번호의 웹 에디터에 첨부된 이미지 저장
	public boolean insertImages(int post_no, List<String> imageFileNamesFromBoardContents) {
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("post_no", post_no);
		
		//본문의 이미지 파일명 저장
		for(int i=0;i<imageFileNamesFromBoardContents.size();i++) {
			fileMap.put("image_path", imageFileNamesFromBoardContents.get(i));
			if(sqlsession.insert("Files.insertFile",fileMap) != 1) {
				return false;
			}
		}
		return true;
	}
	public boolean insertVideoUrl (int post_no, String videoUrl) {
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("post_no", post_no);
		fileMap.put("image_path", videoUrl);
		if(sqlsession.insert("Files.insertFile",fileMap) != 1) {
			return false;
		}
		return true;
	}
	//에디터에 첨부된 파일을 board_ 접두사를 붙여 DB, images 폴더에 저장
	//DB의 board_파일명과 images 폴더에 저장된 파일명들을 비교
	//images 폴더에 저장된 파일이 DB에 저장되어 있지 않다면 삭제
	public int deleteFilesFromUploadThatDoNoExistInTheDatabase(HttpServletRequest requset) {
		int count = 0;
		List<String> imagesList = sqlsession.selectList("Files.getBoardImageAll");
		//String saveFolder = "C:\\Users\\soonho\\Desktop\\it\\JSP\\workspace\\Fromme\\WebContent\\app\\upload\\images";
		String saveFolder = requset.getSession().getServletContext().getRealPath("/") + "app/upload/images";
		File[] filesList = new File(saveFolder).listFiles();	//해당 경로에 저장된 파일을 배열로 가져옵니다.
		
		for(File file : filesList) {
			boolean check = false;
			//file에서 파일명만 추출
			String fileName = file.toString().substring(file.toString().lastIndexOf("\\") + 1);
			for(String image : imagesList) {
				//DB에 저장된 파일이 images 폴더에 없다면 삭제
				if(fileName.equals(image)) {
					/*
					System.out.println("file : " + file);
					System.out.println("image : " + image);
					*/
					check = true;	//파일이 존재한다면 flag 변수를 true로 변경
				}
			}
			//true로 바뀌지 않았다는 것은 해당 파일이 DB 목록에 존재하지 않는 것
			if(!check) {
				file.delete();
				count++;	//삭제한 파일의 개수를 알려주기위해 누적
			}
		}
		return count;
	}
}