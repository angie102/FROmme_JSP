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
		</style>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<%@ include file="../main/src_forInclude.html" %>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	</head>
<body class="size-1140">
	<!-- HEADER -->
	<%@ include file="../main/session_check.jsp" %>
	<header role="banner" class="position-absolute">
		<%@ include file="../main/header.jsp" %>
	</header>
	<!-- MAIN -->
	<main role="main"> <!-- Main Header -->
	<header>
		<%@ include file="../main/banner.jsp" %>
	</header>
	<div class="line"></div>
	<!-- MAIN --> <main role="main"> <!-- Content -->
	<article>
		<div class="section background-white">
			<div class="line">
			<div style="width:70%; margin:auto; font-family: GmarketSansMedium;">
			<div style="margin:auto; width:72%; height:300px; b	ackground-color:white; font-family: GmarketSansMedium;">
					<!-- search bar 세부 옵션  -->
				  	<div style="margin:0 auto;">
                  		<br><h1 style="text-align:center; font-family:GmarketSansMedium;">본인 확인</h1><br>
              		</div>
              		<%
				  		int no = Integer.parseInt(request.getParameter("no"));
				  	%>
				  <form name="checkIdPwForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/UserCheckIdPwOk.use?no=<%=no%>">
				  	<table style="border-top:2px solid black; width:90%; margin:auto;">
				  		<colgroup>
				  			<col width="15%;">
				  			<col width="">
				  			<col width="15%;">
				  		</colgroup>
				  		<tbody>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  				</th>
				  				<td>
				  				<br>
				  					<input type="password" id="box" name="users_pw" placeholder="비밀번호를 입력하세요." value="">
				  				</td>
				  				<td></td>
				  			</tr>
				  		</tbody>
				  	</table>
				  	<div class="s-12 m-12 l-6 center" style="text-align:center;">
				  		<br><a class="button button-black-stroke s-12 " href="javascript:checkIdPwForm.submit()">확인</a>
					</div>
		 		  </form>
			</div>
		</div>
		</div>
	</article>
	</main> <!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp" %>
	</footer>
</body>
</html>