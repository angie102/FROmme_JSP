<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko-KR">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>FROmme - 내 손으로 만드는 특별한 순간</title>
		<%@ include file="../main/src_forInclude.html" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
	</head>
	
	<c:set var="list" value="${requestScope.boardList}"/>
	<c:set var="nowPage" value="${requestScope.nowPage}"/>
	<c:set var="startPage" value="${requestScope.startPage}"/>
	<c:set var="endPage" value="${requestScope.endPage}"/>
	<c:set var="totalCount" value="${requestScope.totalCount}"/>
	<c:set var="totalPage" value="${requestScope.totalPage}"/>
	<c:set var="totalBoardCount" value="${requestScope.totalBoardCount}"/>
	<c:set var="totalReplyCount" value="${requestScope.totalReplyCount}"/>
	<c:set var="filter" value="${requestScope.filter}"/>
	<c:set var="users_id" value="${requestScope.users_id}"/>
	
	<body class="size-1140">
		<!-- HEADER -->
		<header role="banner" class="position-absolute">
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
					<!-- 게시판 목차 -->
					<%@ include file="./board_template/boardNav.jsp" %>
					<!-- 게시판 -->
					<div class="board">
						<%@ include file="./board_template/boardQuickMenu.jsp" %>
						<!-- 게시판 퀵메뉴, 정렬 기능 리스트 -->
						<div class="user_area" style="clear:both; padding-top:15px;">
							<h4 style="text-align:left;">아이디 : ${users_id}</h4>
							<span style="font-size:14px;">총 게시글 : 
								<span style="color:red;">${totalBoardCount}</span>개 ㆍ총 댓글 : 
								<span style="color:red;">${totalReplyCount}</span>개
							</span>				
						</div>
						<div class="sort_wrapper">
							<ul style="float:left !important; margin-top:20px;">
						      	<li class="sort">
						      		<a href="${pageContext.request.contextPath}/board/BoardListByUser.bo?page=1&users_id=${param.users_id}&filter=reply"class="${param.filter == 'reply' ? 'selected' : ''}">댓글단 글</a>
								</li>
								<li class="sort">
						      		<a href="${pageContext.request.contextPath}/board/BoardListByUser.bo?page=1&users_id=${param.users_id}&filter=board" class="${param.filter == 'board' ? 'selected' : ''}">작성글</a>
						      	</li>
							</ul>
						</div>
						<table class="bbsListTbl" summary="번호, 제목, 조회수, 작성일 등을 제공하는 표">
							<caption class="hdd">공지사항 목록</caption>
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">제목</th>
									<c:if test="${filter eq 'reply'}">
										<th scope="col">작성자</th> 
									</c:if>
									<th scope="col">작성일</th>
									<th scope="col">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${list != null and fn:length(list) > 0}">
										<c:forEach var="b_bean" items="${list}" varStatus="stat">
											<jsp:useBean id="today" class="java.util.Date"></jsp:useBean>
											<c:set var="today_year" value="${today.year + 1900}"></c:set>
											<c:set var="today_day" value="${today.date}"></c:set>
											<fmt:parseDate var="parsedDate" value="${b_bean.getPost_date()}" pattern="yyyy-MM-dd HH:mm:ss"/>
											<fmt:parseNumber value="${parsedDate.year + 1900}" var="post_year"/>
											<fmt:parseNumber value="${parsedDate.date}" var="post_date"/>
											<c:set value="${today_day - post_date}" var="dayDiff" />
											<tr>
												<td>${totalCount - stat.index - ((nowPage - 1) * 15)}</td>
												<td class="tit_notice">
													<a href="javascript:checkSessionIdBeforeSubmitBoardView(${b_bean.getPost_no()},${b_bean.getCategory_no()}, 'user')">
														${b_bean.getPost_title()}
													</a>
													<c:if test="${b_bean.getReply_count() > 0}">
														<span style="color:red; font-weight:bold;">[${b_bean.getReply_count()}]</span>								
													</c:if>
													<c:if test="${(today_year eq post_year) and (dayDiff eq 0)}">
														<span class="material-icons fiber_new">fiber_new</span>								
													</c:if>
												</td>
												<c:if test="${filter eq 'reply'}">
													<td><span>${b_bean.getUsers_id()}</span></td>
												</c:if>
												<td>
													<c:choose>
														<c:when test="${(today_year eq post_year) and (dayDiff eq 0)}">
															<fmt:formatDate var="boardPostDate" value="${parsedDate}" pattern="HH:mm"/>
														</c:when>
														<c:otherwise>
															<fmt:formatDate var="boardPostDate" value="${parsedDate}" pattern="yyyy.MM.dd"/>
														</c:otherwise>
													</c:choose>
													${boardPostDate}
												</td>
												<td>${b_bean.getPost_views()}</td>
											</tr>              		
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
						<div style="clear:both;"></div>
						<div class="pagination">
						<c:set var="url" value="${pageContext.request.contextPath}/board/BoardListByUser.bo?&users_id=${param.users_id}&filter=${param.filter}" />
						<c:set var="image_path" value="${pageContext.request.contextPath}/app/assets/img/board_images"></c:set>
							<c:choose>
								<c:when test="${nowPage > 1}">
									<a href="${url}&page=${startPage}" class="firstpage pbtn"><img src="${image_path}/btn_firstpage.png" alt="첫 페이지로 이동"></a>
									<a href="${url}&page=${nowPage - 1}" class="prevpage pbtn"><img src="${image_path}/btn_prevpage.png" alt="이전 페이지로 이동"></a>          	
								</c:when>          
							</c:choose>
							<c:if test="${nowPage >= 1}">
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<c:choose>
										<c:when test="${i eq nowPage}">
											<a href="#"><span class="pagenum currentpage">${i}</span></a>
										</c:when>
										<c:otherwise>
											<a href="${url}&page=${i}"><span class="pagenum">${i}</span></a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:choose>
								<c:when test="${nowPage != totalPage}">
									<a href="${url}&page=${nowPage + 1}" class="nextpage pbtn"><img src="${image_path}/btn_nextpage.png" alt="다음 페이지로 이동"></a>
									<a href="${url}&page=${endPage}" class="lastpage pbtn"><img src="${image_path}/btn_lastpage.png" alt="마지막 페이지로 이동"></a>                              	
								</c:when>
							</c:choose>
						</div>
					</div>
			</article>
		</main>
		<form action="${pageContext.request.contextPath}/board/BoardViewByUser.bo" method="GET" name="boardviewbyuserform">
			<input type="hidden" name="seq" value="" />
			<input type="hidden" name="cat" value="" />
			<input type="hidden" name="users_id" value="${param.users_id}" />
			<input type="hidden" name="page" value="${nowPage}" />
			<input type="hidden" name="filter" value="${param.filter}" />
		</form>
		<div class="line"></div>
		<!-- FOOTER -->
		<footer>
			<%@ include file="../main/footer.jsp" %>
		</footer>
		<%@ include file="./board_template/loginModal.jsp" %>
		<script> var session_id = "${session_id}"</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/app/assets/js/board.js"></script>
	</body>
</html>