<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko-KR">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>FROmme - 내 손으로 만드는 특별한 순간</title>
		<%@ include file="../main/src_forInclude.html" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
	</head>
	<body class="size-1140">
		<c:set var="boardBean" value="${requestScope.boardBean}"/>
		<c:set var="filesBeanList" value="${requestScope.filesBeanList}"/>
		<c:set var="replyBeanList" value="${requestScope.replyBeanList}"/>
		<c:set var="nextBoard" value="${requestScope.nextBoard}"/>
		<c:set var="prevBoard" value="${requestScope.prevBoard}"/>
		
		<!-- HEADER -->
		<header role="banner" class="position-absolute">
			<!-- Top Navigation -->
			<%@ include file="../main/header.jsp" %>
		</header>
		<!-- slide banner -->
		<!-- MAIN -->
		<main role="main">
			<!-- Main Header -->
			<header>
				<%@ include file="../main/banner.jsp" %>
			</header>
		</main>
		<div class="line"></div>
		<!-- MAIN -->
		<main role="main">
			<!-- Content -->
			<article>
 				<div class="board_wrapper">
					<%@ include file="./board_template/boardNav.jsp" %>
					<!-- 게시판 -->
					<div class="board">
						<%@ include file="./board_template/boardQuickMenu.jsp" %>
						<ul class="bbsview_list">
							<!-- 각 항목을 li로 만들어주기 -->
							<li class="bbs_title">${boardBean.getPost_title()}</li>
							<li class="bbs_id">작성자 : <span>${boardBean.getUsers_id()}</span></li>
							<li class="bbs_date">작성일 : <span>${boardBean.getPost_date()}</span></li>
							<li class="bbs_hit">조회수 : <span>${boardBean.getPost_views()}</span></li>
							<li class="bbs_content">
								<div class="editer_content">${boardBean.getPost_content()}</div>
							</li>
							<li class="bbs_file">
								<h4>첨부 파일</h4>
								<c:if test="${filesBeanList != null}">
									<c:forEach var="file" items="${filesBeanList}">
										<a href="${pageContext.request.contextPath}/board/FileDownload.bo?image_path=${file.getImage_path()}">${file.getImage_path()}</a>
									</c:forEach>
								</c:if>
							</li>
						</ul>
						<p class="btn_line txt_right">
							<a href="${pageContext.request.contextPath}/board/BoardListByUser.bo?cat=${boardBean.getCategory_no()}&page=${param.page}&users_id=${param.users_id}&filter=${param.filter}" class="btn_bbs">목록</a>
							<c:if test="${boardBean.getUsers_id() eq session_id}">
								<a href="javascript:deleteBoard()" class="btn_bbs">삭제</a>
								<a href="${pageContext.request.contextPath}/board/BoardModify.bo?seq=${boardBean.getPost_no()}" class="btn_bbs">수정</a>						
							</c:if>
						</p>
						<form action="${pageContext.request.contextPath}/board/BoardDeleteOk.bo" method="post" name="boardform">
							<input type="hidden" name="seq" value="${boardBean.getPost_no()}" />
						</form>
   						<form action="${pageContext.request.contextPath}/board/BoardByUserReplyOk.bo?" method="POST" name="boardReply">
							<input type="hidden" name="users_id" value="${param.users_id}" />
							<input type="hidden" name="filter" value="${param.filter}" />
							<input type="hidden" name="seq" value="${boardBean.getPost_no()}" />
   							<input type="hidden" name="cat" value="${boardBean.getCategory_no()}" />
   							<input type="hidden" name="page" value="${param.page}" />
   							<input type="hidden" name="user" value="user" />   	
							<div class="reply_wrapper">
								<div><h4>댓글</h4></div>
								<textarea class="reply_contents" name="reply_contents"></textarea>
								<a href="javascript:comment()" class="btn_bbs">등록</a>
							</div>
						</form>
						<c:set var="i" value="${0}"/>
						<div class="reply_area">
							<form action="${pageContext.request.contextPath}/board/BoardByUserReplyModifyOk.bo?" method="post" name="boardReplyModifyUser">
	   							<input type="hidden" name="users_id" value="${param.users_id}" />
								<input type="hidden" name="filter" value="${param.filter}" />
	   							<input type="hidden" name="cat" value="${boardBean.getCategory_no()}" />
	   							<input type="hidden" name="page" value="${param.page}" />
	   							<input type="hidden" name="user" value="user" />
	   							<input type="hidden" name="replys_no" value="" />   	
								<input type="hidden" name="seq" value="" />
								<input type="hidden" name="num" value="" />
								<ul>
									<c:choose>
										<%-- 댓글이 한 개라도 있을 때--%>
										<c:when test="${!empty replyBeanList}">
										<%-- <c:when test="${replyBeanList != null and fn:length(replyBeanList) > 0}">--%>
											<c:forEach var="reply" items="${replyBeanList}">
												<c:set var="i" value="${i+1}"/>
												<div class="reply_area_box">
													<li class="reply_id">${reply.getReplys_id()}</li>
													<li>
														<textarea id="${i}" name="board_contents${i}" class="replys reply_contents" style="width:750px; resize:none; border:none " readonly>${reply.getReplys_content()}</textarea>
													</li>
													<li class="reply_info">${reply.getReplys_date()}
														<a href="javascript:deleteReplyForm(${reply.getReplys_no()},${reply.getPost_no()}, 'user')" class="reply_btn">삭제</a>
														<a id="ok${i}" href="javascript:submitReplyUpdateForm(${reply.getReplys_no()},${reply.getPost_no()},${i}, 'user')" style="display:none;">완료</a>
														<a id="ready${i}" href="javascript:updateReply(${i})" class="reply_btn">수정</a>
													</li>
												</div>	
												<%-- 작성자만 수정과 삭제 가능 
												<c:if test="${reply.getMember_id() eq session_id}">
													<a id="ready${i}" href="javascript:updateReply(${i})">[수정]</a>
													<a id="ok${i}" href="javascript:submitReplyUpdateForm(${reply.getReply_num()},${reply.getBoard_num()},${i})" style="display:none;">[수정 완료]</a>
													<a href="${pageContext.request.contextPath}/board/BoardReplyDeleteOk.bo?reply_num=${reply.getReply_num()}&seq=${boardBean.getBoard_num()}">[삭제]</a>
												</c:if>
												--%>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<li>댓글이 없습니다.</li>
										</c:otherwise>
									</c:choose>
								</ul>
							</form>
						</div>
						<form action="${pageContext.request.contextPath}/board/BoardByUserReplyDeleteOk.bo?" method="post" name="boardReplyDeleteUser">
							<input type="hidden" name="users_id" value="${param.users_id}" />
							<input type="hidden" name="filter" value="${param.filter}" />
   							<input type="hidden" name="cat" value="${boardBean.getCategory_no()}" />
   							<input type="hidden" name="page" value="${param.page}" />
   							<input type="hidden" name="user" value="user" />
   							<input type="hidden" name="replys_no" value="" />
							<input type="hidden" name="seq" value="" />
						</form>
						<ul class="near_list mt20">
			                <li><h4 class="prev">다음글</h4>
			                	<c:if test="${!empty nextBoard}">
				                	<a href="${pageContext.request.contextPath}/board/BoardViewByUser.bo?seq=${nextBoard.getPost_no()}&cat=${boardBean.getCategory_no()}&users_id=${param.users_id}&filter=${param.filter}">${nextBoard.getPost_title()}</a>
			                	</c:if>
			                	<c:if test="${empty nextBoard}">
				                	<span style="line-height: 27px;font-weight: bold;">게시글이 없습니다.</span>
			                	</c:if>
			                </li>			
			                <li><h4 class="next">이전글</h4>
			               		<c:if test="${!empty prevBoard}">
			               			<a href="${pageContext.request.contextPath}/board/BoardViewByUser.bo?seq=${prevBoard.getPost_no()}&cat=${boardBean.getCategory_no()}&users_id=${param.users_id}&filter=${param.filter}">${prevBoard.getPost_title()}</a>
			               		</c:if>
			               		<c:if test="${empty prevBoard}">
				                	<span style="line-height: 27px;font-weight: bold;">게시글이 없습니다.</span>
			                	</c:if>
			                </li>							
            			</ul>
					</div>
				</div>
			</article>
		</main>
	    <div class="line"></div>
	    <!-- FOOTER -->
	    <footer>
	      <%@ include file="../main/footer.jsp" %>
	    </footer>
	</body>
	<script src="https://rawgit.com/jackmoore/autosize/master/dist/autosize.min.js"></script>
	<script>autosize($("textarea.replys"));</script>
	<script src="${pageContext.request.contextPath}/app/assets/js/board.js"></script>
</html>