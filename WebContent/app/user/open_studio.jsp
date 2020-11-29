<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko-KR">
  <head>
  	<style type="text/css">
  		#box{border:1px solid black;}
  		#postcodify_search_button{border:1px solid black;}
  	</style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원가입(공방주) / FROmme - 내 손으로 만드는 특별한 순간</title>
   	<%@ include file="../main/src_forInclude.html" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
  </head>

  <body class="size-1140">
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
                  		<h1 style="text-align:center; font-family:GmarketSansMedium;">스튜디오 개설</h1>
                  		<br><br>
              		</div>
				  <form name="studioOpenForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/StudioOpenOk.use">
					<div class="margin-left-10" style="margin-bottom:15px;">
                 		<h2><label class="text-size-20 margin-bottom-0" style="float:left;">사업자 정보</label></h2><br>
               		</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium; height:85px;">
						<label for="studio_name">STUDIO NAME</label><br>
					    <input type="text" id="box" name="studio_name" value="" placeholder="공방의 이름을 입력하세요." style="margin-bottom:2px;">
					    <label id="studioCheck_text" style="font-size:80%; height:20px;"></label>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="studio_president">STUDIO PRESIDENT</label><br>
					    <input type="text" id="box" name="studio_president" value="" placeholder="사업자 이름을 입력하세요.">
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="studio_phone">STUDIO PHONE</label>
						<input type="text" id="box" name="studio_phone" value="" placeholder="공방 전화번호를 입력하세요."/>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="zipcode">SHOP ZIPCODE</label><br>
						<input type="text" id="box" name="studio_zipcode" class="postcodify_postcode5" value="" readonly style="width:79%;" placeholder="매장의 주소를 검색하세요."/>
						<input type="button" id="postcodify_search_button" value="검색" style="width:19%;">
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="address">SHOP ADDRESS</label>
						<input type="text" id="box" name="studio_address" class="postcodify_address" value="" readonly placeholder="매장 주소"/>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="address_detail">SHOP ADDRESS DETAIL</label>
						<input type="text" id="box" name="studio_address2" class="postcodify_details" value="" placeholder="상세 주소"/>
					</div>
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<label for="address_etc">SHOP ADDRESS ETC</label>
						<input type="text" id="box" name="address_etc" class="postcodify_extra_info" value="" readonly placeholder="동, 면, 리"/>
					</div>
					<div class="s-12 m-12 l-6 center">
					  <!-- search button -->
					    <div class="line">
					      <a class="button button-black-stroke s-12" onclick="studioFormSubmit()">승인 신청</a>
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
  <script src="${pageContext.request.contextPath}/app/user/studio.js"></script>
  <script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
  <script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
</html>