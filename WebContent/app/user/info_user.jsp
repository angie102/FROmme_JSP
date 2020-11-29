<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko-KR">
  <head>
  	<style type="text/css">
  		#box{border:1px solid black;}
  		#postcodify_search_button{border:1px solid black;}
  		#body_back{background-image:url("${pageContext.request.contextPath}/app/assets/img/banner/background_body.png");}
  	</style>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원정보 / FROmme - 내 손으로 만드는 특별한 순간</title>
    <%@ include file="../main/src_forInclude.html" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
  </head>

  <body class="size-1140">
  <c:set var="userInfo" value="${requestScope.userInfo}"/>
    <!-- HEADER -->
    <%@ include file="../main/session_check.jsp" %>
    <header role="banner" class="position-absolute">
		<%@ include file="../main/header.jsp" %>
	</header>
	<!-- MAIN -->
	  <main role="main">
	    <!-- Main Header -->
	    <header>
	      <%@ include file="../main/banner.jsp" %>
	    </header>
	    <div class="line"> 	
	    </div>
    <!-- MAIN -->
    <main role="main">
      <!-- Content -->
      <article>
		<div class="section background-white" id="body_back">
		<div style="width:70%; margin:auto; font-family: GmarketSansMedium;">
			<%@ include file="banner_myPage.jsp" %>
		<div style="width:72%; float:left; height:90%; background-color:white; font-family: GmarketSansMedium;">
					<!-- search bar 세부 옵션  -->
				  	<div style="margin:0 auto;">
                  		<br><h1 style="text-align:center; font-family:GmarketSansMedium;">회원정보수정</h1><br>
              		</div>
              		<label style="margin-left:5%;">나의 정보 관리</label>
				  <form name="updateUserForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/UserUpdateOk.use">
				  	<table style="border-top:2px solid black; width:90%; margin:auto;">
				  		<colgroup>
				  			<col width="30%;">
				  			<col width="">
				  			<col width="15%;">
				  		</colgroup>
				  		<tbody>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="id">ID</label>
				  				</th>
				  				<td>
				  					<input type="text" id="box" name="users_id" value="${userInfo.getUsers_id()}" readonly>
				  				</td>
				  				<td></td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="pw">PW</label>
				  				</th>
				  				<td style="height:90%;">
				  					<input type="password" id="box" name="users_pw" value="" placeholder="기존의 비밀번호를 입력하세요.">
				  					<input type="password" id="box" name="updateUsers_pw" value="" placeholder="변경하고자 하는 비밀번호를 입력하세요.">
				  					<input type="password" id="box" name="updateUsers_pw_re" value="" placeholder="변경하고자 하는 비밀번호를 다시 한번 입력하세요.">
				  				</td>
				  				<td></td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="name">이름</label>
				  				</th>
				  				<td>
				  					<input type="text" id="box" name="users_name" value="${userInfo.getUsers_name()}">
				  				</td>
				  				<td></td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="hp">HP</label>
				  				</th>
				  				<td>
									<input type="text" id="box" name="users_phone" value="${userInfo.getUsers_phone()}"/>
								</td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="email">E-MAIL</label>
				  				</th>
				  				<td>
				  					<input type="email" id="box" name="users_email" value="${userInfo.getUsers_email()}"/>
				  					<input type="text" name="users_authority" value="${userInfo.getUsers_authority()}" style="display:none;"/>
				  				</td>
				  				<td></td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="address">주소</label>
				  				</th>
				  				<td>
				  					<input type="text" id="box" name="users_zipcode" class="postcodify_postcode5" value="${userInfo.getUsers_zipcode()}" readonly style="width:79%;" placeholder="${userInfo.getUsers_zipcode()}"/>
									<input type="button" id="postcodify_search_button" value="검색" style="width:19%;">
									<input type="text" id="box" name="users_address" class="postcodify_address" value="${userInfo.getUsers_address()}" readonly placeholder="${userInfo.getUsers_address()}"/>
									<input type="text" id="box" name="users_address_detail" class="postcodify_details" value="${userInfo.getUsers_address_detail()}" placeholder="${userInfo.getUsers_address_detail()}"/>
				  				</td>
				  				<td></td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="genre">장르</label>
				  				</th>
				  				<td>
									<label>선호하는 장르를 선택해 주세요 (1개)</label><br>
									<label for="genre41">공예</label>
									<input type="radio" id="genre41" name="genre_no" value="41">
									<label for="genre42">사진/영상</label>
									<input type="radio" id="genre42" name="genre_no" value="42">
									<label for="genre43">요리/음료</label>
									<input type="radio" id="genre43" name="genre_no" value="43">
									<br>
									<label for="genre44">디자인</label>
									<input type="radio" id="genre44" name="genre_no" value="44">	
									<label for="genre1">목공예</label>
									<input type="radio" id="genre1" name="genre_no" value="1">
									<label for="genre21">순수미술</label>
									<input type="radio" id="genre21" name="genre_no" value="21">
					            </td>
				  				<td></td>
				  			</tr>
				  		</tbody>
				  	</table>
				  	<div class="s-12 m-12 l-6 center" style="text-align:center;">
				  		<br><a onclick="updateFormSubmit()">정보 수정</a><br>
				  		<input style="visibility:hidden;">
					</div>
		 		  </form>
			</div>
		</div>
          <div class="line">
          	
			</div>
		</div>
      </article>
    </main>

  <!-- FOOTER -->
  <footer>
  	<%@ include file="../main/footer.jsp" %>
  </footer>
  <script src="${pageContext.request.contextPath}/app/user/join.js"></script>
  <script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
</html>