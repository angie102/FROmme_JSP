<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html lang="ko-KR">
	<head>
		<title>로그인 / FROmme - 내 손으로 만드는 특별한 순간</title>
		<style type="text/css">
		#box {
			border: 1px solid black;
		}
		.button_basic{
			/* border: 1px solid black; */
			margin:auto;
			width:35%;
			height:30%;
		}
		</style>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<%@ include file="../main/src_forInclude.html" %>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	</head>
	<body class="size-1140">
	<!-- HEADER -->
	<header role="banner" class="position-absolute">
		<%@ include file="../main/header.jsp" %>
	</header>
	<!-- MAIN -->
	<main role="main"> <!-- Main Header --> <header>
		<%@ include file="../main/banner.jsp" %>
	</header>
	<div class="line"></div>
	<!-- MAIN --> <main role="main"> <!-- Content -->
	<article>
		<div class="section background-white">
			<div class="line">
				<div style="width:70%; margin:auto; font-family: GmarketSansMedium;">
		<div style="margin:auto; width:72%; height:300px; background-color:white; font-family: GmarketSansMedium;">
					<!-- search bar 세부 옵션  -->
				  	<div style="margin:0 auto;">
                  		<br><h1 style="text-align:center; font-family:GmarketSansMedium;">가입 정보</h1><br>
              		</div>
				  <form name="checkIdPwForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/UserCheckIdPwOk.use">
				  	<div style="border-top:2px solid black; border-bottom:1px solid gray; width:90%; height:90px; margin:auto; text-align:center;">
		  				<br>
		  				<%
		  					if((int)session.getAttribute("num") == 1){
		  				%>
		  					<label>${users_name}님의 정보로 가입된 아이디는 ${find_id} 입니다.</label><br><br>
		  				<%		
		  					} else {
		  				%>
		  					<label>${users_id}님의 비밀번호는 ${find_pw} 입니다.</label><br><br>
		  				<%
		  					}
		  				%>
				  	</div>
				  	<div style="text-align:center; margin:auto; height:90px; width:90%;">
				  		<br><a class="button_basic" href="${pageContext.request.contextPath}/app/user/login.jsp" style="float:left; margin-left:10%;">로그인</a>
				  		<a class="button_basic" href="javascript:history.back();" style="float:left; margin-left:10%;">확인</a>
					</div>
		 		  </form>
			</div>
		</div>
		</div>
	</article>
	</main>
	<!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp" %>
	</footer>
</body>
</html>