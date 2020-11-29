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
	<main role="main">
	<!-- Main Header -->
	<header>
		<%@ include file="../main/banner.jsp" %>
	</header>
	<div class="line"></div>
	<!-- MAIN --> <main role="main"> <!-- Content -->
	      <article>

        <section class="margin-top-50 full-width">

          <div class="background-white">
            <div class="line">
              <div class="margin margin-m-bottom-30" style="height:700px;">
                <div class="margin-left-10">
                  <h1 style="text-align:center;">회원가입</h1>
                  <h1 class="text-size-20 margin-bottom-0" style="text-align:center;">회원 유형을 선택해주세요.</h1><br><br>
                </div>
                <div>
                <div class="s-12 m-6 l-3" style="margin-left:15%; margin-right:20%; margin-top:6%;">
                  <a class="classFragment__HoverStyledLink" href="${pageContext.request.contextPath}/app/user/terms_of_use.jsp?authority=1">
                    <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                      <div class="image-hover-overlay background-primary" style="background:black;">
                        <div class=" image-hover-overlay-content text-center padding-2x">
                          <!-- hover action text -->
                          <p style="font-family:GmarketSansMedium; font-size:20px;">공방주 회원</p>
                        </div>
                      </div>
                      <img src="${pageContext.request.contextPath}/app/assets/img/open(4).png" alt="" title="Portfolio Image 2" />
                    </div>
                    <!-- simple discription -->
                  </a>
                </div>
                <div class="s-12 m-6 l-3" style="margin-right:15%; margin-top:6%;">
                  <a class="classFragment__HoverStyledLink" href="${pageContext.request.contextPath}/app/user/terms_of_use.jsp?authority=2">
                    <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                      <div class="image-hover-overlay background-primary">
                        <div class=" image-hover-overlay-content text-center padding-2x">
                          <!-- hover action text -->
                          <p  style="font-family:GmarketSansMedium; font-size:20px;">일반 회원</p>
                        </div>
                      </div>
                      <img src="${pageContext.request.contextPath}/app/assets/img/register(3).png" alt="" title="Portfolio Image 2" />
                    </div>
                  </a>
                </div>
                </div>

              </div>
            </div>
          </div>
        </section>
      </article>
	</main>
	<!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp" %>
</body>
</html>