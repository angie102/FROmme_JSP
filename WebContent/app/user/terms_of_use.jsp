<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
  <head>
  	<style type="text/css">
  		#body_back{background-image:url("${pageContext.request.contextPath}/app/assets/img/banner/background_body.png");}
  	</style>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원정보 / FROmme - 내 손으로 만드는 특별한 순간</title>
	<%@ include file="../main/src_forInclude.html" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/app/user/style.css">
  </head>

  <body class="size-1140">
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
	    <div class="line"> 	
	    </div>
    <!-- MAIN -->
    <main role="main">
      <!-- Content -->
      <article>
		<div class="section background-white" id="body_back">
		<div style="width:45%; margin:auto; font-family: GmarketSansMedium;">
		<div style="width:100%; height:1000px;">
			<h1 style="color:white; text-align:center;">회　원　가　입</h1>
			<div style="background-color:white; height:200px; margin:0 auto; margin-bottom:20px;">
				<br><h3><label style="margin-left: 5%; float:left; color:black;">가입 안내</label></h3><br>
				<div style="width:80%; margin:auto;">
					<div class="container" style="width:100%; height:100px;">
							<div align="left" class="circle">
								<span style="color:white;">약관 동의</span>
							</div>
							<div align="center" class="circle_center">
								<span style="color:white;">개인정보입력</span>
							</div>	
							<div align="right" class="circle_back">
								<span style="color:white;">회원가입</span>
							</div>
					</div>
				</div>
			</div>
			
			<form name=checkTosForm class="customform" method="post" action="${pageContext.request.contextPath}/app/user/join_user.jsp?authority=${param.authority}">
			<div style="background-color:white; height:800px; margin:0 auto; margin-bottom:20px;">
				<br><h3><label style="margin-left: 5%; float:left; color:black;">이용약관동의</label></h3><br>
					<div style="margin-left:5%; margin-right:5%;">
						<section>
							<div class="select_subject">
							<div>
							<input type="checkbox" name="check_tos_service" id="service">
							<label for="service">서비스 이용약관<span style="color:skyblue;"> (필수)</span></label><br>
								<%@ include file = "tos_text.jsp" %>
							</div>
							<br>
							<div>
							<input type="checkbox" name="check_tos_info" id="info">
							<label for="info">개인정보 수집 및 이용 내역<span style="color:skyblue;"> (필수)</span></label><br>
							<table style="border:1px solid gray; border-right:1px solid gray;">
								<colgroup>
									<col width="33%">
									<col width="33%">
									<col width="33%">
								</colgroup>
								<thead>
									<tr style="text-align:center;">
										<th>수집 목적</th>
										<th>수집 항목</th>
										<th>보유 기간</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>(법인 정보) 이용자 식별 및 본인여부 확인</td>
										<td>사업자명, 아이디, 비밀번호, 대표자명, 대표, 전화번호, 이메일 주소, 휴대폰</td>
										<td>회원 탈퇴 시까지</td>
									</tr>
									<tr>
										<td>(법인 정보) 법인 서비스 제공을 위한 법인 정보 수집</td>
										<td>법인 브랜드명, 사업유형</td>
										<td>회원 탈퇴 시까지</td>
									</tr>
								</tbody>
							</table>
							</div>
							</div>
							<br>
							<div>
								<input class="input_check" type="checkbox" id="check_all">
								<label for="check_all">모두 동의</label>
							</div>
						</section>
					</div><br>
					<div class="s-12 m-12 l-6 center">
					  <!-- search button -->
					    <div class="line" style="text-align:center; border:1px solid gray; width:40%; height:30px; padding:5px;">
					      <a onclick="check_tos()">약관 동의</a>
					    </div>
					</div>
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