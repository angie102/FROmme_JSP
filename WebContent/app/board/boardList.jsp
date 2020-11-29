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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-imagelist-style.css">
	</head>
	<c:set var="list" value="${requestScope.boardList}"/>
	<c:set var="nowPage" value="${requestScope.nowPage}"/>
	<c:set var="startPage" value="${requestScope.startPage}"/>
	<c:set var="endPage" value="${requestScope.endPage}"/>
	<c:set var="totalCount" value="${requestScope.totalCount}"/>
	<c:set var="totalPage" value="${requestScope.totalPage}"/>
	<c:set var="category_no" value="${requestScope.category_no}"/>
	<c:set var="category_name" value="${requestScope.category_name}"/>
	<c:set var="listtype" value="${requestScope.listtype}" />
	<c:set var="users_authority" value="${requestScope.users_authority}" />
	
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
						<!-- 게시판 퀵메뉴, 정렬 기능 리스트 -->
						<%@ include file="./board_template/boardQuickMenu.jsp" %>
						<%@ include file="./board_template/boardSortMenu.jsp" %>
						<jsp:useBean id="today" class="java.util.Date" />
						<c:choose>
							<c:when test="${listtype == 'image'}">
								<!-- 게시글 이미지 리스트 -->
								<%@ include file="./board_template/boardImageList.jsp" %>
							</c:when>
							<c:otherwise>
								<!-- 게시글 텍스트 리스트 -->
								<%@ include file="./board_template/boardTextList.jsp" %>							
							</c:otherwise>						
						</c:choose>
						
						<!-- 검색창, 페이징 처리 -->
						<%@ include file="./board_template/boardSearchAndPaging.jsp" %>
					</div>
			</article>
		</main>
		<div class="line"></div>
		<!-- FOOTER -->
		<footer>
			<%@ include file="../main/footer.jsp" %>
		</footer>
		<%@ include file="./board_template/loginModal.jsp" %>
	</body>
	<script> var session_id = "${session_id}"</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/app/assets/js/board.js"></script>
</html>