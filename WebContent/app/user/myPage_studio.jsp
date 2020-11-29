<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
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
	    <div class="line"> 	
	    </div>
    <!-- MAIN -->
    <main role="main">
      <!-- Content -->
      <article>
		<div class="section background-white" id="body_back">
		<div style="width:70%; margin:auto; font-family: GmarketSansMedium;">
			<%@ include file="banner_myPage.jsp" %>
		<div style="width:72%; float:left; height:1000px;">
			<div style="background-color:white; height:250px; margin:0 auto; margin-bottom:20px;">
				<br>
				<h1 style="margin-left: 5%; font-size:20px; float:left;">스튜디오 개설</h1><br><br>
				<label style="margin-left:5%; float:left;"><a href="${pageContext.request.contextPath}/user/StudioOpen.use">스튜디오 정보를 입력하세요.</a></label>
			</div>
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
  <script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
</html>