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
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원정보 / FROmme - 내 손으로 만드는 특별한 순간</title>
   	<%@ include file="../main/src_forInclude.html" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
  </head>

  <body class="size-1140">
  <c:set var="studioInfo" value="${requestScope.studioInfo}"/>
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
		<div style="width:72%; float:left; height:1080px; background-color:white; font-family: GmarketSansMedium;">
					<!-- search bar 세부 옵션  -->
				  	<div style="margin:0 auto;">
                  		<br><h1 style="text-align:center; font-family:GmarketSansMedium;">공방정보수정</h1><br>
              		</div>
              		<label style="margin-left:5%;">나의 공방 관리</label>
				  <form name="updateStudioForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/StudioUpdateOk.use">
				  	<table style="border-top:2px solid black; width:90%; margin:auto;">
				  		<colgroup>
				  			<col width="30%;">
				  			<col width="">
				  			<col width="15%;">
				  		</colgroup>
				  		<tbody>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="studio_name">스튜디오 이름</label>
				  				</th>
				  				<td>
				  					<input type="text" id="box" name="studio_name" value="${studioInfo.getStudio_name()}" readonly>
				  				</td>
				  				<td></td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="studio_president">대표자 이름</label>
				  				</th>
				  				<td>
				  					<input type="text" id="box" name="studio_president" value="${studioInfo.getStudio_president()}">
				  				</td>
				  				<td></td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="studio_phone">스튜디오 번호</label>
				  				</th>
				  				<td>
									<input type="text" id="box" name="studio_phone" value="${studioInfo.getStudio_phone()}"/>
								</td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="address">주소</label>
				  				</th>
				  				<td>
									<input type="text" id="box" name="studio_address" class="postcodify_address" value="${studioInfo.getStudio_address()}" readonly style="width:79%" placeholder=""/>
									<input type="button" id="postcodify_search_button" value="검색" style="width:19%;">
									<input type="text" id="box" name="studio_address2" class="postcodify_details" value="${studioInfo.getStudio_address2()}" placeholder="상세주소"/>
				  				</td>
				  				<td></td>
				  			</tr>
				  		</tbody>
				  	</table>
				  	<div class="s-12 m-12 l-6 center" style="text-align:center;">
				  		<br><a onclick="updateStudioFormSubmit()">정보 수정</a>
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
  <script src="${pageContext.request.contextPath}/app/user/studio.js"></script>
  <script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
  <script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
</body>
</html>