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
				<div class="s-12 m-12 l-6 center">
					<!-- search bar 세부 옵션  -->
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<div style="margin:0 auto; width:49%; float:left;" class="findId">
                  			<br><h1 style="text-align:center; font-family:GmarketSansMedium;" class="findId">아이디 찾기</h1><br>
              			</div>
						<div style="margin:0 auto; width:49%; float:left;" class="findPw">
                  			<br><h1 style="text-align:center; font-family:GmarketSansMedium; color:gray;" class="findPw">비밀번호 찾기</h1><br>
              			</div>
              			<div class="findIdTxt" style="display:block;">
							<form name="findIdForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/UserFindIdOk.use">
								<label for="users_name">이름</label><br>
								<input id="box" name="users_name" type="text" placeholder="이름을 입력하세요.">
								<label for="users_phone">PHONE</label><br>
								<input id="box" name="users_phone" type="text" placeholder="번호를 입력하세요.">
							</form>
							<div class="s-12 m-12 l-6 center" style="margin-top:25px; width:150px; margin:25px auto;">
								<!-- search button -->
								<div class="line">
									<a class="button button-black-stroke s-12 " href="javascript:findIdForm.submit()" id="findIdButton">찾기</a>
								</div>
							</div>
              			</div>
              			
	              		<div class="findPwTxt" style="display:none;">
							<form name="findPwForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/UserFindPwOk.use">
								<label for="id">ID</label><br> <input id="box" name="users_id" type="text" placeholder="이름을 입력하세요.">
								<label for="e-mail">E-MAIL</label><br> <input id="box" name="users_email" type="text" placeholder="이메일을 입력하세요.">
							</form>
							<div class="s-12 m-12 l-6 center" style="margin-top:25px; width:150px; margin:25px auto;">
								<!-- search button -->
								<div class="line">
									<a class="button button-black-stroke s-12 " href="javascript:findPwForm.submit()">찾기</a>
								</div>
							</div>
              			</div>
					</div>
			</div>
		</div>
	</article>
	</main> <!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp" %>
	</footer>
	<script>
		$(function(){
			$('.findPw').on('click',function(){
				$('.findIdTxt').hide();
				$('.findPwTxt').show();
				$('.findId').css("color", "gray");
				$('.findPw').css("color", "black");
			})
		});
		$(function(){
			$('.findId').on('click',function(){
				$('.findIdTxt').show();
				$('.findPwTxt').hide();
				$('.findId').css("color", "black");
				$('.findPw').css("color", "gray");
			});
		});
	</script>
</body>
</html>