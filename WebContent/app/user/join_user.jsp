<%@page import="java.util.PrimitiveIterator.OfDouble"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko-KR">
  <head>
  	<style type="text/css">
  		#box{border:1px solid black;}
  		#postcodify_search_button{border:1px solid black;}
  	</style>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원가입(일반유저) / FROmme - 내 손으로 만드는 특별한 순간</title>
    <%@ include file="../main/src_forInclude.html" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
  </head>
  <body class="size-1140">
  	<%
 	int a = request.getParameter("authority")==null? 0 : (Integer.parseInt(request.getParameter("authority")));
  	if(a == 0){
	%>
		<script>
			alert('약관 동의 후 회원가입을 진행해주세요.');
			location.href="${pageContext.request.contextPath}/app/user/join_select.jsp";
		</script>
	<%
		}
	%>
    <!-- HEADER -->
    <header role="banner" class="position-absolute">
      <%@ include file="../main/header.jsp" %>
    </header>
	<!-- MAIN -->
	  <main role="main">
	    <!-- Main Header -->
	    <header>
	      <%@ include file="../main/banner.jsp" %>
	    </header>
	    <div class="line"></div>
    <!-- MAIN -->
    <main role="main">
      <!-- Content -->
      <article>
		<div class="section background-white">
          <div class="line">
          	<div class="s-12 m-12 l-6 center" style="font-family: GmarketSansMedium;">
					<!-- search bar 세부 옵션  -->
				  	<div style="margin:0 auto;">
				  		<%
				  			
				  			/* int a = Integer.parseInt(request.getParameter("authority")); */
				  			if(a == 1){
				  		%>
	                  		<h1 style="text-align:center; font-family:GmarketSansMedium;">회원가입(공방주)<br><br><br></h1>
				  		<%
				  			} else if (a == 2){
				  		%>
	                  		<h1 style="text-align:center; font-family:GmarketSansMedium;">회원가입(일반회원)<br><br><br></h1>
				  		<%
				  			}
				  		%>
              		</div>
				  <form name="joinForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/UserJoinOk.use">
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium; height:85px;">
						<label for="id">ID</label><br>
					    <input type="text" id="box" name="users_id" value="" placeholder="아이디를 입력하세요." style="margin-bottom:0px;">
					    <label id="idCheck_text" style="font-size:80%; height:20px;"></label>
					</div>	
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="name">NAME</label><br>
					    <input type="text" id="box" name="users_name" value="" placeholder="이름을 입력하세요.">
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="password">PASSWORD</label><br>
					    <input type="password" id="box" name="users_pw" value="" placeholder="비밀번호를 입력하세요.">
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium; height:85px;">
						<label for="password_check">PASSWORD CHECK</label><br>
					    <input type="password" id="box" name="users_pw_re" value="" placeholder="비밀번호를 재입력하세요." style="margin-bottom:0px;">
					    <label id="checkPw_re" style="font-size:80%; height:20px;"></label>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="phone">PHONE</label>
						<input type="text" id="box" name="users_phone" value="" placeholder="전화번호를 입력하세요."/>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="email">E-MAIL</label>
						<input type="email" id="box" name="users_email" value="" placeholder="이메일을 입력하세요."/>
						<input name="users_authority" value="${param.authority}" style="display:none">
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="zipcode">ZIPCODE</label><br>
						<input type="text" id="box" name="users_zipcode" class="postcodify_postcode5" value="" readonly style="width:79%;" placeholder="주소를 검색하세요."/>
						<input type="button" id="postcodify_search_button" value="검색" style="width:19%;">
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="address">ADDRESS</label>
						<input type="text" id="box" name="users_address" class="postcodify_address" value="" readonly placeholder="주소"/>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="address_detail">ADDRESS DETAIL</label>
						<input type="text" id="box" name="users_address_detail" class="postcodify_details" value="" placeholder="상세 주소"/>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="address_etc">ADDRESS ETC</label>
						<input type="text" id="box" name="users_address_etc" class="postcodify_extra_info" value="" readonly placeholder="동, 면, 리"/>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="genre_no">GENRE</label><br>
						<label style="font-size:15px;">선호하는 장르를 선택해 주세요 (1개)</label><br><br>
						<label for="genre41">공예</label>
						<input type="radio" id="genre41" name="genre_no" value="41">
						<label for="genre42">사진/영상</label>
						<input type="radio" id="genre42" name="genre_no" value="42">
						<label for="genre43">요리/음료</label>
						<input type="radio" id="genre43" name="genre_no" value="43">
						<label for="genre44">디자인</label>
						<input type="radio" id="genre44" name="genre_no" value="44">	
						<label for="genre1">목공예</label>
						<input type="radio" id="genre1" name="genre_no" value="1">
						<label for="genre21">순수미술</label>
						<input type="radio" id="genre21" name="genre_no" value="21">
					</div>
					<br><br>
					<div class="s-12 m-12 l-6 center">
					  <!-- search button -->
					    <div class="line">
					      <a class="button button-black-stroke s-12" onclick="formSubmit()">회원가입</a>
					    </div>
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
  <script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/app/user/join.js"></script>
  <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
  <script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
</html>


