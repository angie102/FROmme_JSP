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
		<meta name="google-signin-scope" content="profile email">
 		<meta name="google-signin-client_id" content="335079007228-00af1uu8lea4mljp3h17ik0oc68cttpa.apps.googleusercontent.com">
		<%@ include file="../main/src_forInclude.html" %>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
		<script type="text/javascript"
			src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"
			charset="utf-8"></script>
		<script type="text/javascript"
			src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
			charset="utf-8"></script>
		<script type="text/javascript"
			src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
		<script src="https://apis.google.com/js/platform.js" async defer></script>
	</head>
	<body class="size-1140">
	<!-- HEADER -->
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
				<div class="s-12 m-12 l-6 center">
					<!-- search bar 세부 옵션  -->
					<div class="margin-bottom-10 background-white-hightlight" style="font-family: GmarketSansMedium;">
						<div style="margin:0 auto;">
                  			<br><h1 style="text-align:center; font-family:GmarketSansMedium;">로그인</h1><br>
              			</div>
						<form name="loginForm" class="customform" method="post" action="${pageContext.request.contextPath}/user/UserLoginOk.use">
							<label for="id">ID</label><br>
							<input id="box" name="users_id" type="text" placeholder="ID를 입력하세요.">
							<label for="password">PASSWORD</label><br>
							<input id="box" name="users_pw" type="password" placeholder="PASSWORD를 입력하세요.">
						<div style="margin-bottom: 25px; font-family: GmarketSansMedium; height:25px;">
							<a href="${pageContext.request.contextPath}/app/user/find_id_pw.jsp" style="margin-right: 20px; float:left;">아이디/비밀번호 찾기</a>
							<a href="${pageContext.request.contextPath}/app/user/join_select.jsp" style="margin-right: 20px; float:left;">회원가입</a>
							<!-- 네이버아이디로로그인 버튼 노출 영역 -->
							<div id="naverIdLogin" style="width:30%; height:30%; float:left; margin-right: 15px;"></div>
							<!-- 구글아이디로로그인 버튼 노출 -->
							<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark" style="height:30%; float:left;">dfd</div>
						</div><br>
						<div class="s-12 m-12 l-6 center" style="margin-top:25px; width:150px; margin:25px auto;">
							<!-- search button -->
							<div class="line">
								<a class="button button-black-stroke s-12 " href="javascript:loginForm.submit()">로그인</a>
							</div>
						</div>	
						</form>
					</div>
				</div>
				</div>
			</div>
		</div>
	</article>
	</main>
	<!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp" %>
	</footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/app/assets/js/responsee.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/app/assets/owl-carousel/owl.carousel.js"></script> <script
		type="text/javascript" src="${pageContext.request.contextPath}/app/assets/js/template-scripts.js"></script>
	<!-- 네이버아디디로로그인 초기화 Script -->
	<script type="text/javascript">
		var naverLogin = new naver.LoginWithNaverId({
			clientId : "pveDSN8aFWGN1Faa15NC",
			callbackUrl : "http://localhost:8081/FROmme/app/main/index.jsp",
			isPopup : false, /* 팝업을 통한 연동처리 여부 */
			loginButton : {
				color : "green",
				type : 3,
				height : 60
			}
		/* 로그인 버튼의 타입을 지정 */
		});

		/* 설정정보를 초기화하고 연동을 준비 */
		naverLogin.init();
	</script>
	<script>
			componentDidMount()
			{
				Kakao.init("e7aac7edfcf086b5e3915bcf4be4a38d");
			}
	</script>
	<script>
        function onSignIn(googleUser) {
            // Useful data for your client-side scripts:
            var profile = googleUser.getBasicProfile();
            console.log("ID: " + profile.getId()); // Don't send this directly to your server!
            console.log('Full Name: ' + profile.getName());
            console.log('Given Name: ' + profile.getGivenName());
            console.log('Family Name: ' + profile.getFamilyName());
            console.log("Image URL: " + profile.getImageUrl());
            console.log("Email: " + profile.getEmail());

            // The ID token you need to pass to your backend:
            var id_token = googleUser.getAuthResponse().id_token;
            console.log("ID Token: " + id_token);
        };
    </script>
</body>
</html>